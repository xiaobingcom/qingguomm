package com.qingguomama.controller;

import com.qingguomama.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@CrossOrigin(origins = {"http://localhost:2442"})
public class UploadController {
    @Autowired
    private UploadService uploadService;

    private static String bucketHostName="pka9l5dgc.bkt.clouddn.com";

    private static String fileUrl;


    @RequestMapping(value="/fileUpload")
    @ResponseBody
    public String buttonTest(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        if(file!=null) {
            String key = uploadService.upload(file);
            String URL = bucketHostName + "/" + key;

            System.out.println(URL);
            return URL;
        }else{
            return "false";
        }//返回值给微信小程序


    }






}
