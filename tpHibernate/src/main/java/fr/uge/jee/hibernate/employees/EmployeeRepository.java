package fr.uge.jee.hibernate.employees;

import fr.uge.jee.hibernate.persistence.PersistenceUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class EmployeeRepository {

    private final EntityManagerFactory entityManagerFactory = PersistenceUtils.getEntityManagerFactory();
    private final Map<Long,Employee> mapEmployees = new HashMap<>();


    /**
     * Create an employee with the given information
     * @param firstName
     * @param lastName
     * @param salary
     * @return the id of the newly created employee
     */

    public long create(String firstName, String lastName, int salary) {
        EntityManager em = entityManagerFactory.createEntityManager();
        var tx = em.getTransaction();
        try{
            tx.begin();
            var employee = new Employee(firstName,lastName,salary);
            mapEmployees.put(employee.getId(),employee);
            em.persist(employee);
            tx.commit();
            return employee.getId();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        var tx = em.getTransaction();
        try{
            tx.begin();
            em.remove(mapEmployees.get(id));
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Update the salary of the employee with the given id
     * @param id
     * @param salary
     * @return true if the removal was successful
     */

    public boolean update(long id, int salary) {
        EntityManager em = entityManagerFactory.createEntityManager();
        var tx = em.getTransaction();
        try{
            tx.begin();
            var newEmployee = em.merge(mapEmployees.get(id));
            tx.commit();
            if(newEmployee!=null){
                return true;
            }
            return false;
        } catch (Exception e){
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Employee> get(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        var tx = em.getTransaction();
        try{
            tx.begin();
            var employee = em.find(Employee.class,id);
            if (employee!=null){
                System.out.println(employee); // print student with id 1
            }else {
                return Optional.empty();
            }
            tx.commit();
            return Optional.of(employee);
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<Employee> getAll() {
//        return mapEmployees.values().stream().toList();
        var em = entityManagerFactory.createEntityManager();
        var q = "SELECT s FROM Employee ";
        TypedQuery<Employee> query = em.createQuery(q,Employee.class);
        return query.getResultList();
    }

}