package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * Created by Анатолий on 25.02.2016.
 */
public class SaveData {
    final private Logger logger = LoggerFactory.getLogger(SaveData.class);
    Data data = Data.INSTANCE;
    Memory memory = Memory.INSTANCE;
    Converters converters = Converters.INSTANCE;

    public SaveData() {}
    public void save(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = db.newDocument();

        Element eRoot = doc.createElement("root");
        //add input
        Element input = doc.createElement("input");
        input.setAttribute("value",String.valueOf(data.input));
        eRoot.appendChild(input);
        //add output
        Element output = doc.createElement("output");
        output.setAttribute("value",String.valueOf(data.output));
        eRoot.appendChild(output);
        //add process
        Element process = doc.createElement("process");
        process.setAttribute("value",data.process.text);
        eRoot.appendChild(process);
        //add PCadr
        Element PCadr = doc.createElement("PCadr");
        PCadr.setAttribute("value",String.valueOf(data.PCadr));
        eRoot.appendChild(PCadr);
        //add PC
        Element PC = doc.createElement("PC");
        PC.setAttribute("value",String.valueOf(data.PC));
        eRoot.appendChild(PC);
        //add PSW
        Element PSW = doc.createElement("PSW");
        PSW.setAttribute("value",String.valueOf(data.PSW));
        eRoot.appendChild(PSW);
        //add SP
        Element SP = doc.createElement("SP");
        SP.setAttribute("value",String.valueOf(data.SP));
        eRoot.appendChild(SP);
        //add PC_Flag
        Element PC_Flag = doc.createElement("PC_Flag");
        PC_Flag.setAttribute("value",String.valueOf(data.PC_Flag));
        eRoot.appendChild(PC_Flag);
        //add numberReg
        Element numberReg = doc.createElement("numberReg");
        numberReg.setAttribute("value",String.valueOf(data.numberReg));
        eRoot.appendChild(numberReg);
        //add addressValueArray
        Element addressValueArray = doc.createElement("addressValueArray");
        for (int i = 0; i < data.addressValueArray.length ;i++)
        {
            Element addressValue = doc.createElement("addressValue");
            addressValue.setAttribute("value",data.addressValueArray[i].text);
            addressValueArray.appendChild(addressValue);
        }
        eRoot.appendChild(addressValueArray);

        //add registers
        Element registers = doc.createElement("registers");
        //reg A
        Element A = doc.createElement("A");
        A.setAttribute("value",String.valueOf(data.A));
        registers.appendChild(A);
        //reg B
        Element B = doc.createElement("B");
        B.setAttribute("value",String.valueOf(data.B));
        registers.appendChild(B);
        //reg C
        Element C = doc.createElement("C");
        C.setAttribute("value",String.valueOf(data.C));
        registers.appendChild(C);
        //reg D
        Element D = doc.createElement("D");
        D.setAttribute("value",String.valueOf(data.D));
        registers.appendChild(D);
        //reg E
        Element E = doc.createElement("E");
        E.setAttribute("value",String.valueOf(data.E));
        registers.appendChild(E);
        //reg H
        Element H = doc.createElement("H");
        H.setAttribute("value",String.valueOf(data.H));
        registers.appendChild(H);
        //reg L
        Element L = doc.createElement("L");
        L.setAttribute("value",String.valueOf(data.L));
        registers.appendChild(L);
        eRoot.appendChild(registers);

        //add memory
        Element ram =doc.createElement("ram");
        for (int i = 0; i<0xFFFF;i++ )
        {
            Element cell = doc.createElement("cell");
            String str = converters.UInt16ToHex(i);
            cell.setAttribute("address",str);
            cell.setAttribute("value",String.valueOf(memory.RAM.get(str)));
            ram.appendChild(cell);
        }
        eRoot.appendChild(ram);
        doc.appendChild(eRoot);

        String path = System.getProperty("user.dir")+"\\temp";
        if (!(new File(path)).exists())
            new File("temp").mkdir();
        logger.info("Path: "+path);
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            Result output1 = new StreamResult(new File(path+"\\tempData.xml"));
            Source input1 = new DOMSource(doc);
            transformer.transform(input1, output1);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
