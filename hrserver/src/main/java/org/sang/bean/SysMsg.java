package org.sang.bean;

import lombok.Data;

/**
 * @author sang
 * @date 2018/1/26
 */
@Data
public class SysMsg {
    private Long id;
    private Long mid;
    private Integer type;
    private Long hrid;
    private Integer state;
    private MsgContent msgContent;
}
