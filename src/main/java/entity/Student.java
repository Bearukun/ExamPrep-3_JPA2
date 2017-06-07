package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author bear
 */
@Entity
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private int matNr;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date matDate;

    public Student() {
    }

    public Student(int matNr, Date matDate, String firstName, String lastName, int age, boolean isMarried, Date birthDate) {
        
        super(firstName, lastName, age, isMarried, birthDate);
        this.matNr = matNr;
        this.matDate = matDate;
        
    }

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public Date getMatDate() {
        return matDate;
    }

    public void setMatDate(Date matDate) {
        this.matDate = matDate;
    }

    @Override
    public String toString() {
        return "Student{" + "matNr=" + matNr + ", matDate=" + matDate + '}';
    }

    

}
