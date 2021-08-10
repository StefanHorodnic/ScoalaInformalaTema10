import java.util.Comparator;

public class StudentCompareByBirthDate implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate());
    }
}
