package org.sang.handler;

import org.sang.bean.RespBean;
import org.sang.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author walker
 * @date 2019/1/8
 */
@Component
public class LogoutHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        RespBean result = RespBean.ok("注销成功！");
        ResponseUtil.writeResponse(response, result);
    }
}
