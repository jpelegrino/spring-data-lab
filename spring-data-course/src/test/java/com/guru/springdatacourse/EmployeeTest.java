package com.guru.springdatacourse;

import com.guru.springdatacourse.entities.Employee;
import com.guru.springdatacourse.entities.Phone;
import com.guru.springdatacourse.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("Karla");
        Phone phone = new Phone();
        phone.setNumber("809-775-2341");

        Phone phone2 = new Phone();
        phone2.setNumber("809-775-2341");

        employee.addPhone(phone);
        employee.addPhone(phone2);
        employeeRepository.save(employee);
    }
}
