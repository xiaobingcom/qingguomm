package com.qingguomama.controller;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.qingguomama.service.UploadService;
import com.qingguomama.util.LeanCloudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;

    private String fileUrl;
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        LeanCloudUtil.start();
        String currentUser = request.getParameter("CurrentUser");


        String imgUrl = fileUrl;



        if (file!=null){

            String key = uploadService.upload(file);
            imgUrl=bucketHostName+key;
        }

        AVObject user = new AVObject("User");
        user.put("currentUser", currentUser);

        AVObject Img = new AVObject("Imag");
        Img.put("Url", fileUrl);

        user.put("defaultImag", imgUrl);

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {

                }
            }
        });


        return imgUrl;
    }






}
