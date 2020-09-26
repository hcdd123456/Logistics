package com.javayjx.controller.houtai.ser;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.CarDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.service.ser.RecordService;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/houtai/car")
public class HouTai_Car_Controller {
	
	
	@Resource
	private CarDao carDao  ; 
	@Resource
	private RecordService recordService  ; 
	
	
	
	
	/**
	 * /houtai/car/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		

		recordService.add(session, 3, "打开车辆信息");
		
		mav.setViewName("/admin/page/car/car_manage.html");
		return mav;
	}
	
	
	/**
	 * /houtai/car/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/car/add");
		mav.setViewName("/admin/page/car/add_update.html");
		
		recordService.add(session, 3, "打开添加车辆");
		
		return mav;
	}

    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/car.html");
        return mav;
    }
	
	
	/**
	 * /houtai/car/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Car car = carDao.findId(id);
		mav.addObject("car", car);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/car/update?id=" + id);
		mav.setViewName("/admin/page/car/add_update.html");
		

		recordService.add(session, 3, "打开修改车辆");
		
		
		return mav;
	}
	
	
	
	
}
