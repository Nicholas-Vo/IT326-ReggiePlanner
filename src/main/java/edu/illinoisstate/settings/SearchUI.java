package edu.illinoisstate.settings;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import org.hsqldb.HsqlDateTime;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SearchUI {

    public SearchUI(){

    }
    public ArrayList<Course> allCourses(String Search){
        ArrayList<Course> userCourses =  new ArrayList<Course>();
        Statement state;
        ResultSet result;
        try{
            Connection db = (Connection) Database.getInstance().getCourseList();
            state = db.createStatement();
            String searchQuery = "SELECT from 'Courses' WHERE CONCAT('Course ID', 'Course Name') LIKE'%"+Search+"%'";
            result = state.executeQuery(searchQuery);

            Course course;
            while(result.next())
            {
                course = new Course (result.getString("Course ID"), result.getString("Course Name") );
                userCourses.add(course);
            }
        }catch (Exception no){
            no.printStackTrace();
        }

        return userCourses;
    }



    public void FilterSearch(){
        List<Course> allCourses = new ArrayList<Course>();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Course ID", "Course Name"});
        Object[] row = new Object[2];
        for(int i = 0; i < allCourses.size(); i++)
        {
            row[0] = allCourses.get(i).getCourseID();
            row[1] = allCourses.get(i).getName();
            model.addRow(row);
        }

    }
    public void find(){

    }



}





