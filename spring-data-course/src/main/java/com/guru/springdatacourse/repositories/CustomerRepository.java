package com.guru.springdatacourse.repositories;

import com.guru.springdatacourse.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    List<Customer> findByNameAndEmail(String name,String email);
    List<Customer> findByEmailLike(String str);
    List<Customer> findByIdIn(List<Long> list);

    @Modifying
    @Query(value = "UPDATE Customer  SET email=:customerEmail WHERE id=:customerId")
    void updatedCustomerEmail(@Param("customerId") Long id,@Param("customerEmail")String email);

    @Query(value = "FROM Customer")
    List<Customer> getAllCustomers(Pageable pageable);

}
