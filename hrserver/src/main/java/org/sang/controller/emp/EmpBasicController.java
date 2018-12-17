package org.sang.controller.emp;

import org.sang.bean.Employee;
import org.sang.bean.Position;
import org.sang.bean.RespBean;
import org.sang.common.EmailRunnable;
import org.sang.common.poi.PoiUtils;
import org.sang.service.DepartmentService;
import org.sang.service.EmpService;
import org.sang.service.JobLevelService;
import org.sang.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author sang
 * @date 2018/1/12
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    private final EmpService empService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final JobLevelService jobLevelService;
    private final ExecutorService executorService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmpBasicController(EmpService empService, DepartmentService departmentService, PositionService positionService, JobLevelService jobLevelService, ExecutorService executorService, TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.empService = empService;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.jobLevelService = jobLevelService;
        this.executorService = executorService;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping(value = "/basicdata")
    public Map<String, Object> getAllNations() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("nations", empService.getAllNations());
        map.put("politics", empService.getAllPolitics());
        map.put("deps", departmentService.getDepByPid(-1L));
        map.put("positions", positionService.getAllPos());
        map.put("joblevels", jobLevelService.getAllJobLevels());
        map.put("workID", String.format("%08d", empService.getMaxWorkID() + 1));
        return map;
    }

    @GetMapping("/maxWorkID")
    public String maxWorkID() {
        return String.format("%08d", empService.getMaxWorkID() + 1);
    }

    @PostMapping(value = "/emp")
    public RespBean addEmp(Employee employee) {
        if (empService.addEmp(employee) == 1) {
            List<Position> allPos = positionService.getAllPos();
            for (Position allPo : allPos) {
                if (allPo.getId().equals(employee.getPosId())) {
                    employee.setPosName(allPo.getName());
                }
            }
            executorService.execute(new EmailRunnable(employee,
                    javaMailSender, templateEngine));
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping(value = "/emp")
    public RespBean updateEmp(Employee employee) {
        if (empService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping(value = "/emp/{ids}")
    public RespBean deleteEmpById(@PathVariable String ids) {
        if (empService.deleteEmpById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @GetMapping(value = "/emp")
    public Map<String, Object> getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            Long politicId, Long nationId, Long posId,
            Long jobLevelId, String engageForm,
            Long departmentId, String beginDateScope) {
        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = empService.getEmployeeByPage(page, size,
                keywords, politicId, nationId, posId, jobLevelId, engageForm,
                departmentId, beginDateScope);
        Long count = empService.getCountByKeywords(keywords, politicId, nationId,
                posId, jobLevelId, engageForm, departmentId, beginDateScope);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }

    @GetMapping(value = "/exportEmp")
    public ResponseEntity<byte[]> exportEmp() {
        return PoiUtils.exportEmp2Excel(empService.getAllEmployees());
    }

    @PostMapping(value = "/importEmp")
    public RespBean importEmp(MultipartFile file) {
        List<Employee> emps = PoiUtils.importEmp2List(file,
                empService.getAllNations(), empService.getAllPolitics(),
                departmentService.getAllDeps(), positionService.getAllPos(),
                jobLevelService.getAllJobLevels());
        if (empService.addEmps(emps) == emps.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }
}
