package com.aorun.ymgh.service.impl;

import com.aorun.ymgh.dao.ResourceDownloadMapper;
import com.aorun.ymgh.model.ResourceDownload;
import com.aorun.ymgh.service.ResourceDownloadService;
import com.aorun.ymgh.base.BasePageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ResourceDownloadServiceImpl extends BasePageServiceImpl<ResourceDownloadMapper, ResourceDownload> implements ResourceDownloadService {
	@Autowired
	private ResourceDownloadMapper resourceDownloadMapper;
	
	@Override
	protected void initMapper() {
		this.mapper = resourceDownloadMapper;
	}

	public int getTotalByResCode(String resCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resCode", resCode);
		return resourceDownloadMapper.getTotal(map);
	}

	public int getTotalByUser(long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return resourceDownloadMapper.getTotal(map);
	}

}
