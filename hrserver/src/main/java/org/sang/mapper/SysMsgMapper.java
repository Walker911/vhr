package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Hr;
import org.sang.bean.MsgContent;
import org.sang.bean.SysMsg;

import java.util.List;

/**
 * @author sang
 * @date 2018/2/2
 */
public interface SysMsgMapper {

    /**
     * 保存消息
     *
     * @param msg 消息相关参数
     * @return 0/1
     */
    int sendMsg(MsgContent msg);

    /**
     * 发送消息给所有人
     *
     * @param hrs 消息接收人
     * @param mid 消息id
     * @return 0/1
     */
    int addMsg2AllHr(@Param("hrs") List<Hr> hrs, @Param("mid") Long mid);

    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size,@Param("hrid") Long hrid);

    int markRead(@Param("flag") Long flag, @Param("hrid") Long hrid);
}
