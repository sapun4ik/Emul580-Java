package model;

import controller.DigitalPanelButton;
import controller.Processes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Анатолий on 25.02.2016.
 */
public class RecoveryData {
    Data data = Data.INSTANCE;
    Memory memory = Memory.INSTANCE;
    public void recovery() throws IOException, SAXException, ParserConfigurationException {
        String path = System.getProperty("user.dir") + "\\temp\\tempData.xml";
        File file = new File(path);
        if (!file.exists()) return;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        Element eRoot = doc.getDocumentElement();

        NodeList childNodes = eRoot.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++)
            if (childNodes.item(i) instanceof Element) {
                setData((Element) childNodes.item(i));
            }
    }

    private void setData(Element element) {
        String nameAtt = element.getTagName();
        switch (nameAtt) {
            case "input": {
                data.input = Short.parseShort(element.getAttribute("value"));
                break;
            }
            case "output": {
                data.output = Short.parseShort(element.getAttribute("value"));
                break;
            }
            case "process": {
                data.process = Processes.getProcess(element.getAttribute("value"));
                break;
            }
            case "PCadr": {
                data.PCadr = element.getAttribute("value");
                break;
            }
            case "PC": {
                data.PC = Integer.parseInt(element.getAttribute("value"));
                break;
            }
            case "PSW": {
                data.PSW = Short.parseShort(element.getAttribute("value"));
                break;
            }
            case "SP": {
                data.SP = Short.parseShort(element.getAttribute("value"));
                break;
            }
            case "PC_Flag": {
                data.PC_Flag = Boolean.getBoolean(element.getAttribute("value"));
                break;
            }
            case "numberReg": {
                data.numberReg = Integer.parseInt(element.getAttribute("value"));
                break;
            }
            case "addressValueArray": {
                NodeList childNodes = element.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++)
                    if (childNodes.item(i) instanceof Element) {
                        Element e = (Element) childNodes.item(i);
                        data.addressValueArray[i] = DigitalPanelButton.getDigitalPanelBtn(e.getAttribute("value").charAt(0));
                    }
                break;
            }
            case "ram": {
                NodeList childNodes = element.getChildNodes();
                memory.RAM = new HashMap<>();
                for (int i = 0; i < childNodes.getLength(); i++)
                    if (childNodes.item(i) instanceof Element) {
                        Element e = (Element) childNodes.item(i);
                        memory.RAM.put(e.getAttribute("address"),Integer.parseInt(e.getAttribute("value")));
                    }
                break;
            }
        }
    }

}
