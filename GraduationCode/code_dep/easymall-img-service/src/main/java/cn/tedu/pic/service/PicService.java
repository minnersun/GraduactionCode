package cn.tedu.pic.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.utils.UploadUtil;
import com.jt.common.vo.PicUploadResult;

@Service
public class PicService {
	//读取properties中的路径前缀 http://image.jt.com/ c:/img
	@Value("${url.path}")
	private String urlPath;
	@Value("${disk.path}")
	private String diskPath;
	public PicUploadResult imgUp(MultipartFile pic) {
		//准备一个空数据对象
		PicUploadResult result=new PicUploadResult();
		//1.获取图片的后缀,判断是否合法
			//图片源文件名称
		String oldName=pic.getOriginalFilename();
			//oldName="penguine.jpg"
		String extName=oldName
				.substring(oldName.lastIndexOf("."));
			//extName=".jpg"|".png"
			//通过正则表达式判断合法 只要是jpg,png,gif其中一个
		if(!extName.matches(".(jpg|png|gif)")){
			//if进入,说明后缀不合法
			result.setError(1);//表示上传失败
			return result;
		}
		//2.生成一个公用路径 /upload/3/d/2/2/d/g/h/j/
			//直接调用upload的路径生成器,根据图片名称生成路径
			//fileName 图片名称,upload是前缀
			//根据图片名称字符串,生成hash散列的8位字符的字符串
			//3d22dghj,将其截取/3/d/2/2/d/g/h/j/
			//根据传入的前缀名称 拼接形成公用文件夹结构名称
			///upload/3/d/2/2/d/g/h/j/
		String dir="/"+UploadUtil.
				getUploadPath(oldName, "upload")+"/";
		//3.根据公用路径,根据nginx访问的静态文件 位置c:/img生成文件夹
			//c:/img/upload/3/d/2/2/d/g/h/j/,防止一个文件夹中存在大量的图片
			//导致查询效率低下
		String diskDir=diskPath+dir;//将图片数据输出到这个文件夹
			//创建文件夹,需要先判断
		File _dir=new File(diskDir);
		if(!_dir.exists()){//如果不存在,需要先创建
			_dir.mkdirs();
		}
			//if没有进入,说明已经存在,直接使用,将pic中的数据
			//输出保存到文件夹,重命名的文件名称
		//4.重命名文件
			String fileName=UUID.randomUUID().toString()+extName;
			//5f0d34dc-157f-49ba-ad39-1b28927ba6ae_1005714.jpg
		//5.将pic输出为 _dir中的一个fileName,名称的文件
			try{
			//将对象的数据,按照指定的file的路径值,输出到磁盘中
			//生成一个文件
			pic.transferTo(new File(diskDir+fileName));
			}catch(Exception e){
				e.printStackTrace();
				result.setError(1);
				return result;
			}
		//6.生成url地址 http://image.jt.com/upload/1/2/d/3/d/3/d/3/
		//5f0d34dc-157f-49ba-ad39-1b28927ba6ae_1005714.jpg
			String urlName=urlPath+dir+fileName;
			result.setUrl(urlName);
		return result;
	}

}
