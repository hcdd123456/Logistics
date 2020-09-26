package com.javayjx.controller.houtai.ser;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.CarDao;


@Controller
@RequestMapping("/houtai/record")
public class HouTai_Record_Controller {
	@Resource
	private CarDao carDao  ; 
	
	/**
	 * /houtai/record/manage?type=1
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(@RequestParam(value = "type", required = false) Integer type) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("type", type);
		mav.setViewName("/admin/page/record/record_manage.html");
		return mav;
	}
}
