package org.sang.handler;

import org.sang.bean.RespBean;
import org.sang.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author walker
 * @date 2019/1/3
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        RespBean result;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            result = RespBean.error("账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
            result = RespBean.error("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            result = RespBean.error("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            result = RespBean.error("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            result = RespBean.error("账户被禁用，请联系管理员!");
        } else {
            result = RespBean.error("登录失败!");
        }
        response.setStatus(401);
        ResponseUtil.writeResponse(response, result);
    }
}
