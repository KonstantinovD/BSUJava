package tasks.test.studentreader;

import tasks.test.student.Student;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class StudentReader {
    private final String PATH_TO_FILE = "in.txt";

    private String pathToFile;
    private Scanner reader;

    public StudentReader(){}

    public void connectTo(String pathToFile) throws FileNotFoundException{
        this.pathToFile = pathToFile;
        reader = new Scanner(new File(this.pathToFile));
    }

    public boolean hasNext(){
        if(reader != null) {
            return reader.hasNext();
        }return false;
    }

    public Student getNext(){
        Student student = new Student();
        student.setSecondName(reader.next());
        student.setYear(reader.nextInt());
        student.setAverageMark(reader.nextDouble());
        return student;
    }
}
