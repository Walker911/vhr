package org.sang.controller;

import org.sang.bean.Hr;
import org.sang.bean.MsgContent;
import org.sang.bean.RespBean;
import org.sang.bean.SysMsg;
import org.sang.service.HrService;
import org.sang.service.SysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理通知消息的Controller
 * 登录即可访问
 *
 * @author sang
 * @date 2017/12/8
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final HrService hrService;
    private final SysMsgService sysMsgService;

    @Autowired
    public ChatController(HrService hrService, SysMsgService sysMsgService) {
        this.hrService = hrService;
        this.sysMsgService = sysMsgService;
    }

    @RequestMapping(value = "/hrs", method = RequestMethod.GET)
    public List<Hr> getAllHr() {
        return hrService.getAllHrExceptAdmin();
    }

    @RequestMapping(value = "/nf", method = RequestMethod.POST)
    public RespBean sendNf(MsgContent msg) {
        if (sysMsgService.sendMsg(msg)) {
            return RespBean.ok("发送成功!");
        }
        return RespBean.error("发送失败!");
    }

    @RequestMapping("/sysmsgs")
    public List<SysMsg> getSysMsg(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return sysMsgService.getSysMsgByPage(page, size);
    }

    /**
     * 消息标记未已读
     *
     * @param flag -1 全部标记为已读
     * @return result
     */
    @RequestMapping(value = "/markread", method = RequestMethod.PUT)
    public RespBean markRead(Long flag) {
        if (sysMsgService.markRead(flag)) {
            if (flag == -1) {
                return RespBean.ok("multiple");
            } else {
                return RespBean.ok("single");
            }
        } else {
            if (flag == -1) {
                return RespBean.error("multiple");
            } else {
                return RespBean.error("single");
            }
        }
    }
}
