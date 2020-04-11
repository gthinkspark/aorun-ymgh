package com.aorun.ymgh.controller;

import com.aorun.ymgh.model.Resource;
import com.aorun.ymgh.model.ResourceDownload;
import com.aorun.ymgh.service.ResourceDownloadService;
import com.aorun.ymgh.service.ResourceService;
import com.aorun.ymgh.util.DateFormat;
import com.aorun.ymgh.util.ImagePathUtil;
import com.aorun.ymgh.util.RandomNumUtil;
import com.aorun.ymgh.util.UnionConstant;
import com.aorun.ymgh.util.biz.ImagePropertiesConfig;
import com.aorun.ymgh.util.biz.WorkerMemberUtil;
import com.aorun.ymgh.util.file.FileIOUtils;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("resource")
@RestController
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceDownloadService resourceDownloadService;

	
	/**
	 * 
	 * @param sid
	 * @param articleCode
	 * @param articleValue
	 * @param tag
	 * @param source
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("fileUpload")
	public Object fileUpload(
			@RequestParam(value="sid",required=true) String sid,
			@RequestParam(value="articleCode",required=true) String articleCode,
			@RequestParam(value="articleValue",required=false,defaultValue="") String articleValue,
			@RequestParam(value="tag",required=false,defaultValue="") String tag,
			@RequestParam(value="source",required=true) int source,
			@RequestParam("file") MultipartFile[] file) {
		try {
			Long workerId = WorkerMemberUtil.getWorkerId(sid);
			StringBuffer idBuffer = new StringBuffer();
			StringBuffer imgBuffer = new StringBuffer();
			for (int i = 0; i < file.length; i++) {
				boolean writeFileFromIS = false;
				MultipartFile multipartFile = file[i];
				InputStream inputStream = multipartFile.getInputStream();
				if (inputStream != null) {
					Date now = new Date();
					// 获取文件的名字
					String filename = multipartFile.getOriginalFilename();
					String suffix = filename.substring(filename.lastIndexOf(".") + 1);
					long fileSize = multipartFile.getSize();
					long timeMillis = System.currentTimeMillis();
					String TIME_PATH = DateFormat.dateToYearMonth(now);
					String articlePath = "";
					if(UnionConstant.RESOURCE_ARTICLE_CODE_NEWS.equals(articleCode)){
						articlePath = "news";
					}else if(UnionConstant.RESOURCE_ARTICLE_CODE_LIVE_CLAIM.equals(articleCode)||
							UnionConstant.RESOURCE_ARTICLE_CODE_MEDICAL_CLAIM.equals(articleCode)||
							UnionConstant.RESOURCE_ARTICLE_CODE_SCHOOL_CLAIM.equals(articleCode)||
							UnionConstant.RESOURCE_ARTICLE_CODE_TEMP_CLAIM.equals(articleCode)
							){
						articlePath = "claim";
					}
					// 文件类型目录+时间目录+时间戳名+文件后缀名
					String FilePostfix = articlePath + "/" + TIME_PATH + "/" + timeMillis + "." + suffix;
					// 首页功能管理目录文件路径（根目录+文件后缀）
					String filePath = ImagePropertiesConfig.RESOURCE_PATH + FilePostfix;
					writeFileFromIS = FileIOUtils.writeFileFromIS(filePath, inputStream);
					if (writeFileFromIS) {
						String imageUrl = ImagePropertiesConfig.RESOURCE_SERVER_PATH + FilePostfix;
						Resource resource = new Resource();
						resource.setArticleCode(articleCode);
						resource.setArticleValue(articleValue);
						resource.setFileFix(suffix);
						resource.setFileMixName(timeMillis + suffix);
						resource.setFileName(filename);
						resource.setFileSize(fileSize);
						resource.setSource(source);
						resource.setResCode(RandomNumUtil.getCharacterAndNumber(UnionConstant.RESOURCE_RESCODE_LEN));
						resource.setTag(tag);
						//存储相对路径
						resource.setUrl(ImagePathUtil.getFileName(filePath));
						resource.setUserId(workerId+"");
						resource.setCreateTime(now);
						resource.setType(getFileTypeBySuffix(suffix));
						resourceService.add(resource);
						
						idBuffer.append(resource.getId() + ",");
						imgBuffer.append(imageUrl + ",");
					} else {
						return Jsonp.error(multipartFile.getOriginalFilename() + "上传失败");
					}
				}
			}
			String ids = idBuffer.toString();
			if (!StringUtils.isEmpty(ids)) {
				ids = ids.substring(0, ids.length() - 1);
			}
			String imgs = imgBuffer.toString();
			if (!StringUtils.isEmpty(imgs)) {
				imgs = imgs.substring(0, imgs.length() - 1);
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("ids", ids);
			resultMap.put("imgs", imgs);
			return Jsonp_data.success(resultMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Jsonp.error("文件上传异常,请稍后重试！");
	}
	
	private int getFileTypeBySuffix(String suffix){
		int type=0;
		if("jpg,png".indexOf(suffix)!=-1){
			type=1;
		}
		else if("AVI,mov,rmvb,rm,FLV,mp4,3GP".indexOf(suffix)!=-1){
			type=2;
		}
		else if("docx,doc".indexOf(suffix)!=-1){
			type=3;
		}
		else if("xlsx,xls".indexOf(suffix)!=-1){
			type=4;
		}
		else if("pptx,ppt".indexOf(suffix)!=-1){
			type=5;
		}
		else if("txt".indexOf(suffix)!=-1){
			type=6;
		}
		return type;
	}


	@ResponseBody
	@RequestMapping("fileDownLoad")
	public Object fileDownLoad(
			@RequestParam(value="sid",required=true) String sid,
			@RequestParam(value="resCode",required=true) String resCode,
			@RequestParam(value="source",required=true) int source
			){
		Long workerId = WorkerMemberUtil.getWorkerId(sid);
		ResourceDownload resourceDownload = new ResourceDownload();
		resourceDownload.setResCode(resCode);
		resourceDownload.setUserId(workerId);
		resourceDownload.setCreateTime(new Date());
		resourceDownload.setSource(source);
		resourceDownloadService.add(resourceDownload);
		return Jsonp.success();
	}
}
