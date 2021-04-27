package Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEmployee;
    private String nameEmployee;
    private String date;
    private String avatar;
    private boolean gender;

    public Employee() {
    }

    public Employee(long idEmployee, String nameEmployee, String date, String avatar, boolean gender) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.date = date;
        this.avatar = avatar;
        this.gender = gender;
    }

    public long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
