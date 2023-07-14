package com.antra.rewards.cache;

import com.antra.rewards.ApplicationContextProvider;
import com.antra.rewards.entity.Customer;
import com.antra.rewards.repository.CustomerRepository;
import org.apache.shiro.SecurityUtils;

public class CustomerThreadLocal {
    private static ThreadLocal<Customer> customerThreadLocal = new ThreadLocal<>();

    public static Customer get() {
        if (customerThreadLocal.get() == null) {
            String customerName = SecurityUtils.getSubject().getPrincipal().toString();
            CustomerRepository customerRepository = (CustomerRepository) ApplicationContextProvider.getBean("customerRepository");
            Customer customer = customerRepository.findByLoginName(customerName);
            customerThreadLocal.set(customer);
        }
        return customerThreadLocal.get();
    }
}
