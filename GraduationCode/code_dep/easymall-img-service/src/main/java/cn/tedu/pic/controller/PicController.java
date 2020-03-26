package cn.tedu.pic.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jt.common.vo.PicUploadResult;
import cn.tedu.pic.service.PicService;
@RestController
public class PicController {
	//注入业务层
	@Autowired
	private PicService picService;
	//图片上传
	@RequestMapping("pic/upload")
	public PicUploadResult imgUp(MultipartFile pic){
		//在业务层处理图片的上传和数据的生成返回逻辑
		return picService.imgUp(pic);
	}
}
