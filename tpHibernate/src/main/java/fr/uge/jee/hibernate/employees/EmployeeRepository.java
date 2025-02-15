package fr.uge.jee.hibernate.employees;

import fr.uge.jee.hibernate.persistence.PersistenceUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.*;
import java.util.function.Function;

public class EmployeeRepository {

    private final EntityManagerFactory entityManagerFactory = PersistenceUtils.getEntityManagerFactory();


    /**
     * Create an employee with the given information
     * @param firstName
     * @param lastName
     * @param salary
     * @return the id of the newly created employee
     */

    public long create(String firstName, String lastName, int salary) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        var tx = em.getTransaction();
//        try{
//            tx.begin();
//            var employee = new Employee(firstName,lastName,salary);
//            em.persist(employee);
//            tx.commit();
//            return employee.getId();
//        } catch (Exception e){
//            tx.rollback();
//            throw e;
//        } finally {
//            em.close();
//        }
        Function<EntityManager, Long> function = entityManager -> {
            var employee = new Employee(firstName, lastName, salary);
            entityManager.persist(employee);
            return employee.getId();
        };
        return PersistenceUtils.inTransaction(function);
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        var tx = em.getTransaction();
//        try{
//            tx.begin();
//            var employee = em.find(Employee.class,id);
//            em.remove(employee);
//            tx.commit();
//            return true;
//        } catch (Exception e){
//            tx.rollback();
//            return false;
//        } finally {
//            em.close();
//        }
        Function<EntityManager, Boolean> function = entityManager -> {
            var employee = entityManager.find(Employee.class,id);
            entityManager.remove(employee);
            var employeeTest = entityManager.find(Employee.class,id);
            return employeeTest == null;
        };
        return PersistenceUtils.inTransaction(function);
    }

    /**
     * Update the salary of the employee with the given id
     * @param id
     * @param salary
     * @return true if the removal was successful
     */

    public boolean update(long id, int salary) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        var tx = em.getTransaction();
//        try{
//            tx.begin();
//            var employee = em.find(Employee.class,id);
//            employee.setSalary(salary);
//            var newEmployee = em.merge(employee);
//            tx.commit();
//            return newEmployee != null;
//        } catch (Exception e){
//            tx.rollback();
//            return false;
//        } finally {
//            em.close();
//        }
        Function<EntityManager, Boolean> function = em -> {
            var employee = em.find(Employee.class, id);
            employee.setSalary(salary);
            var newEmployee = em.merge(employee);
            return newEmployee != null;
        };
        return PersistenceUtils.inTransaction(function);
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Employee> get(long id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        var tx = em.getTransaction();
//        try{
//            tx.begin();
//            var employee = em.find(Employee.class,id);
//            if (employee!=null){
//                System.out.println(employee); // print student with id 1
//            }else {
//                return Optional.empty();
//            }
//            tx.commit();
//            return Optional.of(employee);
//        } catch (Exception e){
//            tx.rollback();
//            throw e;
//        } finally {
//            em.close();
//        }
        Function<EntityManager, Optional<Employee>> fun = (em) -> {
            var employee = em.find(Employee.class, id);
            if (employee != null) {
                System.out.println(employee); // print student with id 1
                return Optional.of(employee);
            }
            return Optional.empty();
        };
        return PersistenceUtils.inTransaction(fun);
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<Employee> getAll() {
//        return mapEmployees.values().stream().toList();
        var em = entityManagerFactory.createEntityManager();
        var q = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = em.createQuery(q,Employee.class);
        return query.getResultList();
    }

    public List<Employee> getAllByFirstName(String firstName){
        var em = entityManagerFactory.createEntityManager();
        var q = "SELECT e FROM Employee e where e.firstName = :firstName";
        TypedQuery<Employee> query = em.createQuery(q,Employee.class).setParameter("firstName", firstName);
        return query.getResultList();
    }

}