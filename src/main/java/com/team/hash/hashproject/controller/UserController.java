package com.team.hash.hashproject.controller;

import com.team.hash.hashproject.api.AppInformationManager;
import com.team.hash.hashproject.domain.User;
import com.team.hash.hashproject.util.AesUtil;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map<String, String> loginInfo, HttpSession session) {

        String email = StringUtil.replaceNullForServlet(loginInfo.get("email"));
        String tempPassword = StringUtil.replaceNullForServlet(loginInfo.get("password"));
        String password = new AesUtil().encrypt(tempPassword);
        JSONObject jsonObject = AppInformationManager.getInstance().login(email, password);

        boolean isConfirm = false;
        int no = 0;
        String name = "";
        try {
            isConfirm = "Y".equals(jsonObject.getString("isConfirm"));
            no = jsonObject.getInt("no");
            name = jsonObject.getString("name");
        } catch(JSONException e) { }

        if(isConfirm) {
            session.setAttribute("no", String.valueOf(no));
            session.setAttribute("email", email);
            session.setAttribute("name", name);
        }

        return jsonObject.toString();
    }


    @PostMapping("/join")
    @ResponseBody
    public String join(@RequestBody User user) {

        String email = StringUtil.replaceNullForServlet(user.getEmail());
        String tempPassword = StringUtil.replaceNullForServlet(user.getPassword());
        String password = new AesUtil().encrypt(tempPassword);
        String name = StringUtil.replaceNullForServlet(user.getName());
        String sex = StringUtil.replaceNullForServlet(user.getSex());
        String height = StringUtil.replaceNullForServlet(user.getHeight());
        String weight = StringUtil.replaceNullForServlet(user.getWeight());
        String pictureUrl = StringUtil.replaceNullForServlet(user.getPictureUrl());
        String description = StringUtil.replaceNullForServlet(user.getDescription());
        String strTagArray = StringUtil.replaceNullForServlet(user.getStrTagArray());
        String instagramId = StringUtil.replaceNullForServlet(user.getInstargramId());


        JSONObject jsonObject = AppInformationManager.getInstance().join(email, password, name, sex, height, weight, pictureUrl, description, strTagArray, instagramId);

        return jsonObject.toString();
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        String email = StringUtil.replaceNullForServlet(user.getEmail());
        String tempPassword = StringUtil.replaceNullForServlet(user.getPassword());
        String password = "";
        if(!StringUtil.isEmpty(tempPassword)) {
            password = new AesUtil().encrypt(tempPassword);
        }
        String name = StringUtil.replaceNullForServlet(user.getName());
        String sex = StringUtil.replaceNullForServlet(user.getSex());
        String height = StringUtil.replaceNullForServlet(user.getHeight());
        String weight = StringUtil.replaceNullForServlet(user.getWeight());
        String pictureUrl = StringUtil.replaceNullForServlet(user.getPictureUrl());
        String description = StringUtil.replaceNullForServlet(user.getDescription());
        String strTagArray = StringUtil.replaceNullForServlet(user.getStrTagArray());
        String instagramId = StringUtil.replaceNullForServlet(user.getInstargramId());

        JSONObject jsonObject = AppInformationManager.getInstance().updateUser(userNo, email, password, name, sex, height, weight, pictureUrl, description, strTagArray, instagramId);

        return jsonObject.toString();
    }

    @PostMapping("/getMyInformation")
    @ResponseBody
    public String getMyInformation(HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));
        JSONObject jsonObject = AppInformationManager.getInstance().getMyInformation(userNo);

        return jsonObject.toString();

    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
