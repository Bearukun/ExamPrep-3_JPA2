package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;


/**
 *
 * @author bear
 */
@Entity
public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = 1L;


    private int soSecNr;
    private float wage;
    private String taxClass;

    public Employee() {
    }

    public Employee(int soSecNr, float wage, String taxClass, String firstName, String lastName, int age, boolean isMarried, Date birthDate) {
        super(firstName, lastName, age, isMarried, birthDate);
        this.soSecNr = soSecNr;
        this.wage = wage;
        this.taxClass = taxClass;
    }


    
    public int getSoSecNr() {
        return soSecNr;
    }

    public void setSoSecNr(int soSecNr) {
        this.soSecNr = soSecNr;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }
    
    


    @Override
    public String toString() {
        return "";
    }
    
}
