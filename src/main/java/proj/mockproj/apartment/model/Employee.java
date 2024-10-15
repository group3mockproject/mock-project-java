package proj.mockproj.apartment.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data  // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates a constructor with all arguments
@Builder  // Enables builder pattern
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private Date dob;
    private String position;
    private String email;
    private String phone;
    private String ssn;
    private String status;
    private String password;
    private Boolean delflag;
}
