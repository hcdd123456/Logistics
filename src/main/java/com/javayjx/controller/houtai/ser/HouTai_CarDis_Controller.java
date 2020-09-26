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

import com.javayjx.dao.ser.CarDisDao;
import com.javayjx.dao.ser.StaffDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.CarDis;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.CarService;
import com.javayjx.service.ser.RecordService;
import com.javayjx.service.ser.StaffService;



@Controller
@RequestMapping("/houtai/car/dis")
public class HouTai_CarDis_Controller {
	
	@Resource
	private CarDisDao carDisDao      ; 
	@Resource
	private CarService carService    ; 
	@Resource
	private RecordService recordService  ; 
	@Resource
	private StaffService staffService  ; 
	@Resource
	private StaffDao  staffDao ; 
	
	/**
	 * /houtai/car/dis/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/car_dis/car_dis_manage.html");		
		recordService.add(session, 6, "打开派遣车辆");

		Map<String, Object> map =new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 200);
		mav.addObject("carList", carList);
		
		List<Staff> staffList  = staffDao.findAll();
		mav.addObject("staffList", staffList);
		
		return mav;
	}
	
	
	/**
	 * /houtai/car/dis/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/car/dis/add");
		mav.setViewName("/admin/page/car_dis/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
			List<Staff> staffList =staffService.list(map,  0, 100);
		
		mav.addObject("staffList",staffList);
		
		mav.addObject("carList",carList);
		recordService.add(session, 6, "添加派遣车辆");

		return mav;
	}
	
	/**
	 * /houtai/car/dis/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		CarDis carDis = carDisDao.findId(id);
		mav.addObject("carDis", carDis);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/car/dis/update?id=" + id);
		mav.setViewName("/admin/page/car_dis/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
		List<Staff> staffList =staffService.list(map,  0, 100);
		mav.addObject("staffList",staffList);
		mav.addObject("carList",carList);
		recordService.add(session, 6, "修改派遣车辆");

		return mav;
	}
	
	
	
	
}
