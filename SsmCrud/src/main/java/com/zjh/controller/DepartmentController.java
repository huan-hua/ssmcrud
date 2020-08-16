package com.zjh.controller;

import com.zjh.entity.Department;
import com.zjh.entity.Msg;
import com.zjh.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *处理和部门有关的请求
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 返回所有部门信息
     * @return
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
       List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts",list);
    }
}
