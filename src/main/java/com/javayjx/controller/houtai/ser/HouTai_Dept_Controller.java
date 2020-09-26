package com.javayjx.controller.houtai.ser;

import com.javayjx.entity.ser.*;
import com.javayjx.service.ser.DeptService;
import com.javayjx.service.ser.RecordService;
import com.javayjx.service.ser.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/26 0026 17:19
 */
@Controller
@RequestMapping("/houtai/dept")
public class HouTai_Dept_Controller {

    @Autowired
    private DeptService deptService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private StaffService staffService;

    @RequestMapping("/manage")
    public ModelAndView manage(HttpSession session ) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/page/dept/dept_manage.html");

//        Map<String, Object> map =new HashMap<String, Object>();
//        List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
//        mav.addObject("warehouseList", warehouseList);

        recordService.add(session, 4, "打开部门列表");

        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/dept/add");
        mav.setViewName("/admin/page/car/add_update.html");

        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> staffList = staffService.list(map , 0, 100);
        mav.addObject("staffList",staffList);

        recordService.add(session, 4, "打开添加部门");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Dept dept = deptService.findId(id);
        mav.addObject("dept", dept);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/dept/update?id=" + id);
        mav.setViewName("/admin/page/car/add_update.html");

        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> staffList = staffService.list(map , 0, 100);
        mav.addObject("staffList",staffList);

        recordService.add(session, 4, "打开修改部门");

        return mav;
    }

}
