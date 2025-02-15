package fr.uge.jee;

import fr.uge.jee.hibernate.employees.Employee;
import fr.uge.jee.hibernate.employees.EmployeeRepository;
import fr.uge.jee.hibernate.students.StudentRepository;
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
        var homerOp = repo.get(homer);
        if(homerOp.isPresent()) {
            repo.update(homer,homerOp.get().getSalary()+100);
        }
        System.out.println(repo.getAll());
        System.out.println(repo.getAllByFirstName("Bob"));


        StudentRepository studentRepo = new StudentRepository();
        var alex = studentRepo.create("Alexandre", "Kingué");
        var yass = studentRepo.create("Yassine", "Ben");

        var uge = studentRepo.createUniversity("Uge");
        var dp = studentRepo.createLecture("Design Patern");

        studentRepo.updateAddressOfStudent(alex,"9 rue des fermes", "Magny-le-Hongr","France");
        studentRepo.updateUniversityOfStudent(alex, uge);


    }
}
