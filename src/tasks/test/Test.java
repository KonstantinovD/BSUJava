package tasks.test;

import tasks.test.cars.Car;
import tasks.test.dataextremum.DataExtremum;
import tasks.test.student.Student;
import tasks.test.studentreader.StudentReader;


import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args){


        DataExtremum<Student> cont = new DataExtremum<>();

        StudentReader reader = new StudentReader();
        try {
            reader.connectTo("tasks.lab12.resources\\in.txt");
        }catch (FileNotFoundException fnfex){
            fnfex.printStackTrace();
        }

        while(reader.hasNext()){
            Student sst = reader.getNext();
            System.out.println(sst.getSecondName() + " "
            + sst.getYear() + " " + sst.getAverageMark());
            cont.add(sst);
        }

        System.out.println("//////////");

        cont.sort((o1, o2) -> {return o1.compareTo(o2);});

        for(Student st : cont){
            System.out.println(st.getSecondName() + " " + st.getYear() + " " + st.getAverageMark());
        }


    }
}
