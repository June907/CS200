/**
 * This is Final Project for CS200
 *This program will simulate database management for a high school system. The user will have the option to search and sort for the student’s information 
 *(name, age, GPA, and grade), the teacher’s information (name, age, salary, and subjects). They will also be able to read and add to the CSV file.
 *(student.csv/teacher.csv) The user will also be able to find the teacher’s average and standard deviation of the salaries and ages and 
 *the student’s average and standard deviation of the ages and grades. 
 *Sound-effects is implemented as the user do a certain task in the program.
 *Techniques used:
 *Input/Output with files, Inheritance, user-defined methods, Exception Handling, Generics,
 *Collectiond(ArrayList), searching and sorting
 * 
 * @author (Junwen Huang)
 * @author (Touchsada Jan-On)
 * @version (Dec.2/2020)
 */
import java.util.*; 
import java.lang.*; 
import java.io.*; 

public class studentData{
    //creating variables
    String name;
    Integer age;
    Double gpa;
    Integer grade;
    //contructor
    public studentData(String name, Integer age, Double gpa, 
    Integer grade){
        this.name=name;
        this.age=age;
        this.gpa=gpa;
        this.grade=grade;
    }
    //ToString method
    @Override
    public String toString(){
        return name+" "+age+" "+gpa+" "+grade;
    }
     
    
}
