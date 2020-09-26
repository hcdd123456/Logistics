package com.javayjx.controller.houtai.ser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.javayjx.dao.ser.CarDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.RepairDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.Repair;
import com.javayjx.service.ser.CarService;
import com.javayjx.service.ser.RecordService;

@Controller
@RequestMapping("/houtai/repair")
public class HouTai_Repair_Controller {
	
	@Resource
	private CarService carService; 
	@Resource
	private RecordService recordService  ; 
	@Resource
	private RepairDao repairDao  ;

	@Resource
	private CarDao carDao;
	
	/**
	 * /houtai/repair/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/repair/repair_manage.html");
		
		Map<String, Object> map =new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 200);
		mav.addObject("carList", carList);
		
		recordService.add(session, 6, "打开维修管理");
		return mav;
	}

    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/repair.html");
        return mav;
    }
	
	/**
	 * /houtai/repair/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/repair/add");
		mav.setViewName("/admin/page/repair/add_update.html");


		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
		mav.addObject("carList",carList);
		recordService.add(session, 6, "打开 添加维修管理");
		return mav;
	}
	
	
	/**
	 * /houtai/repair/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Repair repair = repairDao.findId(id);
		mav.addObject("repair", repair);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/repair/update?id=" + id);
		mav.setViewName("/admin/page/repair/add_update.html");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Car> carList = carService.list(map , 0, 100);
		
		mav.addObject("carList",carList);
		recordService.add(session, 6, "打开 修改维修管理 ");

		return mav;
	}
	
	
	
}
