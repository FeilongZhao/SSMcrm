package com.alvin.crm.service;

import java.util.List;

import com.alvin.crm.pojo.BaseDict;



public interface BaseDictService {

	
	//查询
	public List<BaseDict> selectBaseDictListByCode(String code);
}
