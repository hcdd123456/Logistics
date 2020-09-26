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
import com.javayjx.dao.ser.CarUseDao;
import com.javayjx.dao.ser.StaffDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.CarUse;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.CarService;
import com.javayjx.service.ser.RecordService;



@Controller
@RequestMapping("/houtai/car/use")
public class HouTai_CarUse_Controller {
	
	@Resource
	private CarUseDao carUseDao    ; 
	@Resource
	private CarService carService    ; 
	@Resource
	private RecordService recordService  ; 
	@Resource
	private StaffDao staffDao ; 
	
	
	/**
	 * /houtai/car/use/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/car_use/car_use_manage.html");
		
		recordService.add(session, 6, "打开用车登记");


		Map<String, Object> map =new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 200);
		mav.addObject("carList", carList);

		List<Staff> staffList  = staffDao.findAll();
		mav.addObject("staffList", staffList);
		
		
		return mav;
	}


    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/carUse.html");
        return mav;
    }
	
	
	/**
	 * /houtai/car/use/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/car/use/add");
		mav.setViewName("/admin/page/car_use/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();

		List<Car> carList = carService.list(map , 0, 100);
		mav.addObject("carList",carList);
		recordService.add(session, 6, "添加 用车登记");
		
		List<Staff> staffList = staffDao.findAll();
		mav.addObject("staffList",staffList);
		
		
		return mav;
	}
	
	
	/**
	 * /houtai/car/use/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		CarUse carUse = carUseDao.findId(id);
		mav.addObject("carUse", carUse);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/car/use/update?id=" + id);
		mav.setViewName("/admin/page/car_use/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
		mav.addObject("carList",carList);
		
		List<Staff> staffList = staffDao.findAll();
		mav.addObject("staffList",staffList);
		
		
		recordService.add(session, 6, "修改 用车登记");

		return mav;
	}
	
	
	
	
}
