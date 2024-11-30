package velykoivanStanislav;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserName {
    private String firstName;
    private String lastName;
    private String MiddleName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    @Override
    public String toString() {
        return "UserName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                '}';
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }
}
