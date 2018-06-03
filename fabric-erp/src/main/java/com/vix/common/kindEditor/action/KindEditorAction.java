package com.vix.common.kindEditor.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;


@Controller
@Scope("prototype")
public class KindEditorAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String uploadFile(){
		//文件保存目录路径    img_upload是服务器存储上传图片的目录名
        String savePath = getServletContext().getRealPath("/") + "attached/kindeditor";
        //文件保存目录URL
        String saveUrl = getRequest().getContextPath() + "/attached/kindeditor";
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        //允许最大上传文件大小
        long maxSize = 2048000;
        //Struts2 请求 包装过滤器
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) getRequest();
        //获得上传的文件名
        String fileName = wrapper.getFileNames("imgFile")[0];
        //获得文件过滤器
        File file = wrapper.getFiles("imgFile")[0];
        //得到上传文件的扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        //获得文件上传的类型
        String dirName = getRequest().getParameter("dir");
        if (dirName == null) {
                dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
                setMessage("目录名不正确。");
        		return UPDATE;
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
        }
        //检查扩展名
        if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                setMessage("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
        		return UPDATE;
        }
        //检查文件大小
        if (file.length() > maxSize) {
                setMessage("上传文件大小超过限制，上传的文件不能超过2M。");
        		return UPDATE;
        } 
        //检查目录
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String dir = sdf.format(new Date());
        File uploadDir = new File(savePath + dir);
        uploadDir.mkdirs();
        if (!uploadDir.isDirectory()) {
                setMessage("上传目录不存在 。");
        		return UPDATE;
        }
        //检查目录写入权限
        if (!uploadDir.canWrite()) {
                setMessage("上传目录没有写入权限。");
        		return UPDATE;
        }
        //重构上传图片的名称 
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newImgName = df.format(new Date()) + "_"
                        + new Random().nextInt(1000) + "." + fileExt;
        
        byte[] buffer = new byte[1024];
        //获取文件输出流
        FileOutputStream fos = null;
        //获取内存中当前文件输入流
        InputStream in = null;
        try {
        	 fos = new FileOutputStream(savePath + dir + "/" + newImgName);
        	 in = new FileInputStream(file);
            int num = 0;
            while ((num = in.read(buffer)) > 0) {
                    fos.write(buffer, 0, num);
            }
        } catch (Exception e) {
                e.printStackTrace(System.err);
        } finally {
        	try{
                in.close();
                fos.close();
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
        }

        //发送给 KE 
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", saveUrl + dir + "/" + newImgName);
		setMessage(obj.toJSONString());
		return UPDATE;
	}
}
