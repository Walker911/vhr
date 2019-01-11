package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Hr;
import org.sang.bean.Role;

import java.util.List;

/**
 * @author sang
 * @date 2018/1/26
 */
public interface HrMapper {
    /**
     * 根据 username 获取用户信息
     *
     * @param username
     * @return
     */
    Hr loadUserByUsername(String username);

    /**
     * 根据 id 获取角色信息
     *
     * @param id
     * @return
     */
    List<Role> getRolesByHrId(Long id);

    int hrReg(@Param("username") String username, @Param("password") String password);

    /**
     * 根据关键字获取 HR
     *
     * @param keywords
     * @return
     */
    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    /**
     * 更新 HR
     *
     * @param hr
     * @return
     */
    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    /**
     * 根据 id 获取用户信息
     *
     * @param hrId
     * @return
     */
    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHr(@Param("currentId") Long currentId);
}
