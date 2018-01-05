package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".","/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        String newBody = getUpdatedFileContent(vacancies);
        updateFile(newBody);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();
            Element element = document.getElementsByClass("template").first();
            Element template = element.clone();
            template.removeAttr("style");
            template.removeClass("template");
            document.getElementsByAttributeValue("class", "vacancy").remove();

            for (Vacancy vacancy : vacancies) {
                Element clone = template.clone();
                clone.getElementsByClass("city").first().text(vacancy.getCity());
                clone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                clone.getElementsByClass("salary").first().text(vacancy.getSalary());
                clone.getElementsByTag("a").first().text(vacancy.getTitle()).attr("href", vacancy.getUrl());
                element.before(clone.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String s) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            char[] data = s.toCharArray();
            for (int i = 0; i < data.length; i++) {
                fileOutputStream.write((byte) data[i]);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
