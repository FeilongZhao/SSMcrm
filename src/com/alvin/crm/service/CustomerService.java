package com.alvin.crm.service;

import com.alvin.crm.pojo.Customer;
import com.alvin.crm.pojo.QueryVo;
import com.alvin.crm.utils.Page;

public interface CustomerService {

    Page<Customer> selectPageByQueryVo(QueryVo queryVo);
    Customer selectCustomerById(Integer id);
    void updateCustomerById(Customer customer);
    void deleteCustomerById(Integer id);
}
