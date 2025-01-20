package fr.uge.jee;

import fr.uge.jee.hibernate.employees.Employee;
import fr.uge.jee.hibernate.employees.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import fr.uge.jee.hibernate.persistence.*;


public class Application {
    public static void main(String[] args) {
        EmployeeRepository repo = new EmployeeRepository();
        var bobM = repo.create("Bob","Moran",500);
        var bobD = repo.create("Bob","Dylan",600);
        var lisa = repo.create("Lisa","Simpson",100);
        var marge = repo.create("Marge","Simpson",1000);
        var homer = repo.create("Homer","Simpson",450);

        repo.delete(lisa);
        repo.update(homer,repo.get(homer).get().getSalary()+100);
        System.out.println(repo.getAll());

    }
}
