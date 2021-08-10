import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentRepositoryTest {

    @Test
    @Description("Should add a student to the studentRepository and check that the size of the repository is one")
    void addOneStudent() {

        StudentRepository studentRepository = new StudentRepository();

        try{
            Student student = new Student("Stefan", "Horodnic", LocalDate.of(1990, 04, 15), "M", 1);
            studentRepository.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals(1, studentRepository.getStudents().size());
        System.out.println("SudentRepository contains the student: " + studentRepository.getStudents().first().toString());
    }
    @Test
    void deletingOneStudent(){

        StudentRepository studentRepository = new StudentRepository();

        try{
            Student student = new Student("Stefan", "Horodnic", LocalDate.of(1990, 04, 15), "M", 1);
            studentRepository.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            studentRepository.deleteStudent(1);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(0, studentRepository.getStudents().size());
        System.out.println("SudentRepository does not contain any student");
    }

    @Test
    void preventCreatingStudentWithEmptyFirstName(){
        StudentRepository studentRepository = new StudentRepository();

        try{
            studentRepository.addStudent(new Student("","Horodnic",LocalDate.of(1990,04,15),"M",1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(0, studentRepository.getStudents().size());
    }

    @Test
    void preventCreatingStudentWithEmptyLastName(){
        StudentRepository studentRepository = new StudentRepository();

        try{
            studentRepository.addStudent(new Student("Stefan","",LocalDate.of(1990,04,15),"M",1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(0, studentRepository.getStudents().size());
    }

    @Test
    @Description("Date of birth has to be between 1900 and current year - 18 ")
    void preventCreatingStudentWithInvalidDateOfBirth(){
        StudentRepository studentRepository = new StudentRepository();

        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.of(1899,04,15),"M",1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.now().minusYears(17),"M",1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(0, studentRepository.getStudents().size());
    }

    @Test
    @Description("Gender should be male or female (or M and F), upper/lower case should both be accepted")
    void preventCreatingStudentWithInvalidGender(){
        StudentRepository studentRepository = new StudentRepository();

        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.of(1990,04,15),"M",1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.of(1990,04,15),"F",2));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.of(1990,04,15),"m",3));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.of(1990,04,15),"f",4));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        //This has to generayte an error because the gender is n;
        try{
            studentRepository.addStudent(new Student("Stefan","Horodnic",LocalDate.of(1990,04,15),"n",4));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(4, studentRepository.getStudents().size());
    }

    @Test
    @Description("When the id of the student is not found in the repository then an exeption is thrown. " +
            "And the size of the repository remains the same")
    void invalidIdWhenDeletingAStudent(){
        StudentRepository studentRepository = new StudentRepository();

        try{
            Student student = new Student("Stefan", "Horodnic", LocalDate.of(1990, 04, 15), "M", 1);
            studentRepository.addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            studentRepository.deleteStudent(0);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(1, studentRepository.getStudents().size());
    }

    @Test
    void retrieveAListWithStudentsOfGivenAge(){
        StudentRepository students = new StudentRepository();

        int age = LocalDate.now().getYear()-1990;

        try {
            students.addStudent(new Student("1", "1", LocalDate.of(LocalDate.now().minusYears(age).getYear(),5,13),"m",1));
            students.addStudent(new Student("2", "2", LocalDate.of(2000,6,7),"f",2));
            students.addStudent(new Student("3", "3", LocalDate.of(LocalDate.now().minusYears(age).getYear(),6,12),"m",3));
            students.addStudent(new Student("4", "4", LocalDate.of(1997,9,11),"f",4));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            int numberOfStudents = students.withAge(age).size();
            assertEquals(2, numberOfStudents);
            System.out.println("Studend repository contains "+numberOfStudents+" students with the age "+age);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void throwExeptionWhenAgeIsNegative() {
        StudentRepository students = new StudentRepository();

        try {
            students.addStudent(new Student("1", "1", LocalDate.of(1990,5,13),"m",1));
            students.addStudent(new Student("2", "2", LocalDate.of(2000,6,7),"f",2));
            students.addStudent(new Student("3", "3", LocalDate.of(1990,6,12),"m",3));
            students.addStudent(new Student("4", "4", LocalDate.of(1997,9,11),"f",4));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            int numberOfStudents = students.withAge(-1).size();
            assertEquals(2, numberOfStudents);
            System.out.println("Studend repository contains "+numberOfStudents+" students with the age -1");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void listStudentsByLastName(){

        StudentRepository students = new StudentRepository();

        try {
            students.addStudent(new Student("1", "b", LocalDate.of(1990,5,13),"m",1));
            students.addStudent(new Student("2", "j", LocalDate.of(2000,6,7),"f",2));
            students.addStudent(new Student("3", "a", LocalDate.of(1990,6,12),"m",3));
            students.addStudent(new Student("4", "a", LocalDate.of(1997,9,11),"f",4));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            StringBuilder lastNames = new StringBuilder();

            for (Student student:students.orderedByLastName()) {
                lastNames.append(student.getLastName());
            }
            assertEquals("aabj", lastNames.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void listStudentsByBirthDay(){

        StudentRepository students = new StudentRepository();

        try {
            students.addStudent(new Student("1", "b", LocalDate.of(1990,5,13),"m",1));
            students.addStudent(new Student("2", "j", LocalDate.of(2000,6,7),"f",2));
            students.addStudent(new Student("3", "a", LocalDate.of(1990,6,12),"m",3));
            students.addStudent(new Student("4", "a", LocalDate.of(1997,9,11),"f",4));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            StringBuilder ids = new StringBuilder();

            for (Student student:students.orderedByBirthDay()) {
                ids.append(student.getId());
            }
            assertEquals("1342", ids.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}