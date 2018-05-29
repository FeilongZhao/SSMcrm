package com.alvin.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alvin.crm.pojo.BaseDict;
import com.alvin.crm.pojo.Customer;
import com.alvin.crm.pojo.QueryVo;
import com.alvin.crm.service.BaseDictService;
import com.alvin.crm.service.CustomerService;
import com.alvin.crm.utils.Page;

/***
 * 跳转
 * @author Alvin
 *
 */
@Controller
public class CustomerController {
    
    @Autowired
    private BaseDictService baseDictService;
    @Autowired
    private CustomerService customerService;
    @Value("${fromType.code}")
    private String fromTypeCode;
    @Value("${industryType.code}")
    private String industryTypeCode;
    @Value("${levelType.code}")
    private String levelTypeCode;

    @RequestMapping(value = "/customer/list")
    public String list(QueryVo queryVo,Model model) {
        
        List<BaseDict> fromType = baseDictService.selectBaseDictListByCode(fromTypeCode);
        List<BaseDict> industryType = baseDictService.selectBaseDictListByCode(industryTypeCode);
        List<BaseDict> levelType = baseDictService.selectBaseDictListByCode(levelTypeCode);
        model.addAttribute("fromType", fromType);
        model.addAttribute("industryType",industryType);
        model.addAttribute("levelType", levelType);
        
        Page<Customer> page = customerService.selectPageByQueryVo(queryVo);
        model.addAttribute("page", page);
        model.addAttribute("custName", queryVo.getCustName());
        model.addAttribute("custSource", queryVo.getCustSource());
        model.addAttribute("custIndustry", queryVo.getCustIndustry());
        model.addAttribute("custLevel", queryVo.getCustLevel());
        return "customer";
    }
    @RequestMapping(value = "/customer/edit.action")
    public @ResponseBody
    Customer edit(Integer id){
        return customerService.selectCustomerById(id);
    }
    
  //修改保存
    @RequestMapping(value = "/customer/update.action")
    public @ResponseBody
    String update(Customer customer){
        //修改
        customerService.updateCustomerById(customer);
        return "OK";
    }
  //删除
    @RequestMapping(value = "/customer/delete.action")
    public @ResponseBody
    String delete(Integer id){
        //删除
        customerService.deleteCustomerById(id);
        return "OK";
    }
}
