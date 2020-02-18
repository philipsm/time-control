package com.evan.flp_time_control.control;

import com.evan.flp_time_control.pojo.User;
import com.evan.flp_time_control.result.Result;
import com.evan.flp_time_control.result.ResultFactory;
import com.evan.flp_time_control.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        //对 html 标签进行转义，防止 XSS 攻击
        String userName = requestUser.getUsername();
        userName = StringUtils.isEmpty(userName) ? null : HtmlUtils.htmlEscape(userName);
        User user = userService.get(userName);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(user);
        } catch (AuthenticationException e) {
            String message = "登陆失败，账号密码错误";
            return ResultFactory.buildFailResult(message);
        }


//        if (null==user){
//            System.out.println("未接收到请求数据");
//            return new Result(400);
//        }else {
//            System.out.println("登陆成功");
//            session.setAttribute("user",user);//登陆缓存
//            return new Result(200);
//        }
    }
    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        user.setEnabled(true);
        boolean exist = userService.isExist(username);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.addOrUpdate(user);

        return ResultFactory.buildSuccessResult(user);
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }


}
