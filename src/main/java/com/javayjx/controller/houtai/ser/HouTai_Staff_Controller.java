package com.javayjx.controller.houtai.ser;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.StaffDao;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.RecordService;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/houtai/staff")
public class HouTai_Staff_Controller {
	
	
	@Resource
	private StaffDao staffDao    ; 
	@Resource
	private RecordService recordService  ; 
	
	
	/**
	 * /houtai/staff/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		recordService.add(session, 4, "打开员工列表");
		
		mav.setViewName("/admin/page/staff/staff_manage.html");
		return mav;
	}


    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/staff.html");
        return mav;
    }
	
	
	/**
	 * /houtai/staff/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/staff/add");
		mav.setViewName("/admin/page/staff/add_update.html");
		

		recordService.add(session, 4, "打开添加员工");
		
		return mav;
	}
	
	/**
	 * /houtai/staff/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Staff staff = staffDao.findId(id);
		mav.addObject("staff", staff);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/staff/update?id=" + id);
		mav.setViewName("/admin/page/staff/add_update.html");
		

		recordService.add(session, 4, "打开修改员工");
		
		return mav;
	}
	
	
	
	
}
