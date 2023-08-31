package controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import entity.Customer;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestPath;
import service.CustomerService;

import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    public List<Customer> retrieveCustomers(){
        List<Customer> customers = new ArrayList<>();
        try{
            customers = customerService.findAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @POST
    @Transactional
    public void addCustomer(Customer customer){
        customerService.addCustomer(customer);
        Response.ok().status(201);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, Customer customer){
        return customerService.updateCustomer(customer, id);

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteCustomer(@PathParam("id") Long id,Customer customer){

         customerService.deleteCustomer(customer, id);
    }

}
