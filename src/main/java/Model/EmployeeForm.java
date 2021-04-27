package Model;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeForm {
    private long idEmployee;
    private String nameEmployee;
    private String date;
    private MultipartFile avatar;
    private boolean gender;

    public EmployeeForm() {
    }

    public EmployeeForm(long idEmployee, String nameEmployee, String date, MultipartFile avatar, boolean gender) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.date = date;
        this.avatar = avatar;
        this.gender = gender;
    }
    public EmployeeForm(long idEmployee, String nameEmployee, String date,  boolean gender) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.date = date;
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
