import java.util.*; 
import java.lang.*; 
import java.io.*; 
/**
 * This is Assignment4 for CS200
 *
 * @author (Junwen Huang)
 * @version (Nov.18/2020)
 */
public class teacherData{
    String name;
    Integer age;
    Integer salary;
    String subject;
    
    public teacherData(String name, Integer age, Integer salary, 
    String subject){
        this.name=name;
        this.age=age;
        this.salary=salary;
        this.subject=subject;
    }
    @Override
    public String toString(){
        return name+" "+age+" "+salary+" "+subject;
    }
    
}
