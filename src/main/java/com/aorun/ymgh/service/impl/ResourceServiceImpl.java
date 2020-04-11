package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.ResourceMapper;
import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.base.BasePageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl extends BasePageServiceImpl<ResourceMapper, Resource> implements ResourceService {
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	protected void initMapper() {
		this.mapper = resourceMapper;
	}

	public Resource selectByResCode(String resCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resCode", resCode);
		List<Resource> selectByMap = resourceMapper.selectByMap(map);
		return null!=selectByMap&&selectByMap.size()>0?selectByMap.get(0):null;
	}

	public List<Resource> selectArticle(String articleCode, String articleValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleCode", articleCode);
		map.put("articleValue", articleValue);
		return resourceMapper.selectByMap(map);
	}

	public int updateBatchByPrimaryKeySelective(String ids,String articleCode,String articleValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("articleCode", articleCode);
		map.put("articleValue", articleValue);
		return mapper.updateBatchByPrimaryKeySelective(map);
	}
	
}
