import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class StudentRepository {

    public static final Logger LOGGER = LogManager.getLogger(StudentRepository.class);

    //Fields
    private TreeSet<Student> students = new TreeSet<>();

    //Getters

    public TreeSet<Student> getStudents() {
        TreeSet<Student> buffer = new TreeSet<>();
        buffer.addAll(students);
        return buffer;
    }

    //Public methods

    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent(int studentId) throws Exception {
        for (Student student: students) {
            if(student.getId() == studentId){
                students.remove(student);
                break;
            }
            else{
                LOGGER.error("The student cannot be deleted because the id "+studentId+" does not exist.");
                throw new Exception("The student cannot be deleted because the id "+studentId+" does not exist.");
            }
        }
    }

    public ArrayList<Student> withAge(int age) throws Exception {
        if(age <= 0){
            LOGGER.error("Cannot retrieve student repository because the age provided is "+age+". Age cannot be negative");
            throw new Exception("Cannot retrieve student repository because the age provided is "+age+". Age cannot be negative");
        }
        else{
            ArrayList<Student> studentsWithAge = new ArrayList<>();
            for (Student student: students) {
                if(student.getAge() == age){
                    studentsWithAge.add(student);
                }
            }
            return studentsWithAge;
        }
    }

    public ArrayList<Student> orderedByLastName(){
        ArrayList<Student> studentsByLastName = new ArrayList<>(students);
        studentsByLastName.sort(new StudentCompareByLastName());
        return studentsByLastName;
    }

    public ArrayList<Student> orderedByBirthDay(){
        ArrayList<Student> studentsByLastName = new ArrayList<>(students);
        studentsByLastName.sort(new StudentCompareByBirthDate());
        return studentsByLastName;
    }

}
