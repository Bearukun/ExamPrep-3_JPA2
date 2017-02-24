/**
 * Q: Describe how you handled persistence in the last three semesters
 *
 * A: First time I used any sort of persistence was to serialize objects, and save
 * them into a file in the project folder. Later on we were introduced to mySQL and
 * how to persist data onto a database. The data on our 2'nd semester was persisted
 * with the JDBC-driver, and writing the mySQL statements through prepared statements,
 * and then extracting the data from the received resultSet. One of the more recent
 * thing that we have learned is the way that cookies work and how you can save data
 * in them when using web projects.
 *
 * Q: Explain the Pros & Cons in using an ORM (Object Relational Mapping) framework
 *
 * A:
 *  Pros:
 *      ORM typically reduces the amount of code that needs to be written.
 *      Avoids low level JDBC and SQL code.
 *      Provides database and schema independence.
 *      Allows to use the Object Oriented-paradigm.
 *
 *  Cons:
 *      The high level of abstraction can obscure what is actually happening in the implementation code.
 *      Heavy reliance on ORM software has been cited as a major factor in producing poorly designed databases.
 *      Variety of difficulties that arise when considering how to match an object system to a relational database.
 *      Annotations using JPA is hard to memorize.
 *
 * Q: Elaborate on some of the problems ORM tries to solve
 *
 * A: Answered through the Pros and Cons above.
 *
 *
 * Q: Explain how Inheritance in an OO language can be mapped to tables in a relational database
 * 
 * A: You have to extends the classes with with class you want to inherit from. In this assignment
 * student and employee extends the person class. Then make sure you have deleted the ID from the classes
 * that extends. 
 *
 */
package facade;

import entity.Employee;
import entity.Student;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Facade {

    private EntityManagerFactory emf;
    private EntityManager em;

    public static void main(String[] args) {

        Facade fc = new Facade();

        fc.initiateSystem();

        //add a student
        fc.addStudent("Hanna", "Montana", new Date(2017, 1, 25), 24, false, 1, new Date(2018, 4, 24));
        fc.addStudent("John", "Doe", new Date(2017, 1, 25), 24, false, 1, new Date(2018, 4, 24));

        //add an employee 
        fc.addEmployee("John", "Smith", new Date(1984, 1, 22), 12, true, 1221984, 9001, "SuperiorMaster");

        //edit a student, in this case Hanna
        fc.editStudent(1, "Anna", "Montana", new Date(2017 - 1900, 1, 25), 24, false, 1, new Date(2018, 4, 24));
        
        //remove a student 
        fc.deleteStudent(1);

        //Close entityManager
        fc.closeSystem();

    }

    public void initiateSystem() {

        Persistence.generateSchema("pu", null);

        emf = Persistence.createEntityManagerFactory("pu");

        em = emf.createEntityManager();

    }

    public void closeSystem() {

        em.close();

    }

    public void addStudent(String firstName, String lastName, Date birthDate, int age, boolean isMarried, int matNr, Date matDate) {

        em.getTransaction().begin();

        em.persist(new Student(matNr, matDate, firstName, lastName, age, isMarried, birthDate));

        em.getTransaction().commit();

    }

    public void addEmployee(String firstName, String lastName, Date birthDate, int age, boolean isMarried, int soSecNr, float wage, String taxClass) {

        em.getTransaction().begin();

        em.persist(new Employee(soSecNr, wage, taxClass, firstName, lastName, age, isMarried, birthDate));

        em.getTransaction().commit();

    }

    public void editStudent(int idToEdit, String firstName, String lastName, Date birthDate, int age, boolean isMarried, int matNr, Date matDate) {

        em.getTransaction().begin();

        em.find(Student.class, idToEdit).setFirstName(firstName);
        em.find(Student.class, idToEdit).setLastName(lastName);
        em.find(Student.class, idToEdit).setBirthDate(birthDate);
        em.find(Student.class, idToEdit).setAge(age);
        em.find(Student.class, idToEdit).setIsMarried(isMarried);
        em.find(Student.class, idToEdit).setMatNr(matNr);
        em.find(Student.class, idToEdit).setMatDate(matDate);

        em.getTransaction().commit();

    }

    public void deleteStudent(int idToDelete) {

        em.getTransaction().begin();
        
        em.remove(em.find(Student.class, idToDelete));

        em.getTransaction().commit();

    }

}
