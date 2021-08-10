import java.time.LocalDate;

public class Student implements Comparable<Student>{

    //Fields
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private int id;

    //Getters

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getGender() {
        return gender;
    }
    public int getId() {
        return id;
    }

    public int getAge(){
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    //Constructor
    public Student(String firstName, String lastName, LocalDate birthDate, String gender, int id) {

        try {
            setFirstName(firstName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            setLastName(lastName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            setBirthDate(birthDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try{
            setGender(gender);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        this.id = id;
    }

    //Private methods
    private void setBirthDate(LocalDate birthDate) throws Exception {
        if (birthDate == null || birthDate.getYear() < 1900 || birthDate.getYear() > (LocalDate.now().getYear() - 18)) {
            throw new Exception("Data invalida");
        } else {
            this.birthDate = birthDate;
        }
    }

    private void setFirstName(String firstName) throws Exception {
        if (firstName == null) {
            throw new Exception("Prenume invalid");
        } else {
            this.firstName = firstName;
        }
    }

    private void setLastName(String lastName) throws Exception {
        if (lastName == null) {
            throw new Exception("Nume invalid");
        } else {
            this.lastName = lastName;
        }
    }

    private void setGender(String gender) throws Exception {
        switch (gender) {
            case "m", "M", "F", "f" -> this.gender = gender;
            default -> throw new Exception("Sex invalid");
        }
    }

    @Override
    public int compareTo(Student o) {
        return this.id - o.getId();
    }
}
