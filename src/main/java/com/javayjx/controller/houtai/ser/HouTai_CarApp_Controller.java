package com.javayjx.controller.houtai.ser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.CarAppDao;
import com.javayjx.dao.ser.StaffDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.CarApp;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.CarService;
import com.javayjx.service.ser.RecordService;
import com.javayjx.service.ser.StaffService;




@Controller
@RequestMapping("/houtai/car/app")
public class HouTai_CarApp_Controller {
		
	@Resource
	private CarAppDao  carAppDao ; 
	@Resource
	private CarService carService; 
	@Resource
	private RecordService recordService  ; 
	@Resource
	private StaffService staffService  ; 
	@Resource
	private StaffDao  staffDao ; 
	
	
	/**
	 * /houtai/car/app/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/car_app/car_app_manage.html");
		
		Map<String, Object> map =new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 200);
		mav.addObject("carList", carList);
		
		List<Staff> staffList  = staffDao.findAll();
		mav.addObject("staffList", staffList);
		
		recordService.add(session, 6, "打开车辆申请");
		return mav;
	}
	
	
	/**
	 * /houtai/car/app/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/car/app/add");
		mav.setViewName("/admin/page/car_app/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
		List<Staff> staffList =staffService.list(map,  0, 100);
		
		mav.addObject("staffList",staffList);
		mav.addObject("carList",carList);
		recordService.add(session, 6, "打开 添加车辆申请");

		return mav;
	}
	
	
	/**
	 * /houtai/car/app/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		CarApp carApp = carAppDao.findId(id);
		mav.addObject("carApp", carApp);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/car/app/update?id=" + id);
		mav.setViewName("/admin/page/car_app/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
		List<Staff> staffList =staffService.list(map,  0, 100);
		
		mav.addObject("staffList",staffList);
		mav.addObject("carList",carList);
		recordService.add(session, 6, "打开 修改车辆申请  ");

		return mav;
	}
	
	
	
	
}
