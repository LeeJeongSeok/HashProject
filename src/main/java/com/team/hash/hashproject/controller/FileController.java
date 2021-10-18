package com.team.hash.hashproject.controller;

import com.team.hash.hashproject.service.S3Uploader;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class FileController {

    private final S3Uploader s3Uploader;

    public FileController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        boolean isConfirm = false;
        String fileName = "";
        String resultPath = s3Uploader.upload(file, "static");
        System.out.println("S3 Image Path : " + resultPath);

        isConfirm = true;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fileName", fileName);
            jsonObject.put("fileUrl", resultPath);
            jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
        } catch (JSONException e) {
        }

        return jsonObject.toString();
    }
}
