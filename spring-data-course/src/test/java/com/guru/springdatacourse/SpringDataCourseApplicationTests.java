package com.guru.springdatacourse;

import com.guru.springdatacourse.entities.Address;
import com.guru.springdatacourse.entities.Customer;
import com.guru.springdatacourse.repositories.CustomerRepository;
import static junit.framework.TestCase.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataCourseApplicationTests {

	public static final String THIS_ENTITY_NOT_EXISTS = "This entity not exists";
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSave() {
		Customer customer=new Customer();
		customer.setName("Juan");
		customer.setEmail("jmartinez@gmail.com");

		customerRepository.save(customer);



	}


	@Test
	public void testFindById(){
		Customer customer=customerRepository.findById(1L).get();

		assertNotNull(customer);
		assertEquals("Juan",customer.getName());
	}


	@Test
	public void testUpdate() {
		if(!customerRepository.existsById(1L))
			System.out.println(THIS_ENTITY_NOT_EXISTS);

		Customer customer=customerRepository.findById(1L).get();
		customer.setEmail("jmartinez@itla.edu.do");

		customerRepository.save(customer);

	}


	@Test
	public void testDelete() {
		if (!customerRepository.existsById(1L))
			System.out.println(THIS_ENTITY_NOT_EXISTS);

		Customer customer=customerRepository.findById(1L).get();
		customerRepository.delete(customer);
	}


	@Test
	public void testFindByName() {
		List<Customer> customers=customerRepository.findByNameAndEmail("Pedro","pcordero@gmail.com");
		assertNotNull(customers);

		customers.stream().forEach(c->{
			System.out.println(c.getName());
		});
	}

	@Test
	public void testFindByEmailLike() {
		List<Customer> customers=customerRepository.findByEmailLike("%gmail%");
		assertNotNull(customers);

		customers.stream().forEach(c->{
			System.out.println(c.getName());
		});

	}

	@Test
	public void testFindByIdIn() {
		List<Customer> customers=customerRepository.findByIdIn(Arrays.asList(3L,4L));
		assertNotNull(customers);

		customers.stream().forEach(c->{
			System.out.println(c.getName());
		});

	}

	@Test
	public void testFindAllPagingAndSort() {

		Sort sort=new Sort(Sort.Direction.DESC,"name");
		Pageable pageable=new PageRequest(0,2,sort);

		customerRepository.findAll(pageable).forEach(c-> System.out.println(c.getName()));
	}


	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateCustomerOnRepository() {

		customerRepository.updatedCustomerEmail(2L,"jpelegrino@gmail.com");
		System.out.println("Successful updated..");

	}


	@Test
	@Transactional
	@Rollback(false)
	public void testCreateCustomer() {
		Customer customer=new Customer();
		customer.setName("Josefina");
		customer.setEmail("josefina@gmail.com");

		Customer customer2=new Customer();
		customer2.setName("Karla");
		customer2.setEmail("karla@gmail.com");

		customerRepository.saveAll(Arrays.asList(customer,customer2));
	}


	@Test
	public void testGetAllCustomers() {
		customerRepository.getAllCustomers(new PageRequest(0,5,
				new Sort(Sort.Direction.DESC,"name")))
				.forEach(c-> System.out.println(c.getName()));
	}

	@Test
	public void saveCustomerEmbedable() {
		Customer customer = new Customer();
		customer.setEmail("ema@gmail.com");
		customer.setName("Ema");
		Address address = new Address();
		address.setCity("Santo Domingo");
		address.setStreet("Wenceslao Alvarez");
		address.setZipcode("7845");
		customer.setAddress(address);
		customerRepository.save(customer);
	}


}
