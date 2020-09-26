package com.javayjx.controller.houtai.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/houtai/config")
public class HouTai_Config_Controller {
	
	
	/**
	 * /houtai/config/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/config/config_manage");
		return mav;
	}
	
	
	
}
