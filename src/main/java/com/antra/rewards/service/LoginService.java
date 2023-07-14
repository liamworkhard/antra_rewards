package com.antra.rewards.service;

import com.antra.rewards.entity.Customer;

import java.util.List;
import java.util.Map;

public interface LoginService {
    Customer findCustomer(String customerName);
    List<Map<String, Object>> getCustomerPermission(String customerName);
}
