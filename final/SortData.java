import java.util.*; 
import java.lang.*; 
import java.io.*; 
class SortData implements Comparator<studentData>
{

    public int reversed(studentData a, studentData b)
    {
        return b.name.compareTo(a.name);
    }
    public int compare(studentData a, studentData b){
        return a.age - b.age;
    }
}