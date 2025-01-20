package fr.uge.jee.hibernate.employees;

import jakarta.persistence.*;


@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name="EMPLOYEEID")
    private long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "SALARY")
    private int salary;
    public Employee(String firstName, String lastName, int salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "["+id+"] "+firstName+" "+ lastName+": "+ salary;
    }
}
