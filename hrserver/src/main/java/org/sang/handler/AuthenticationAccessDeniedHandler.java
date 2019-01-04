package org.sang.handler;

import org.sang.bean.RespBean;
import org.sang.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author sang
 * @date 2017/12/29
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        RespBean error = RespBean.error("权限不足，请联系管理员!");
        ResponseUtil.writeResponse(response, error);
    }
}
