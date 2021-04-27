package Service;

import Model.Employee;

import java.util.List;

public interface IEmployeeService {

    void save(Employee employee);

    Employee findById(int idEmployee);

    void update(int idEmployee, Employee employee);

    void remove(int idEmployee);

     List<Employee> findAllDataBase();

    Employee findByIdDataBase(Long id);

     Employee saveDataBase(Employee employee);
}
