package com.isep.testjpa.controller;

import com.isep.testjpa.model.Emp;
import com.isep.testjpa.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SimpleController {
    @Autowired
    private EmpRepository empRepository;
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@Param("name") String name) {
        return "Hello " + name;
    }
    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }
    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }
    @RequestMapping(value="/employees/{id}",method= RequestMethod.GET)
    public Emp getbyID(@PathVariable Long id) {
        return empRepository.findById(id).orElse(null);
    }
    @PutMapping(value="/employees/{id}")
    public Emp update(@PathVariable Long id,@RequestBody Emp detailEmploye) {
        Emp employe = getbyID(id);
        if(employe!=null) {
            employe.setEfirst(detailEmploye.getEfirst());
            employe.setEname(detailEmploye.getEname());
            employe.setSal(detailEmploye.getSal());
            employe.setJob(detailEmploye.getJob());
            employe.setMgr(detailEmploye.getMgr());
            return empRepository.save(employe);
        }
        detailEmploye.setEmpno(id);
        return addEmployee(detailEmploye);

    }
    @DeleteMapping(value="/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        empRepository.deleteById(id);
    }
}


