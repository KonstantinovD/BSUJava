package tasks.lab13.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tasks.lab13.student.Student;
import tasks.lab13.view.Frame;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Model {

    private List<Student> container;
    private List<Student> vizible;
    private Frame view;

    public Model(){
        container = new ArrayList<>();
        vizible = new ArrayList<>();
        xmlReadWriteHandler = new XMLReadWriteHandler();
    }

    public void setView(Frame view) {
        this.view = view;
    }

    public void add(Student student){
        container.add(student);
        vizible.add(student);
        sort(vizible);
        view.setContent(vizible);
    }

    /**
     * Reads data from file and stores in container
     * @param file
     */
    public void loadFile(File file){

        String extension = "";
        int i = file.getPath().lastIndexOf('.');
        if (i > 0) {
            extension = file.getPath().substring(i+1);
        }
        try {
            if(extension.equals("xml")){
                readFromXml(file);
            }else if(extension.equals("txt")){
                readFromTxt(file);
            }
            vizible.clear();
            if(container.isEmpty()) return;
            for(Student student : container){
                vizible.add(student);
            }
            view.setContent(container);
        }catch (FileNotFoundException fnfex){
            view.sendErrorMessage("File not found!");
            //fnfex.printStackTrace();
        }catch (Exception ex){
            view.sendErrorMessage("Unable to open file \"" + file.getAbsolutePath() + "\"");
            //ex.printStackTrace();
        }
    }
    private void readFromTxt(File file) throws FileNotFoundException{
        Scanner reader = new Scanner(file);
        container.clear();
        while(reader.hasNext()){
            container.add(getNextStudentFromScanner(reader));
        }
    }
    private Student getNextStudentFromScanner(Scanner reader){
        //secondName, bookNumber, year, group
        Student student = new Student();
        student.setSecondName(reader.next());
        student.setRecordBookNumber(reader.nextInt());
        student.setYear(reader.nextInt());
        student.setGroup(reader.nextInt());
        return student;
    }

    //XML methods
    private XMLReadWriteHandler xmlReadWriteHandler;

    private void readFromXml(File file) throws SAXException, IOException, ParserConfigurationException{
        container.clear();
        xmlReadWriteHandler.readFromXml(file, container);
    }

    public void saveFileToXml(File file){
        try {
            xmlReadWriteHandler.writeXMLToFile(file, vizible);
        }catch (IOException ioex){
            ioex.printStackTrace();
            view.sendErrorMessage("Unable to save data to file!");
        }catch (Exception ex){
            ex.printStackTrace();
            view.sendErrorMessage("Unable to save data to file!");
        }
    }


    /**
     * Sorts elements to show them
     */
    public void sort(List<Student> list){
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int res = o1.getSecondName().compareTo(o2.getSecondName());
                if(res == 0) {
                    res = Integer.compare(o1.getYear(), o2.getYear());
                    if (res == 0) {
                        res = Integer.compare(o1.getGroup(), o2.getGroup());
                        if (res == 0) {
                            res = o1.getSecondName().compareTo(o2.getSecondName());
                        }
                    }
                }return res;
            }
        });
    }

    public void buildDuplicates(){
        updateDuplicatesVizibleContent();
    }

    public void showAllContent(){
        vizible.clear();
        if(container.isEmpty()) return;
        for(Student student : container){
            vizible.add(student);
        }
        view.setContent(vizible);
    }

    /**
     * Updates content tho show after some changes in all the content
     */
    private void updateDuplicatesVizibleContent(){
        vizible.clear();
        if(container.isEmpty()) return;
        for(Student student : container){
            vizible.add(student);
        }

        vizible.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getSecondName().compareTo(o2.getSecondName());
            }
        });

        List<Student> local = vizible;

        ListIterator<Student> it = vizible.listIterator();
        int numberOfDuplicates = 0;
        String currentString = vizible.get(0).getSecondName();
        String nextString;
        while(it.hasNext()){
            nextString = it.next().getSecondName();
            if(!currentString.equals(nextString)){
                if(numberOfDuplicates == 1){
                    it.previous();
                    it.previous();
                    it.remove();
                    it.next();
                }numberOfDuplicates = 1;
                currentString = nextString;
            }else{
                numberOfDuplicates++;
            }
        }
        if(numberOfDuplicates == 1){
            it.previous();
            it.remove();
        }

        sort(vizible);
        view.setContent(vizible);
    }


}
