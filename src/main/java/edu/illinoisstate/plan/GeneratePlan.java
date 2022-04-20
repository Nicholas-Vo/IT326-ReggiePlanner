package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GeneratePlan {
    private final Database db = Database.getInstance();
    private final List<Course> completedCourses = new ArrayList<>();
    private final JButton addBtn = new JButton("Add Taken Courses");
    private final JTextField inputField = new JTextField();
    private final List<String> planCourses = new ArrayList<>();
    private final List<String> userCourses = new ArrayList<>();
    public GeneratePlan(UserAccount user) {
        generate();
    }

    private void generate() {
        //Get all courses in the database in a list
        try {
            File classes = new File("courses.txt");
            Scanner c  = new Scanner(classes);
            while(c.hasNextLine()) {
                    String p = c.nextLine();
                    planCourses.add(p);
            }
        }
        catch (FileNotFoundException y) {
            //JOptionPane.showMessageDialog("Error No files found")
        }
        addBtn.addActionListener(e -> {
            String input = inputField.getText();

            if (db.getCourseByID(input) == null) {
                //JOptionPane.showMessageDialog("Error doesn't exist");
                return;
            }

            Course course = db.getCourseByID(input);
            completedCourses.add(course);
            //Add completed Course to a Separate List

            planCourses.remove(course);
            //Remove Completed course from the sample list

            inputField.setText("");
            int credits = 30;
            int count = 0;
            //generate random index for List
            Random rand = new Random();
            //Add Courses to User Plan
            while(count < credits){
                int r = rand.nextInt(planCourses.size());
                userCourses.add(String.valueOf(r));
                count++;
            }
            //Print out User Plan in separate Window
        });
        // Now we have a list of courses the student has taken,
        // use this list to generate a plan
        // ...

    }
}
