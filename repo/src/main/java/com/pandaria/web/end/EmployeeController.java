package com.pandaria.web.end;

import com.pandaria.web.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {

    Logger logger = LogManager.getLogger();

    Employee employee = new Employee();

    @ResponseBody
    @GetMapping(value = "/getEmpJson/{name}", produces={"application/xml", "application/json"})
    public Employee getEmployeeInJson(@PathVariable String name) {

        employee.setName(name);
        employee.setEmail("employee1@gmail.com");
        employee.setBirthday(new Date());
        logger.info("Output json type value.");

        return employee;
    }

    @ResponseBody
    @GetMapping(value = "/getEmpXml/{name}", produces = "application/xml")
    public Object getEmployeeInXml(@PathVariable String name) {

        employee.setName(name);
        employee.setEmail("employee2@gmail.com");
        employee.setBirthday(new Date());

        return employee;
    }

    @GetMapping("/go")
    public String redirect2Index() {

        return "success";
    }

}
