package hello.com.xeigna.ms.demo.model;

import com.intellij.openapi.vcs.checkin.StepIntersection;
import org.springframework.data.annotation.Id;

public class Person {

    @Id private String id;

    private String firstName;
    private String lastName;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

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
}