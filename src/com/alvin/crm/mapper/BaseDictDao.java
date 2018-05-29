package com.alvin.crm.mapper;

import java.util.List;

import com.alvin.crm.pojo.BaseDict;

public interface BaseDictDao {

    //查询
    public List<BaseDict> selectBaseDictListByCode(String Code);
}
