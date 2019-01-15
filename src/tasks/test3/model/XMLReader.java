package tasks.test3.model;

import javax.swing.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XMLReader {

    private XMLStreamReader reader;

    public void read(File file, List<Human> humans) throws XMLStreamException, FileNotFoundException {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(new FileInputStream(file))) {
            reader = processor.getReader();

            while (doUntil(XMLEvent.START_ELEMENT, "employee")){
                Employee e = new Employee();

                doUntil(XMLEvent.START_ELEMENT, "secondname");
                e.setSecondName(reader.getElementText());
                doUntil(XMLEvent.START_ELEMENT, "salary");
                e.setSalary(Double.parseDouble(reader.getElementText()));
                doUntil(XMLEvent.START_ELEMENT, "position");
                e.setPosition(Employee.Position.valueOf(reader.getElementText()));

                humans.add(e);
            }
        }
    }

    public boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent && value.equals(reader.getLocalName())) {
                return true;
            }
        }
        return false;
    }


}


class StaxStreamProcessor implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) { // empty
            }
        }
    }
}