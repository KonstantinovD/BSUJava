package tasks.lab13.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tasks.lab13.student.Student;

import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReadWriteHandler {

    private final static String xmlStudent = "student";
    private final static String xmlStudentRecordBookNumber = "recordBookNumber";
    private final static String xmlStudentSecondName = "secondName";
    private final static String xmlStudentYear = "year";
    private final static String xmlStudentGroup = "group";
    private static enum ReadingState{
        NOTHING,
        RECORD_BOOK_NUMBER,
        SECOND_NAME,
        YEAR,
        GROUP;

        public static ReadingState getStateByString(String xmlElement){
            if(xmlElement.equals(xmlStudentRecordBookNumber)) return RECORD_BOOK_NUMBER;
            if(xmlElement.equals(xmlStudentSecondName)) return SECOND_NAME;
            if(xmlElement.equals(xmlStudentYear)) return YEAR;
            if(xmlElement.equals(xmlStudentGroup)) return GROUP;
            return NOTHING;
        }
        public static void setValueToStudent(Student student, String value, ReadingState state){
            switch (state){
                case NOTHING: break;
                case RECORD_BOOK_NUMBER: student.setRecordBookNumber(Integer.parseInt(value)); break;
                case SECOND_NAME: student.setSecondName(value); break;
                case YEAR: student.setYear(Integer.parseInt(value)); break;
                case GROUP: student.setGroup(Integer.parseInt(value)); break;
            }
        }
    }

    private ReadingState state;
    private List<Student> outherContainer;

    public XMLReadWriteHandler(){
        state = ReadingState.NOTHING;
    }

    public void readFromXml(File file, List<Student> container)
            throws SAXException, ParserConfigurationException, IOException{
        if(container == null) throw new NullPointerException("Container to write to is null!");
        this.outherContainer = container;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(file, new XMLStudentHandler());
    }
    //handler for reading from file (process different events)
    private class XMLStudentHandler extends DefaultHandler {
        Student student;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase(xmlStudent)) {
                student = new Student();
            }else{
                state = ReadingState.getStateByString(qName);
            }
        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase(xmlStudent)) {
                outherContainer.add(student);
            }
        }
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            ReadingState.setValueToStudent(student, new String(ch, start, length), state);
            state = ReadingState.NOTHING;
        }
    }


    public void writeXMLToFile(File file, List<Student> container)
            throws IOException{
        BufferedWriter writer = null;
        try {
            String content = prepareXMLContent(container);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }
    private final static String xmlStudentsRoot = "students";
    private String prepareXMLContent(List<Student> container){
        StringBuilder sb = new StringBuilder(createStartElement(xmlStudentsRoot));

        for(Student st : container){
            sb.append(createStartElement(xmlStudent));

            sb.append(generateNodeWithValue(xmlStudentRecordBookNumber, String.valueOf(st.getRecordBookNumber())));
            sb.append(generateNodeWithValue(xmlStudentSecondName, st.getSecondName()));
            sb.append(generateNodeWithValue(xmlStudentYear, String.valueOf(st.getYear())));
            sb.append(generateNodeWithValue(xmlStudentGroup, String.valueOf(st.getGroup())));

            sb.append(createEndElement(xmlStudent));
        }
        sb.append(createEndElement(xmlStudentsRoot));
        return sb.toString();
    }
    private String createStartElement(String name){
        return "<"+name+">";
    }
    private String createEndElement(String name){
        return "</"+name+">";
    }

    private StringBuilder generateNodeWithValue(String name, String value){
        StringBuilder res = new StringBuilder(createStartElement(name));
        res.append(value);
        res.append(createEndElement(name));
        return res;
    }

    public static void main(String[] args) {
        XMLReadWriteHandler rwh = new XMLReadWriteHandler();

        ArrayList<Student> container = new ArrayList<>();

        JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file != null) {
                try {
                    rwh.readFromXml(file, container);
                    for(Student st : container){
                        System.out.println(st);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
