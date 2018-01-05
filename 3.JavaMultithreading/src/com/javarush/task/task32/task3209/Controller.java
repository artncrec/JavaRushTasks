package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by admin on 18.07.2017.
 */
public class Controller {
    private View view;
    private HTMLDocument document = new HTMLDocument();
    private File currentFile;

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view) {
        this.view = view;
    }

    public void init(){
        createNewDocument();
    }

    public void setPlainText(String text){
        resetDocument();;
        StringReader reader = new StringReader(text);
        HTMLEditorKit kit = new HTMLEditorKit();
        try{
            kit.read(reader,  document, 0);
        } catch (Exception e) {ExceptionHandler.log(e);}
    }

    public String getPlainText(){
        StringWriter writer = new StringWriter();
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.write(writer, document, 0 ,document.getLength());
        } catch (Exception e) {ExceptionHandler.log(e);}
        return writer.toString();
    }

    public void resetDocument(){
        if (document!=null){
            document.removeUndoableEditListener(view.getUndoListener());
            HTMLEditorKit kit = new HTMLEditorKit();
            document = (HTMLDocument)kit.createDefaultDocument();
            document.addUndoableEditListener(view.getUndoListener());
            view.update();
        }
    }

    public void exit() {
        System.exit(0);
    }


    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML editor");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if (fileChooser.showOpenDialog(view) < 1) {
            try {
                view.selectHtmlTab();
                currentFile = fileChooser.getSelectedFile();
                resetDocument();
                view.setTitle(currentFile.getName());
                FileReader reader = new FileReader(currentFile);
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.read(reader, document, 0);
                view.resetUndo();
            } catch (Exception e) {ExceptionHandler.log(e);}
        }
    }

    public void saveDocument() {
        if (currentFile==null)
            saveDocumentAs();
        else {
            view.selectHtmlTab();
            try {
                FileWriter writer = new FileWriter(currentFile);
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.write(writer, document, 0, document.getLength());
                writer.close();
            } catch (Exception e) {ExceptionHandler.log(e);}
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if (fileChooser.showSaveDialog(view) < 1) {
            try {
                currentFile = fileChooser.getSelectedFile();
                view.setTitle(currentFile.getName());
                FileWriter writer = new FileWriter(currentFile);
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.write(writer, document, 0, document.getLength());
                writer.close();
            } catch (Exception e) {ExceptionHandler.log(e);}
        }
    }
}
