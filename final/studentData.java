import java.util.*; 
import java.lang.*; 
import java.io.*; 
/**
 * This is Assignment4 for CS200
 *
 * @author (Junwen Huang)
 * @version (Nov.18/2020)
 */
public class studentData{
    String name;
    Integer age;
    Double gpa;
    Integer grade;
    
    public studentData(String name, Integer age, Double gpa, 
    Integer grade){
        this.name=name;
        this.age=age;
        this.gpa=gpa;
        this.grade=grade;
    }
    @Override
    public String toString(){
        return name+" "+age+" "+gpa+" "+grade;
    }
     
    
}
