package me.yasiru.udacityapi.POJO;


import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {


    private String title;
    private String subtitle;
    private String short_summary;
    private String image;
    private String banner_image;
    private String level;
    private List<Instructors> instructors;


    public List<Instructors> getInstructors() {
        return instructors;
    }

    public String getBanner_image() {
        return reSizeImage(banner_image);
    }


    public String getTitle() {
        return title;
    }


    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return reSizeImage(image);
    }

    public String getLevel() {
        return level;
    }

    public String getShort_summary() {
        return short_summary;
    }


    // default image size is too large to display
    public String reSizeImage(String url){
        if (url.equals(""))
             return url;
        String[] separated = url.split("=");
       return separated[0] + "=s500";
    }


    public class Instructors implements Serializable
    {

        private String image;
        private String name;
        private String bio;

        public String getBio() {
            return bio;
        }

        public String getImage() {
            return (new Course()).reSizeImage(image);
        }

        public String getName() {
            return name;
        }
    }

    }
