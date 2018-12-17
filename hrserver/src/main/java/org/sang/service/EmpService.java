package org.sang.service;

import org.sang.bean.Employee;
import org.sang.bean.Nation;
import org.sang.bean.PoliticsStatus;
import org.sang.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author sang
 * @date 2018/1/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmpService {
    private final EmpMapper empMapper;

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    private SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Autowired
    public EmpService(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    public List<Nation> getAllNations() {
        return empMapper.getAllNations();
    }

    public List<PoliticsStatus> getAllPolitics() {
        return empMapper.getAllPolitics();
    }

    public int addEmp(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        Double contractTerm = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(contractTerm / 12)));
        return empMapper.addEmp(employee);
    }

    /**
     * 获取最大工号
     *
     * @return 工号
     */
    public Long getMaxWorkID() {
        Long maxWorkID = empMapper.getMaxWorkID();
        return maxWorkID == null ? 0 : maxWorkID;
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer size, String keywords, Long politicId, Long nationId, Long posId, Long jobLevelId, String engageForm, Long departmentId, String beginDateScope) {
        int start = (page - 1) * size;
        Date startBeginDate = null;
        Date endBeginDate = null;
        if (beginDateScope != null && beginDateScope.contains(",")) {
            try {
                String[] split = beginDateScope.split(",");
                startBeginDate = birthdayFormat.parse(split[0]);
                endBeginDate = birthdayFormat.parse(split[1]);
            } catch (ParseException e) {
            }
        }
        return empMapper.getEmployeeByPage(start, size, keywords, politicId, nationId, posId, jobLevelId, engageForm, departmentId, startBeginDate, endBeginDate);
    }

    public Long getCountByKeywords(String keywords, Long politicId, Long nationId, Long posId, Long jobLevelId, String engageForm, Long departmentId, String beginDateScope) {
        Date startBeginDate = null;
        Date endBeginDate = null;
        if (beginDateScope != null && beginDateScope.contains(",")) {
            try {
                String[] split = beginDateScope.split(",");
                startBeginDate = birthdayFormat.parse(split[0]);
                endBeginDate = birthdayFormat.parse(split[1]);
            } catch (ParseException e) {
            }
        }
        return empMapper.getCountByKeywords(keywords, politicId, nationId, posId, jobLevelId, engageForm, departmentId, startBeginDate, endBeginDate);
    }

    /**
     * 修改员工信息
     *
     * @param employee 员工信息
     * @return 0/1
     */
    public int updateEmp(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        Double contractTerm = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(contractTerm / 12)));
        return empMapper.updateEmp(employee);
    }

    public boolean deleteEmpById(String ids) {
        String[] split = ids.split(",");
        return empMapper.deleteEmpById(split) == split.length;
    }

    public List<Employee> getAllEmployees() {
        return empMapper.getEmployeeByPage(null, null, "", null, null, null, null, null, null, null, null);
    }

    public int addEmps(List<Employee> emps) {
        return empMapper.addEmps(emps);
    }

    public List<Employee> getEmployeeByPageShort(Integer page, Integer size) {
        int start = (page - 1) * size;
        return empMapper.getEmployeeByPageShort(start,size);
    }
}
