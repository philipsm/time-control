package com.evan.flp_time_control.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {


        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        // 验证成功的与记住状态的都可以登陆
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return false;
        }
        return true;



//        HttpSession session = request.getSession();
//        String contextPath = session.getServletContext().getContextPath();
//
//        String[] requireAuthPages = new String[]{
//                "index"
//        };
//
//        String uri = request.getRequestURI();
//        uri = StringUtils.remove(uri,contextPath+"/");
//
//        if (begingWith(uri,requireAuthPages)){
//            User user = (User) session.getAttribute("user");
//            if (null==user){
//                response.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
