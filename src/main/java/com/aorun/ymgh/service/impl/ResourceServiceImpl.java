package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.base.BasePageServiceImpl;
import com.aorun.ymgh.dao.ResourceMapper;
import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.util.UnionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
		map.put("isDel", UnionConstant.IS_DEL_N);
		List<Resource> selectByMap = resourceMapper.selectByMap(map);
		return null!=selectByMap&&selectByMap.size()>0?selectByMap.get(0):null;
	}

	@Override
	public List<Resource> selectByIds(String ids) {
		if(StringUtils.isEmpty(ids)){
			return new ArrayList<Resource>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("isDel", UnionConstant.IS_DEL_N);
		return resourceMapper.selectByMap(map);
	}

	public List<Resource> selectArticle(String articleCode, String articleValue) {
		if(StringUtils.isEmpty(articleCode)||StringUtils.isEmpty(articleValue)){
			return new ArrayList<Resource>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleCode", articleCode);
		map.put("articleValue", articleValue);
		map.put("isDel", UnionConstant.IS_DEL_N);
		return resourceMapper.selectByMap(map);
	}

	public int updateBatchByPrimaryKeySelective(String ids,String articleCode,String articleValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("articleCode", articleCode);
		map.put("articleValue", articleValue);
		map.put("lastModifyTime", new Date());
		map.put("isDel", UnionConstant.IS_DEL_N);
		return mapper.updateBatchByPrimaryKeySelective(map);
	}

	@Override
	public int delete(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("lastModifyTime", new Date());
		map.put("isDel", UnionConstant.IS_DEL_Y);
		return mapper.updateBatchByPrimaryKeySelective(map);
	}

}
