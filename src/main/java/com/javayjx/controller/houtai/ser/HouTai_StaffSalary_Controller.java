package com.javayjx.controller.houtai.ser;

import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.Staff;
import com.javayjx.entity.ser.StaffSalary;
import com.javayjx.service.ser.RecordService;
import com.javayjx.service.ser.StaffSalaryService;
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
 * @create 2020/9/26 0026 22:28
 */
@Controller
@RequestMapping("/houtai/staffSalary")
public class HouTai_StaffSalary_Controller {


    @Autowired
    private RecordService recordService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffSalaryService staffSalaryService;

    @RequestMapping("/manage")
    public ModelAndView manage(HttpSession session ) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/page/staffSalary/staffSalary_manage.html");

//        Map<String, Object> map =new HashMap<String, Object>();
//        List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
//        mav.addObject("warehouseList", warehouseList);

//        recordService.add(session, 4, "打开部门列表");

        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/staffSalary/add");
        mav.setViewName("/admin/page/staffSalary/add_update.html");

        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> staffList = staffService.list(map , 0, 100);
        mav.addObject("staffList",staffList);

//        recordService.add(session, 4, "打开添加部门");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        StaffSalary staffSalary = staffSalaryService.findId(id);
        mav.addObject("staffSalary", staffSalary);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/staffSalary/update?id=" + id);
        mav.setViewName("/admin/page/staffSalary/add_update.html");

        Map<String, Object> map = new HashMap<String, Object>();
        List<Staff> staffList = staffService.list(map , 0, 100);
        mav.addObject("staffList",staffList);

//        recordService.add(session, 4, "打开修改部门");

        return mav;
    }

}
