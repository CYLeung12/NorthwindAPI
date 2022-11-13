package com.sparta.sleepint.northwindapi.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "territories")
public class Territory {
    @Id
    @Column(name = "TerritoryID", nullable = false, length = 20)
    private String id;

    @Column(name = "TerritoryDescription", nullable = false, length = 50)
    private String territoryDescription;

//    @ManyToMany
//    @JoinTable(name = "employeeterritories",
//            joinColumns = @JoinColumn(name = "TerritoryID"),
//            inverseJoinColumns = @JoinColumn(name = "EmployeeID"))
//    private Set<Employee> employees = new LinkedHashSet<>();
//
//    public Set<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Set<Employee> employees) {
//        this.employees = employees;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

}
