package me.yasiru.udacityapi.POJO;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// to get response
public class UdacityResponse {

    private Course[] courses;
   //return course list
    public ArrayList<Course> getData() {
        return filterCourse(courses);
    }


    // remove course which is not complete details
    ArrayList<Course> filterCourse(Course[] courses){
        ArrayList<Course> courseList =new ArrayList<Course>();
        Log.i("TAG",courses.length +"");
        for (int i=0;i<courses.length;i++){
            Course tempCourse=courses[i];
            List<Course.Instructors> instructors =tempCourse.getInstructors();

            if(instructors.size() >0)
                // check for banner images and instructor's image
                if(!tempCourse.getBanner_image().equals("") &&
                        !instructors.get(0).getImage().equals("")) {
                // get front to courses with more instructors
                    if(instructors.size() >1)
                        courseList.add(0,tempCourse);
                        else
                    courseList.add(tempCourse);
                }
        }

      return courseList;
    }



}
