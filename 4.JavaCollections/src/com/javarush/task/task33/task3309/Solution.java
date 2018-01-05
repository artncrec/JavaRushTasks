package com.javarush.task.task33.task3309;

import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import org.w3c.dom.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException{
        StringWriter writer = new StringWriter();
        Marshaller marshaller = JAXBContext.newInstance(obj.getClass()).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
        Document document = new CoreDocumentImpl();

        return writer.toString();
    }
    @XmlRootElement
    public static class first {
        @XmlElement(name = "first")
        String data = "";
        @XmlElement(name = "second")
        String[] strings = new String[]{"some string", "", "<second>", "need CDATA because of <>"};
        @XmlElement(name = "second2")
        String string = "need CDATA because of \"";
    }

    public static void main(String[] args) throws JAXBException{
        first firstSecondObject = new first();
        System.out.println(toXmlWithComment(firstSecondObject, "second", "it’s a comment"));
    }
}
