package org.sang.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sang
 * @date 2018/1/26
 */
@Data
public class Role implements Serializable {
    private Long id;
    private String name;
    private String nameZh;
}
