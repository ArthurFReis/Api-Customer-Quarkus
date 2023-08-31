package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import entity.Customer;
import jakarta.ws.rs.core.Response;
import repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;


    public List<Customer> findAllCustomers(){
        return customerRepository.findAll().list();
    }

    public void addCustomer(Customer customer){
        customerRepository.persist(customer);
    }
    public Response updateCustomer(Customer customer, Long id) {

        Customer customerObj = customerRepository.findById(id);


        if(customerObj != null){
            customerObj.setName(customer.getName());
            customerObj.setLastName(customer.getLastName());
            customerObj.setAge(customer.getAge());
            customerObj.setEmail(customer.getEmail());

        } else{
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.ACCEPTED).build();

    }
    public Response deleteCustomer(Customer customer, long id){

        Customer customerObj = customerRepository.findById(id);

        if(customerObj != null){
            customerRepository.delete(customerObj);

        } else{
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.ACCEPTED).build();

    }
}
