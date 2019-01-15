package tasks.test3.controller;

import tasks.test3.model.Employee;
import tasks.test3.model.Human;
import tasks.test3.model.XMLReader;
import tasks.test3.view.View;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Controller {

    private View view;

    private List<Human> humans;
    private List<Employee> visible;

    public Controller(){
        humans = new ArrayList<>();
        visible = new ArrayList<>();
    }

    public void setView(View v){
        this.view = v;
        initListeners();
    }

    void initListeners(){
        view.getMenuItemOpenFile().addActionListener(e -> {
            try {

                XMLReader reader = new XMLReader();
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    humans.clear();
                    File file = fc.getSelectedFile();
                    if (file != null) {
                        reader.read(file, humans);
                    }
                }
                view.setContent(humans);
            }catch (Exception ex){
                view.sendErrorMessage("ERROR: unable to open file!");
            }
        });

        view.getMenuItemSortByPosition().addActionListener(e -> {
            visible.clear();
            for(Human h : humans){
                visible.add((Employee)h);
            }
            Collections.sort(visible);
            view.setContent(visible);
        });

        view.getMenuItemSortBySalary().addActionListener(e -> {
            visible.clear();
            for(Human h : humans){
                visible.add((Employee)h);
            }
            visible.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return Double.compare(o2.getSalary(), o1.getSalary());
                }
            });
            view.setContent(visible);
        });

        view.getMenuItemShowPositions().addActionListener(e -> {
            TreeSet<Employee.Position> set = new TreeSet<>();
            for(Human h : humans){
                set.add(((Employee)h).getPosition());
            }
            List<Employee.Position> lst = new ArrayList<>(set);
            view.showPos(lst);
        });
    }
}
