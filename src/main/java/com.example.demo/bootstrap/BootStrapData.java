package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Division defaultDivision = new Division();
        defaultDivision.setDivision_name("Alaska");
        defaultDivision.setCountry_id(1L);
        divisionRepository.save(defaultDivision);


        Customer customer1 = new Customer();
        customer1.setFirstName("Stephen");
        customer1.setLastName("Grant");
        customer1.setAddress("100 ABC");
        customer1.setPostal_code("88011");
        customer1.setPhone("0192102912");
        customer1.setDivision(defaultDivision);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Foster");
        customer2.setLastName("Adams");
        customer2.setAddress("1231 AC");
        customer2.setPostal_code("70001");
        customer2.setPhone("291831121");
        customer2.setDivision(defaultDivision);
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Henry");
        customer3.setLastName("Corrales");
        customer3.setAddress("131 IAC");
        customer3.setPostal_code("812012");
        customer3.setPhone("891278192");
        customer3.setDivision(defaultDivision);
        customerRepository.save(customer3);

        Customer customer4 = new Customer();
        customer4.setFirstName("Abdul");
        customer4.setLastName("Wahab");
        customer4.setAddress("9182 AVH AVE");
        customer4.setPostal_code("90001");
        customer4.setPhone("981329121");
        customer4.setDivision(defaultDivision);
        customerRepository.save(customer4);

        Customer customer5 = new Customer();
        customer5.setFirstName("Birdie");
        customer5.setLastName("Gold");
        customer5.setAddress("9812 Ave");
        customer5.setPostal_code("98102");
        customer5.setPhone("917281981");
        customer5.setDivision(defaultDivision);
        customerRepository.save(customer5);



    }
}
