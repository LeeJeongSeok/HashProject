package com.team.hash.hashproject.util;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "sessionedUser";
    public static final int USER_NOT_FOUND = 0;

    public static boolean isLoginUser(HttpSession session) {
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public static int getUserNoFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return USER_NOT_FOUND;
        }

        return StringUtil.getStringNumber((String)session.getAttribute("no"));
    }
}
