package anec.vip.springbootdemo01.controller;

import anec.vip.springbootdemo01.dao.DepartmentDao;
import anec.vip.springbootdemo01.dao.EmployeeDao;
import anec.vip.springbootdemo01.entities.Department;
import anec.vip.springbootdemo01.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class userlist {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao  departmentDao;

    @GetMapping("/emps")
    public String userlist(Model m){
        Collection<Employee> ulist = employeeDao.getAll();
        m.addAttribute("ulist",ulist);
        return "list";
    }

    @GetMapping("/emp")
    public String useraddshow(Model m){
        Collection<Department> dm = departmentDao.getDepartments();
        m.addAttribute("dm",dm);
        return "add";
    }

    @PostMapping("/emp")
    public String useradd(Employee emp){
        System.out.println(emp);
        employeeDao.save(emp);
        // redirect 表示重定向到一个地址
        // forward 表示转发到一个地址
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String userupdatashow(@PathVariable("id") int id, Model m){
        Employee emp = employeeDao.get(id);
        Collection<Department> dm = departmentDao.getDepartments();
        m.addAttribute("dm",dm);
        m.addAttribute("emp",emp);
        return "add";
    }

    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String userupdata(Employee emp){
        employeeDao.save(emp);
        return "redirect:/emps";
    }

    @DeleteMapping(value = "/emp/{id}")
    public String userdelete(@PathVariable("id") int id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}
