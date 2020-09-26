package com.javayjx.controller.houtai.ser;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.javayjx.dao.ser.ClientDao;
import com.javayjx.entity.ser.Client;
import com.javayjx.service.ser.RecordService;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/houtai/client")
public class HouTai_Client_Controller {

	@Resource
	private ClientDao clientDao;
	@Resource
	private RecordService recordService  ;

	/**
	 * /houtai/client/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();


		recordService.add(session, 2, "打开客户列表");

		mav.setViewName("/admin/page/client/client_manage.html");
		return mav;
	}

    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/client.html");
        return mav;
    }


	/**
	 * /houtai/client/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/client/add");
		mav.setViewName("/admin/page/client/add_update.html");


		recordService.add(session, 2, "打开添加客户");


		return mav;
	}

	/**
	 * 2019-11-24 start 在客户列表页面展示订单信息
	 */
	@RequestMapping("/show")
	public ModelAndView showOrder(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
//		System.out.println("id是"+id.toString());
		Client client = clientDao.findId(id);
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/client/add");
		mav.setViewName("/admin/page/client/showOrderList.html");
		recordService.add(session, 2, "打开添加客户");
		return mav;
	}


	/**
	 * /houtai/client/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Client client = clientDao.findId(id);
		mav.addObject("client", client);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/client/update?id=" + id);
		mav.setViewName("/admin/page/client/add_update.html");


		recordService.add(session, 2, "打开修改客户");


		return mav;
	}



}
