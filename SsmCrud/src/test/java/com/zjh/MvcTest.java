package com.zjh;

import com.github.pagehelper.PageInfo;
import com.zjh.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:shoppingSpringMVC.xml"})
public class MvcTest {
        //传入spring的ioc
        @Autowired
        WebApplicationContext context;
        //虚拟mvc请求，获取到处理结果
        MockMvc mockMvc;

        @Before
        public void initMockMvc(){
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
        @Test
        public void testPage() throws Exception {
            //模拟请求拿到返回值
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "50")).andReturn();
            //请求成功以后，请求域中有pageInfo，拿出pageInfo经行验证
            PageInfo page =(PageInfo) result.getRequest().getAttribute("pageInfo");
            System.out.println("当前页码"+page.getPageNum());
            System.out.println("总页码"+page.getPages());
            System.out.println("总记录数"+page.getTotal());
            System.out.println("连续显示的页码");
            int[] nums = page.getNavigatepageNums();
            for (int i : nums){
                System.out.print(" "+i);
            }
            //获取员工数据
            List<Employee> list = page.getList();
            for (Employee employee : list){
                System.out.println("id:"+employee.getdId()+"--name:"+employee.getEmpName());
            }
        }

}
