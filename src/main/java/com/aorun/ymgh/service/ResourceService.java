package com.aorun.ymgh.service;

import com.aorun.ymgh.base.BasePageService;
import com.aorun.ymgh.model.Resource;

import java.util.List;


public interface ResourceService extends BasePageService<Resource> {
	Resource selectByResCode(String resCode);
	List<Resource> selectArticle(String articleCode, String articleValue);
	int updateBatchByPrimaryKeySelective(String ids, String articleCode, String articleValue);
}
