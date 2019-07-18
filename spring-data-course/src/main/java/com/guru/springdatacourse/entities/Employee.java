package com.guru.springdatacourse.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private Set<Phone> phones;


    public void addPhone(Phone phone) {
        if(Objects.isNull(phones))
            phones=new HashSet<>();
        phone.setEmployee(this);
        phones.add(phone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }
}
