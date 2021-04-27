package Controller;

import Service.EmployeeService;
import Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import Model.Employee;
import Model.EmployeeForm;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
//@RequestMapping("/employee")

public class EmployeeController {
//    private final IEmployeeService employeeService = new EmployeeService();
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public ModelAndView index() {
        List<Employee> listEmployee = employeeService.findAllDataBase();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("listEmployee", listEmployee);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getFormEdit(@PathVariable Long id) {
        Employee employee = employeeService.findByIdDataBase(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("editForm", employee);
        return modelAndView;
    }


    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute EmployeeForm employeeForm) {
//        if (employee.getNameEmployee() == null || employee.getNameEmployee().trim().equals("") && employee.getAvatar().trim().equals("")
//                && employee.getDate().trim().equals("")) {
//            model.addAttribute("status", "invalid  try again");
//            return "/edit";
//        }

        String fileName;
        if (employeeForm.getAvatar().getSize() != 0){
        MultipartFile multipartFile = employeeForm.getAvatar();
        fileName = multipartFile.getOriginalFilename(); }
        else {
            EmployeeService employeeService = new EmployeeService();
            fileName = employeeService.findByIdDataBase(employeeForm.getIdEmployee()).getAvatar();
        }
        Employee employee1 = new Employee(employeeForm.getIdEmployee(), employeeForm.getNameEmployee(),
                employeeForm.getDate(), fileName, employeeForm.isGender());
        employeeService.saveDataBase(employee1);
        return "redirect:/";
    }

    @PostMapping("/save")
    public ModelAndView saveEmployee(@ModelAttribute EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Employee employee = new Employee(employeeForm.getIdEmployee(), employeeForm.getNameEmployee(),
                employeeForm.getDate(),fileName,employeeForm.isGender());
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employeeForm", employeeForm);
        modelAndView.addObject("message", "Created new employee successfully !");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return "redirect:/";
    }

//    @GetMapping("/listofdatabase")
//    public ModelAndView listEmployee() {
//        List<Employee> employeeList = employeeService.findAllDataBase();
//        ModelAndView modelAndView = new ModelAndView("/listofdatabase");
//        modelAndView.addObject("employeeList", employeeList);
//        return modelAndView;
//    }
//
//    @GetMapping("/editofdatabase/{id}")
//    public ModelAndView getFormEdit(@PathVariable Long id) {
//        Employee employee = employeeService.findByIdDataBase(id);
//        ModelAndView modelAndView = new ModelAndView("/editofdatabase");
//        modelAndView.addObject("editForm", employee);
//        return modelAndView;
//    }
//
//    @PostMapping("/editofdatabase")
//    public String editEmployee(@ModelAttribute("editForm") Employee employee, Model model) {
//        if (employee.getNameEmployee() == null || employee.getNameEmployee().trim().equals("") && employee.getAvatar().trim().equals("")
//                && employee.getDate().trim().equals("")) {
//            model.addAttribute("status", "invalid  try again");
//            return "/editofdatabase";
//        }
//
//        employeeService.save(employee);
//        return "redirect:/listofdatabase";
//    }
}
