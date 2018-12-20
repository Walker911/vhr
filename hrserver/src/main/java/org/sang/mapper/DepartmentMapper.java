package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Department;

import java.util.List;

/**
 *
 * @author sang
 * @date 2018/1/7
 */
public interface DepartmentMapper {
    /**
     * 添加部门
     *
     * @param department 部门信息
     */
    void addDep(@Param("dep") Department department);

    /**
     * 删除部门
     *
     * @param department 部门信息
     */
    void deleteDep(@Param("dep") Department department);

    /**
     * 根据 parentId 获取部门
     *
     * @param pid parentId
     * @return 部门列表
     */
    List<Department> getDepByPid(Long pid);

    /**
     * 获取所有部门
     *
     * @return 部门列表
     */
    List<Department> getAllDeps();
}
