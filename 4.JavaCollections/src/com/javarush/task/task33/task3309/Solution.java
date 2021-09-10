package com.javarush.task.task33.task3309;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, TransformerException, ParserConfigurationException, IOException, SAXException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        marshaller.marshal(obj, document);

        NodeList nodeList = document.getElementsByTagName("*");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if(node.getNodeName().equals(tagName)) {
                node.getParentNode().insertBefore(document.createComment(comment), node);
            }

            replaceTextWithCDATA(node, document);

        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        StringWriter result = new StringWriter();

        transformer.transform(new DOMSource(document), new StreamResult(result));

        return result.toString();
    }

    private static void replaceTextWithCDATA(Node node, Document document){
        if (node.getNodeType() == Node.TEXT_NODE && (Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find())){
            Node cdataSection = document.createCDATASection(node.getNodeValue());
            node.getParentNode().replaceChild(cdataSection, node);
        }

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            replaceTextWithCDATA(nodeList.item(i), document);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException, JAXBException {
//        System.out.println(toXmlWithComment(new First(), "second", "it's a comment"));
    }
}