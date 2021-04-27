package Service;

import Model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private List<Employee> employees = new ArrayList<>();


    @Override
    public void save(Employee employee) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee employee1 = new Employee();
            employee1.setIdEmployee(employee.getIdEmployee());
            employee1.setNameEmployee(employee.getNameEmployee());
            employee1.setAvatar(employee.getAvatar());
            employee1.setGender(employee.isGender());
            employee1.setDate(employee.getDate());
            session.saveOrUpdate(employee1);
            transaction.commit();

        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        finally {
            if(session !=null){
                session.close();
            }
        }

    }


    @Override
    public Employee findById(int id) {
        return employees.get(id);
    }

    @Override
    public void update(int id, Employee employee) {
        for (Employee p : employees) {
            if (p.getIdEmployee() == id) {
                p = employee;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdEmployee() == id) {
                employees.remove(i);
                break;
            }
        }
    }

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Employee> findAllDataBase(){
        String queryStr = "SELECT e FROM Employee AS e";
        TypedQuery<Employee> query = entityManager.createQuery(queryStr,Employee.class);
        return query.getResultList();
    }
    @Override
    public Employee findByIdDataBase(Long id){
        String queryStr = "SELECT e FROM Employee AS e WHERE e.idEmployee =:id";
        Employee employee = entityManager.createQuery(queryStr,Employee.class).setParameter("id",id).getSingleResult();
        return employee;
    }

    @Override
    public Employee saveDataBase(Employee employee){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee employee1 = findByIdDataBase( employee.getIdEmployee());
            employee1.setNameEmployee(employee.getNameEmployee());
            employee1.setAvatar(employee.getAvatar());
            employee1.setGender(employee.isGender());
            employee1.setDate(employee.getDate());
            session.saveOrUpdate(employee1);
            transaction.commit();
            return employee1;
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        finally {
            if(session !=null){
                session.close();
            }
        }
        return null;
    }

    public void delete(long id){
        EntityTransaction transaction = entityManager.getTransaction();
        Employee employee = findByIdDataBase(id);
        transaction.begin();
        entityManager.remove(employee);
        transaction.commit();

    }

//    public static void main(String[] args) {
//        EmployeeService employeeService;
//        System.out.println(employeeService.findAllDataBase());
//    }

}
