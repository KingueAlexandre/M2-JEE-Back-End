package fr.uge.jee.hibernate.students;

import fr.uge.jee.hibernate.employees.Employee;
import fr.uge.jee.hibernate.persistence.PersistenceUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.function.Function;

public class StudentRepository {

    private final EntityManagerFactory entityManagerFactory = PersistenceUtils.getEntityManagerFactory();

    public long create(String firstName, String lastName) {
        Function<EntityManager, Long> function = entityManager -> {
            var student = new Student(firstName, lastName);
            entityManager.persist(student);
            return student.getId();
        };
        return PersistenceUtils.inTransaction(function);
    }

    public long createUniversity(String name){
        Function<EntityManager, Long> function = entityManager -> {
            var university = new University(name);
            entityManager.persist(university);
            return university.getId();
        };
        return PersistenceUtils.inTransaction(function);
    }

    public boolean deleteUniversity(long id){
        Function<EntityManager, Boolean> function = entityManager -> {
            var university = entityManager.find(University.class, id);
            entityManager.remove(university);
            var universityTest = entityManager.find(University.class, id);
            return universityTest == null;
        };
        return PersistenceUtils.inTransaction(function);
    }


    public long createLecture(String name){
        Function<EntityManager, Long> function = entityManager -> {
            var lecture = new Lecture(name);
            entityManager.persist(lecture);
            return lecture.getId();
        };
        return PersistenceUtils.inTransaction(function);
    }

    public boolean deleteLecture(long id){
        Function<EntityManager, Boolean> function = entityManager -> {
            var lecture = entityManager.find(Lecture.class, id);
            entityManager.remove(lecture);
            var lectureTest = entityManager.find(University.class, id);
            return lectureTest == null;
        };
        return PersistenceUtils.inTransaction(function);
    }

    public boolean updateAddressOfStudent(long id, String street, String city, String state){
        Function<EntityManager, Boolean> function = entityManager -> {
            var student = entityManager.find(Student.class, id);
            student.setAddress(new Address(street, city, state));
            var newStudent = entityManager.merge(student);
            return newStudent != null;
        };
        return PersistenceUtils.inTransaction(function);
    }


    public boolean updateUniversityOfStudent(long id, long idUniversity){
        Function<EntityManager, Boolean> function = entityManager -> {
            var student = entityManager.find(Student.class, id);
            student.setUniversity(entityManager.find(University.class, idUniversity));
            var newStudent = entityManager.merge(student);
            return newStudent != null;
        };
        return PersistenceUtils.inTransaction(function);
    }

    public long addComment(long id, String com){
        Function<EntityManager, Long> function = entityManager -> {
            var student = entityManager.find(Student.class, id);
            var comment = new Comment(com);
            var lstComment = student.getComments();
            lstComment.add(comment);
            student.setComments(lstComment);
            var newStudent = entityManager.merge(student);
            return comment.getId();
        };
        return PersistenceUtils.inTransaction(function);
    }

    public boolean deleteComment(long id){
        Function<EntityManager, Boolean> function = entityManager -> {
            var comment = entityManager.find(Comment.class, id);
            entityManager.remove(comment);
            var commentTest = entityManager.find(Comment.class, id);
            return commentTest != null;
        };
        return PersistenceUtils.inTransaction(function);
    }

}
