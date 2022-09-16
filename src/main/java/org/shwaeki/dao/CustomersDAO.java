package org.shwaeki.dao;

import org.shwaeki.models.Customer;

import java.util.ArrayList;

public interface CustomersDAO {
    boolean isCustomerExists(String email, String password);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerID);

    ArrayList<Customer> gatAllCustomer();

    Customer gatOneCustomer(int customerID);

}
