package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.Resource;
import com.dxl.fc.model.Work;
import com.dxl.fc.service.ResourceService;
import com.dxl.fc.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    WorkService workService;
    @Autowired
    ResourceService resourceService;


    @PostMapping("/upload")
    @ResponseBody
    public Msg upload(HttpServletRequest request,
                      @RequestParam("file") MultipartFile file,
                      @RequestParam("type") String type,
                      @RequestParam("file_id") int file_id)
            throws IllegalStateException, IOException {

        // 测试MultipartFile接口的各个方法
        System.out.println("文件类型ContentType=" + file.getContentType());
        System.out.println("文件组件名称Name=" + file.getName());
        System.out.println("文件原名称OriginalFileName=" + file.getOriginalFilename());
        System.out.println("文件大小Size=" + file.getSize() / 1024 + "KB");


        // 如果文件不为空，写入上传路径，进行文件上传

        String path = ResourceUtils.getURL("classpath:").getPath();
        if (!file.isEmpty()) {
            if (type.equals("work")) {
                Work work = workService.getById(file_id);
                work.setInfo(file.getOriginalFilename());
                workService.update(work);
                path = path + "static/document";
            }else{//不是作业，就是资料
                String category = type.substring(type.lastIndexOf("&"));

                Resource resource = resourceService.getById(file_id);
                resource.setContent(file.getOriginalFilename());
                resourceService.update(resource);
                if (category.equals("文档")){
                    path = path + "static/document";
                }else if (category.equals("图片")){
                    path = path + "static/img";
                }else{
                    path = path + "static/video";
                }

            }

            // 构建上传文件的存放路径

            // 获取上传的文件名称，并结合存放路径，构建新的文件名称
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);

            // 判断路径是否存在，不存在则新创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到目标文件目录
            file.transferTo(new File(path + File.separator + filename));
            return Msg.success().add("msg", "上传成功");
        } else {
            return Msg.fail().add("msg", "上传失败。");
        }
    }


    @RequestMapping(value = "/download")
    @ResponseBody
    public void download(@RequestParam("category") String category, @RequestParam("fileName") String fileName, HttpServletResponse response) throws FileNotFoundException {
        String filePath = ResourceUtils.getURL("classpath:").getPath();
        String filePackage = "static/";
        if (category.equals("文档")) {
            filePackage = filePackage + "document/";
        } else if (category.equals("图片")) {
            filePackage = filePackage + "img/";
        } else {
            filePackage = filePackage + "video/";
        }
        String path = filePath + filePackage + fileName;
        System.out.printf(path);
        File file = new File(path);
        if (!file.exists()) {

            return;
        }

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            byte[] buffer = new byte[1024];
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream outputStream = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
