package com.team.hash.hashproject.controller;

import com.team.hash.hashproject.api.AppInformationManager;
import com.team.hash.hashproject.domain.StyleItem;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {

    @PostMapping("/getReplyArray")
    @ResponseBody
    public String getReplyArray(@RequestBody StyleItem styleItem, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        int styleNo = StringUtil.getStringNumber(Integer.toString(styleItem.getNo()));
        JSONObject jsonObject = AppInformationManager.getInstance().getReplyArray(userNo, styleNo);

        return jsonObject.toString();
    }

    @PostMapping("/writeReply")
    @ResponseBody
    public String writeReply(@RequestBody StyleItem styleItem, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        int styleNo = StringUtil.getStringNumber(Integer.toString(styleItem.getNo()));
        String content = StringUtil.replaceNullForServlet(styleItem.getContent());
        System.out.println("styleItem Content : " + content);
        JSONObject jsonObject = AppInformationManager.getInstance().writeReply(userNo, styleNo, content);


        return jsonObject.toString();
    }

    @PostMapping("/deleteReply")
    @ResponseBody
    public String deleteReply(@RequestBody String info, HttpSession session) {

        // 기존에 ReplyItem 객체로 받아서 넘길려고했는데 부족했음.
        // 현재는 String 형태로 json 형식의 데이터를 받은 후 JSONObject으로 변환하여 사용중

        JSONObject replyItem = new JSONObject(info);

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        int replyNo = (int) replyItem.get("replyNo");
        JSONObject jsonObject = AppInformationManager.getInstance().deleteReply(userNo, replyNo);

        return jsonObject.toString();
    }
}
