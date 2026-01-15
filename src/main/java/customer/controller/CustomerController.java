package customer.controller;
import customer.entity.CustomerEntity;
import customer.mapper.CustomerMapper;
import customer.repository.CustomerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Path("/customer")
public class CustomerController {
    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerMapper customerMapper;

    @GET
    public List<CustomerEntity> findAll(){
        return customerRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public CustomerEntity findCustomerById(@PathParam("id") Long id){
        return customerRepository.findById(id);
    }

    @POST
    @Transactional
    public CustomerEntity createCustomer(CustomerEntity customerEntity){
        customerRepository.persist(customerEntity);
        return customerEntity;
    }

    @PATCH
    @Transactional
    @Path("/{id}")
    public CustomerEntity updateCustomer(@PathParam("id") Long id, CustomerEntity customerEntity){
        CustomerEntity newCustomerEntity = customerRepository.findById(id);
        customerMapper.updateEntityFromDto(customerEntity, newCustomerEntity);
        customerRepository.persist(newCustomerEntity);
        return newCustomerEntity;
    }


}
