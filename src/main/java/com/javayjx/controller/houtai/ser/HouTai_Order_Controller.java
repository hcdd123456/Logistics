package com.javayjx.controller.houtai.ser;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.OrderDao;
import com.javayjx.entity.ser.Order;
import com.javayjx.service.ser.RecordService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/houtai/order")
public class HouTai_Order_Controller {

	@Resource
	private OrderDao orderDao  ;
	@Resource
	private RecordService recordService    ;
	@Resource
	private StaffService staffService;
	
	/**
	 * /houtai/order/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		recordService.add(session, 1, "打开订单列表");
		mav.setViewName("/admin/page/order/order_manage.html");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Staff> staffList = staffService.list(map , 0, 100);
		mav.addObject("staffList",staffList);

		return mav;
	}
	
	/**
	 * /houtai/order/map
	 */
	@RequestMapping("/map")
	public ModelAndView map(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		recordService.add(session, 6, "打开最优路线");
		mav.setViewName("/admin/page/order/map.html");
		return mav;
	}
	
	
	
	/**
	 * 
	 * 
	 * /houtai/order/center
	 */
	@RequestMapping("/center")
	public ModelAndView center(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		recordService.add(session, 1, "打开接单中心");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Staff> staffList = staffService.list(map , 0, 100);
		mav.addObject("staffList",staffList);
		
		mav.setViewName("/admin/page/order/order_center.html");
		return mav;
	}

    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/order.html");
        return mav;
    }
	
	
	
	
	/**
	 * /houtai/order/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		recordService.add(session, 1, "打开新增订单");
		
		
		mav.setViewName("/admin/page/order/add_update.html");
		mav.addObject("btn_text", "添加");
		mav.addObject("edit", false);
		mav.addObject("save_url", "/admin/order/add");
		return mav;
	}
	
	
	/**
	 * /houtai/order/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Order order = orderDao.findId(id);
		
		recordService.add(session, 1, "打开修改订单");

		
		mav.addObject("order", order);
		mav.addObject("btn_text", "修改");
		mav.addObject("edit", true);
		mav.addObject("save_url", "/admin/order/update?id=" + id);
		mav.setViewName("/admin/page/order/add_update.html");
		return mav;
	}
	
	
	/**
	 * /houtai/order/receiver?id=1
	 */
	@RequestMapping("/receiver")
	public ModelAndView receiver(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "提交");
		mav.addObject("save_url", "/admin/order/update2?id=" + id);
		mav.setViewName("/admin/page/order/receiver.html");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Staff> staffList = staffService.list(map , 0, 100);
		mav.addObject("staffList",staffList);

		return mav;
	}
	
	
	
	
	/**
	 * /houtai/order/set/map?id=1
	 */
	@RequestMapping("/set/map")
	public ModelAndView set_map(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("save_url", "/admin/order/update2?id=" + id);
		mav.setViewName("/admin/page/order/set_map.html");
		return mav;
	}
	
	/**
	 * /houtai/order/see/map?id=1
	 */
	@RequestMapping("/see/map")
	public ModelAndView see_map(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Order order = orderDao.findId(id);
		mav.addObject("order", order);
		
		mav.setViewName("/admin/page/order/see_map.html");
		return mav;
	}
	
	/**
	 * 打印订单
	 * /houtai/order/print?id=1
	 */
	@RequestMapping("/print")
	public ModelAndView print(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Order order = orderDao.findId(id);
		mav.addObject("order", order);
		mav.setViewName("/admin/page/order/print.html");
		return mav;
	}
	
	
}
