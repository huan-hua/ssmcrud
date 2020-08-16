package com.zjh;

import com.zjh.dao.DepartmentMapper;
import com.zjh.dao.EmployeeMapper;
import com.zjh.entity.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * 1.推荐：spring的项目使用spring的单元测试，可以自动注入我们需要的组件
 * 2.导入spring-test模板（jar包）
 * 3.@ContextConfiguration指定spring配置文件的位置
 * 4.@RunWith指定用spring的单元测试
 * 5.autowired要使用的组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void TestCrud(){
//        System.out.println(departmentMapper);
        //部门:测试插入数据
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
        //员工：测试插入数据
//          employeeMapper.insertSelective(new Employee(null,"张三","M","1578@qq.com",1));
        //批量插入
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
        }
        System.out.println("批量完成");
    }
}