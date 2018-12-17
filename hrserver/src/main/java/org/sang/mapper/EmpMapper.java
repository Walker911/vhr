package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Employee;
import org.sang.bean.Nation;
import org.sang.bean.PoliticsStatus;

import java.util.Date;
import java.util.List;

/**
 *
 * @author sang
 * @date 2018/1/12
 */
public interface EmpMapper {
    List<Nation> getAllNations();

    List<PoliticsStatus> getAllPolitics();

    /**
     * 添加员工
     *
     * @param employee 员工信息
     * @return 0/1
     */
    int addEmp(Employee employee);

    /**
     * 获取最大工号
     *
     * @return 工号
     */
    Long getMaxWorkID();

    List<Employee> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords, @Param("politicId") Long politicId, @Param("nationId") Long nationId, @Param("posId") Long posId, @Param("jobLevelId") Long jobLevelId, @Param("engageForm") String engageForm, @Param("departmentId")Long  departmentId, @Param("startBeginDate") Date startBeginDate, @Param("endBeginDate") Date endBeginDate);

    Long getCountByKeywords(@Param("keywords") String keywords, @Param("politicId") Long politicId, @Param("nationId") Long nationId, @Param("posId") Long posId, @Param("jobLevelId") Long jobLevelId, @Param("engageForm") String engageForm, @Param("departmentId")Long  departmentId, @Param("startBeginDate") Date startBeginDate, @Param("endBeginDate") Date endBeginDate);

    /**
     * 修改员工信息
     *
     * @param employee 员工信息
     * @return 0/1
     */
    int updateEmp(@Param("emp") Employee employee);

    /**
     * 删除员工
     *
     * @param ids id 数组
     * @return
     */
    int deleteEmpById(@Param("ids") String[] ids);

    int addEmps(@Param("emps") List<Employee> emps);

    List<Employee> getEmployeeByPageShort(@Param("start") int start, @Param("size") Integer size);
}
