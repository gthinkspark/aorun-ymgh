package com.aorun.ymgh.service;


import com.aorun.ymgh.base.BasePageService;
import com.aorun.ymgh.model.ResourceDownload;

public interface ResourceDownloadService extends BasePageService<ResourceDownload> {
	
	int getTotalByResCode(String resCode);
	
	int getTotalByUser(long userId);

}
