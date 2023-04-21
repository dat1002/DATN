//package com.fruits.congtyhoaqua.services.imp;
//
//import com.fruits.congtyhoaqua.dtos.CustomerDTO;
//import com.fruits.congtyhoaqua.exceptions.BadRequestException;
//import com.fruits.congtyhoaqua.exceptions.NotFoundException;
//import com.fruits.congtyhoaqua.models.Customer;
//import com.fruits.congtyhoaqua.repositories.CustomerRepository;
//import com.fruits.congtyhoaqua.services.ICustomerService;
//import com.fruits.congtyhoaqua.utils.Convert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//@Service
//public class CustomerServiceImp implements ICustomerService {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//
//    @Override
//    public Customer createCustomer(CustomerDTO customerDTO) {
//        Customer oldCustomer = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber());
//        if (oldCustomer != null){
//            throw new BadRequestException("Duplicate Customer.");
//        }
//        Customer customer = new Customer();
//        Convert.fromCustomerDTOToCustomer(customerDTO,customer);
//        return customerRepository.save(customer);
//    }
//
//    @Override
//    public Customer editCustomer(Integer idCustomer, CustomerDTO customerDTO) {
//        Optional<Customer> customer = customerRepository.findById(idCustomer);
//        if(customer.isEmpty()){
//            throw new NotFoundException("No Customer");
//        }
//        return customerRepository.save(Convert.fromCustomerDTOToCustomer(customerDTO,customer.get()));
//    }
//
//    @Override
//    public Customer deleteCustomer(Integer idCustomer) {
//        Optional<Customer> customer = customerRepository.findById(idCustomer);
//        if(customer.isEmpty()){
//            throw new NotFoundException("No Customer");
//        }
//        customerRepository.delete(customer.get());
//        return customer.get();
//    }
//
//    @Override
//    public Set<Customer> getAllCustomer() {
//        Set<Customer> customers = new HashSet<>(customerRepository.findAll());
//        if (customers.isEmpty()){
//            throw new NotFoundException("No Customer");
//        }
//        return customers;
//    }
//}
