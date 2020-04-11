package com.aorun.ymgh.dao;


import com.aorun.ymgh.base.BaseMapper;
import com.aorun.ymgh.model.Resource;

import java.util.Map;

public interface ResourceMapper extends BaseMapper<Resource> {
	int updateBatchByPrimaryKeySelective(Map<String, Object> map);
}