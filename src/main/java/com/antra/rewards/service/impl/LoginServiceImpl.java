package com.antra.rewards.service.impl;

import com.antra.rewards.entity.Customer;
import com.antra.rewards.repository.CustomerRepository;
import com.antra.rewards.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer findCustomer(String loginName) {
        return customerRepository.findByLoginName(loginName);
    }

    @Override
    public List<Map<String, Object>> getCustomerPermission(String customerName) {
        return new ArrayList<>();
    }
}
