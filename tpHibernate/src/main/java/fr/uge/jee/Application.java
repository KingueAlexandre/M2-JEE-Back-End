package fr.uge.jee;

import fr.uge.jee.hibernate.employees.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = PersistenceUtils.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        var tx = em.getTransaction();
        try{
            tx.begin();
            var harry = new Employee("Harry","Potter",1000);
            em.persist(harry);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
