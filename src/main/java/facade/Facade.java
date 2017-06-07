package facade;

import entity.Employee;
import entity.Person;
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

        //Find student
        fc.findStudent(2);
        
        //find person
        fc.findPerson(2);
        
        //Assign student to supervisor
        fc.assignSupervisor(2, 3);
        
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
    
    public void assignSupervisor(int studentID, int supervisorID){
        em.getTransaction().begin();
        
        Employee emp =  em.find(Employee.class, supervisorID);
        Student std =  em.find(Student.class, studentID);
        
        std.addSupervisor(emp);
        
        em.persist(std);
        
        em.getTransaction().commit();

        System.out.println("Student" + std.getFirstName() + ", has been assigned"
        + " to the supervisor " + emp.getFirstName());
        
        
    } 
    
    

    public void addStudent(String firstName, String lastName, Date birthDate, int age, boolean isMarried, int matNr, Date matDate) {

        em.getTransaction().begin();

        em.persist(new Student(matNr, matDate, firstName, lastName, age, isMarried, birthDate));

        em.getTransaction().commit();

    }
    
    public void findStudent(int id){
        
        Student st = em.find(Student.class, id);
        System.out.println(st.toString() );
        
    }
    
  public void findPerson(int id){
        
        Person ps = em.find(Person.class, id);
        System.out.println(ps.getFirstName() );
        
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
