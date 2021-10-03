package com.team.hash.hashproject.controller;

import com.team.hash.hashproject.api.AppInformationManager;
import com.team.hash.hashproject.domain.StyleArray;
import com.team.hash.hashproject.domain.StyleItem;
import com.team.hash.hashproject.util.HttpSessionUtils;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class StyleController {

    @PostMapping("/getMyStyleArray")
    @ResponseBody
    public String getMyStyleArray(HttpSession session) {

        int userNo = HttpSessionUtils.getUserNoFromSession(session);
        JSONObject jsonObject = AppInformationManager.getInstance().getMyStyleArray(userNo);

        return jsonObject.toString();

    }

    @PostMapping("/getStyleArray")
    @ResponseBody
    public String getStyleArray(@RequestBody StyleArray styleArray, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        String categoryName = StringUtil.replaceNullForServlet(styleArray.getJSONObject().getString("categoryName"));
        String keyword = StringUtil.replaceNullForServlet(styleArray.getJSONObject().getString("keyword"));
        int page = StringUtil.getStringNumber(styleArray.getJSONObject().getString("page"));
        int viewCount = StringUtil.getStringNumber(styleArray.getJSONObject().getString("viewCount"));
        String orderColumn = StringUtil.replaceNullForServlet(styleArray.getJSONObject().getString("orderColumn"));
        String orderDirection = StringUtil.replaceNullForServlet(styleArray.getJSONObject().getString("orderDirection"));
        JSONObject jsonObject = AppInformationManager.getInstance().getStyleArray(userNo, categoryName, keyword, page, viewCount, orderColumn, orderDirection);

        return jsonObject.toString();
    }


    @PostMapping("/writeStyle")
    @ResponseBody
    public String writeStyle(@RequestBody StyleItem styleItem, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        String content = StringUtil.replaceNullForServlet(styleItem.getContent());
        String pictureUrl = StringUtil.replaceNullForServlet(styleItem.getPictureUrl());
        String strPictureUrlArray = StringUtil.replaceNullForServlet(styleItem.getStrPictureUrlArray());
        String strProductArray = StringUtil.replaceNullForServlet(styleItem.getStrProductArray());
        JSONObject jsonObject = AppInformationManager.getInstance().writeStyle(userNo, content, pictureUrl, strPictureUrlArray, strProductArray);

        return jsonObject.toString();
    }

    @PostMapping("/updateStyle")
    @ResponseBody
    public String updateStyle(@RequestBody StyleItem styleItem, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        int styleNo = StringUtil.getStringNumber(Integer.toString(styleItem.getNo()));
        String content = StringUtil.replaceNullForServlet(styleItem.getContent());
        String pictureUrl = StringUtil.replaceNullForServlet(styleItem.getPictureUrl());
        String strPictureUrlArray = StringUtil.replaceNullForServlet(styleItem.getStrPictureUrlArray());
        String strProductArray = StringUtil.replaceNullForServlet(styleItem.getStrProductArray());
        JSONObject jsonObject = AppInformationManager.getInstance().updateStyle(userNo, styleNo, content, pictureUrl, strPictureUrlArray, strProductArray);

        return jsonObject.toString();
    }

    @PostMapping("/deleteStyle")
    @ResponseBody
    public String deleteStyle(@RequestBody String styleItem, HttpSession session) {

        // deleteReply와 마찬가지인 문제

        System.out.println("styleItem : " + styleItem);
        JSONObject style = new JSONObject(styleItem);

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

//        int styleNo = StringUtil.getStringNumber(Integer.toString(styleItem.getNo()));
        int styleNo = (int) style.get("styleNo");
        JSONObject jsonObject = AppInformationManager.getInstance().deleteStyle(userNo, styleNo);

        return jsonObject.toString();
    }

    @PostMapping("/updateStyleLike")
    @ResponseBody
    public String updateStyleLike(@RequestBody StyleItem styleItem, HttpSession session) {

        int userNo = StringUtil.getStringNumber((String)session.getAttribute("no"));

        int styleNo = StringUtil.getStringNumber(Integer.toString(styleItem.getNo()));
        String isLike = StringUtil.replaceNullForServlet(styleItem.getIsLike());
        JSONObject jsonObject = AppInformationManager.getInstance().updateStyleLike(userNo, styleNo, isLike);

        return jsonObject.toString();

    }

}
