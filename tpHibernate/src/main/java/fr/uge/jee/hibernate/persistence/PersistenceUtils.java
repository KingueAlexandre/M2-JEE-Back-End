package fr.uge.jee.hibernate.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.security.spec.ECField;
import java.util.function.Consumer;
import java.util.function.Function;

public class PersistenceUtils {

    static final EntityManagerFactory ENTITY_MANAGER_FACTORY
            = Persistence.createEntityManagerFactory("main-persistence-unit");

    public static EntityManagerFactory getEntityManagerFactory(){
        return ENTITY_MANAGER_FACTORY;
    }

    public static void inTransaction(Consumer<EntityManager> consumer){
        inTransaction(entityManager -> {consumer.accept(entityManager);return null;});
    }

    public static <T> T inTransaction(Function<EntityManager,T> action){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        var tx = em.getTransaction();
        T res;
        try {
            tx.begin();
            res = action.apply(em);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            throw e;
        }finally {
            em.close();
        }
        return res;
    }
}