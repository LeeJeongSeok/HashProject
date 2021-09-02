package com.team.hash.hashproject.controller;

import com.team.hash.hashproject.common.Constant;
import com.team.hash.hashproject.util.DateUtil;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        boolean isConfirm = false;
        String fileName = "";
        String resultPath = "";

        long nowCurrentTime = System.currentTimeMillis();

        String uploadPath = "/Users/jeongseok/Desktop/workspace_intellij/HashProject/src/main/webapp/WEB-INF/temp";
        File uploadFolder = new File(uploadPath);
        uploadFolder.mkdir();

        String filePath = uploadPath + "/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        System.out.println("--------------------------------------------");
        System.out.println("file start: " + DateUtil.getTodayDate("HH:mm:ss"));

//        MultipartRequest multipartRequest = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024 * 10, "utf-8", new DefaultFileRenamePolicy());
//        int userNo = StringUtil.getStringNumber(multipartRequest.getParameter("userNo"));
        fileName = StringUtil.replaceNullForServlet(file.getOriginalFilename());
        System.out.println("fileName: " + fileName);

        resultPath = "/" + fileName;
        System.out.println("resultPath: " + resultPath);

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
