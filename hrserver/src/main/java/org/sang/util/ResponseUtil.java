package org.sang.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sang.bean.RespBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author walker
 * @date 2019/1/3
 */
public class ResponseUtil {
    /**
     * 返回响应结果
     *
     * @param resp  HttpServletResponse
     * @param respBean RespBean
     * @throws IOException IOException
     */
    public static void writeResponse(HttpServletResponse resp, RespBean respBean) throws IOException {
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
