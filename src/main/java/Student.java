import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Student implements Comparable<Student> {

    public static final Logger LOGGER = LogManager.getLogger(Student.class);

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

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    //Constructor

    public Student(String firstName, String lastName, LocalDate birthDate, String gender, int id) throws Exception {

        try {
            setFirstName(firstName);
            setLastName(lastName);
            setBirthDate(birthDate);
            setGender(gender);
        } catch (Exception e) {
            LOGGER.error("The student could not be created because: " + e.getMessage());
            throw new Exception("The student could not be created because: " + e.getMessage());
        }

        this.id = id;
    }

    //Private methods

    private void setBirthDate(LocalDate birthDate) throws Exception {
        if(birthDate == null){
            LOGGER.error("Birthdate cannot be null");
            throw new Exception("Birthdate cannot be null");
        }
        else if (birthDate.getYear() < 1900) {
            LOGGER.error("The year of birth date is "+birthDate.getYear()+". Birthdate year cannot be before the year 1900");
            throw new Exception("The year of birth date is "+birthDate.getYear()+". Birthdate year cannot be before the year 1900");
        } else if (birthDate.getYear() > (LocalDate.now().getYear() - 18)) {
            LOGGER.error("The year of birth date is "+birthDate.getYear()+". Birthdate year cannot be after the year " + (LocalDate.now().getYear() - 18));
            throw new Exception("The year of birth date is "+birthDate.getYear()+". Birthdate year cannot be after the year " + (LocalDate.now().getYear() - 18));
        } else {
            this.birthDate = birthDate;
        }
    }

    private void setFirstName(String firstName) throws Exception {
        if (firstName == null || firstName.equals("")) {
            LOGGER.error("FirstName is " + firstName + ". FirstName should not be empty or null");
            throw new Exception("FirstName is " + firstName + ". FirstName should not be empty or null");
        } else {
            this.firstName = firstName;
        }
    }

    private void setLastName(String lastName) throws Exception {
        if (lastName == null || lastName.equals("")) {
            LOGGER.error("LastName is " + lastName + ". LastName should not be empty or null");
            throw new Exception("LastName is " + lastName + ". LastName should not be empty or null");
        } else {
            this.lastName = lastName;
        }
    }

    private void setGender(String gender) throws Exception {
        switch (gender) {
            case "m" -> this.gender = gender;
            case "M" -> this.gender = gender;
            case "F" -> this.gender = gender;
            case "f" -> this.gender = gender;
            default -> {
                LOGGER.error("LastName is " + lastName + ". LastName should not be empty or null");
                throw new Exception("Gender is " + gender + ". Gender has to be M(m) or F(f)");
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.id - o.getId();
    }
}
