package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private HashMap<String, Integer> courses = new HashMap<>();

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        float sum = 0;
        int num = 0;
        for (Integer grade :
                courses.values()) {
            sum += grade.intValue();
            num += 1;
        }
        if (num != 0)
            return sum/num;
        return 0;
    }

    public String getCourses() {
        String output = "";
        for (String courseNo :
                courses.keySet()) {
            output = output + courseNo + ", ";
        }
        if (output.equals(""))
            return "-";
        return output;
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }

    public void addCourse(String courseName, int grade) {
        this.courses.put(courseName, grade);
    }
}
