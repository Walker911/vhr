package org.sang.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author sang
 * @date 2018/1/26
 */
public interface MenuRoleMapper {
    int deleteMenuByRid(@Param("rid") Long rid);

    int addMenu(@Param("rid") Long rid, @Param("mids") Long[] mids);
}
