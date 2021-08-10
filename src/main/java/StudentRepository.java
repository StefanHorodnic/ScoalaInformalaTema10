import java.util.TreeSet;

public class StudentRepository {

    //Fields
    private TreeSet<Student> students = new TreeSet<>();

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
                throw new Exception("Invalid Id");
            }
        }
    }

    public TreeSet<Student> withAge(int age) throws Exception {
        if(age <= 0){
            throw  new Exception("Int age cannot be negative");
        }
        else{
            TreeSet<Student> studentsByAge = new TreeSet<>();
            for (Student student: students) {
                if(student.getAge() == age){
                    studentsByAge.add(student);
                }
            }
            return studentsByAge;
        }
    }

    public TreeSet<Student> orderedByLastName(){
        TreeSet<Student> studentsByLastName = new TreeSet<>(new StudentCompareByLastName());
        studentsByLastName.addAll(students);
        return studentsByLastName;
    }

    public TreeSet<Student> orderedByBirthDay(){
        TreeSet<Student> studentsByAge = new TreeSet<>(new StudentCompareByAge());
        studentsByAge.addAll(students);
        return studentsByAge;
    }

}
