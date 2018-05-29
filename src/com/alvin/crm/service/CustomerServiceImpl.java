package com.alvin.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvin.crm.mapper.CustomerDao;
import com.alvin.crm.pojo.Customer;
import com.alvin.crm.pojo.QueryVo;
import com.alvin.crm.utils.Page;

/***
 * 客户管理
 * 
 * @author Alvin
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    
    public Page<Customer> selectPageByQueryVo(QueryVo queryVo) {
        
        Page<Customer> page = new Page<Customer>();
        page.setSize(5);
        queryVo.setSize(5);
        if (queryVo != null) {
            if (queryVo.getPage() != null) {

                
                page.setPage(queryVo.getPage());
                queryVo.setStartRow((queryVo.getPage() - 1) * queryVo.getSize());
            }
            if (queryVo.getCustName() != null && "".equals(queryVo.getCustName().trim())) {
                queryVo.setCustName(queryVo.getCustName().trim());
            }
            if (queryVo.getCustSource() != null && "".equals(queryVo.getCustSource().trim())) {
                queryVo.setCustSource(queryVo.getCustSource().trim());
            }
            if (queryVo.getCustLevel() != null && "".equals(queryVo.getCustLevel().trim())) {
                queryVo.setCustLevel(queryVo.getCustLevel().trim());
            }
            if (queryVo.getCustIndustry() != null && "".equals(queryVo.getCustIndustry().trim())) {
                queryVo.setCustIndustry(queryVo.getCustIndustry().trim());
            }

            page.setTotal(customerDao.customerCountByQueryVo(queryVo));
            page.setRows(customerDao.selectCustomerListByQueryVo(queryVo));
        }
        
      
        return page;

    }

    public Customer selectCustomerById(Integer id){
        return customerDao.selectCustomerById(id);
    }
    
    //修改客户通过ID
    public void updateCustomerById(Customer customer){
        customerDao.updateCustomerById(customer);
    }
    
  //通过ID 删除客户
    public void deleteCustomerById(Integer id){
        customerDao.deleteCustomerById(id);
    }
}
