package com.sparta.sleepint.northwindapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sleepint.northwindapi.entity.Employee;
import com.sparta.sleepint.northwindapi.entity.Territory;
import com.sparta.sleepint.northwindapi.repositories.EmployeeRepository;
import com.sparta.sleepint.northwindapi.repositories.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.cert.CertPath;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TerritoryController {
    private final TerritoryRepository territoryRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TerritoryController(TerritoryRepository territoryRepository, EmployeeRepository employeeRepository) {
        this.territoryRepository = territoryRepository;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/territory/employees/{territoryId}")
    public Set<Employee> getEmployeesByTerritoryId(@PathVariable String territoryId){
        List<Employee> employees = employeeRepository.findAll();
        Set<Employee> employeeSet = new HashSet<>();

        for (Employee employee : employees){
            Set<Territory> territoriesResponsible = employee.getTerritories();
            for (Territory territory : territoriesResponsible){
                if ((territory.getId().equals(territoryId))) {
                    employeeSet.add(employee);
                    break;
                }
            }
        }
        return employeeSet;
    }

    @GetMapping("/territory/{territoryId}")
    public EntityModel<Territory> getTerritoryById(@PathVariable String territoryId) {
        Territory territory = territoryRepository.findById(territoryId).get();
        EntityModel <Territory> entityModel = EntityModel.of(territory);
        //Connect employees to territory
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getEmployeesByTerritoryId(territoryId));
        entityModel.add(webMvcLinkBuilder.withRel("employees"));
        return entityModel;
    }


    // Collection is used to create a wrapper for a collection of entities
    @GetMapping("/territory/all")
    public CollectionModel<EntityModel<Territory>> getAllTerritories() {
        List<Territory> territories = territoryRepository.findAll();
        List<EntityModel<Territory>> result = new ArrayList<>();

        for(Territory territory : territories) {
            WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getEmployeesByTerritoryId(territory.getId()));
            Link link = webMvcLinkBuilder.withRel("employees");
            EntityModel<Territory> entityModel = EntityModel.of(territory);
            entityModel.add(link);
            result.add(entityModel);
        }
        CollectionModel<EntityModel<Territory>> collectionModel = CollectionModel.of(result);
        return collectionModel;
    }

//// Return a List of EntityModel<Territory>
//    @GetMapping("/territory/all")
//    public List<EntityModel<Territory>> getAllTerritories() {
//        List<Territory> territories = territoryRepository.findAll();
//        //CollectionModel<Territory> collectionModel = null;
//        List<EntityModel<Territory>> result = new ArrayList<>();
//
//
//        for(Territory territory : territories) {
//            WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getEmployeesByTerritoryId(territory.getId()));
//            Link link = webMvcLinkBuilder.withRel("employees");
//
//            EntityModel<Territory> entityModel = EntityModel.of(territory);
//            entityModel.add(link);
//            result.add(entityModel);
//
//        }
//        return result;
//    }



}
