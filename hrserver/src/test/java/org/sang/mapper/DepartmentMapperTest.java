package org.sang.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.bean.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author walker
 * @date 2018/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    private Gson gson = new GsonBuilder().create();

    @Test
    public void testGetAllDeps() {
        List<Department> departments = departmentMapper.getAllDeps();
        Assert.assertNotNull(departments);
    }

    @Test
    public void testGetDepByPid() {
        List<Department> departments = departmentMapper.getDepByPid(1L);
        Assert.assertNotNull(departments);
    }
}