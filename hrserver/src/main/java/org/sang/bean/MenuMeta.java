package org.sang.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sang
 * @date 2018/1/26
 */
@Data
public class MenuMeta implements Serializable {
    private boolean keepAlive;
    private boolean requireAuth;
}
