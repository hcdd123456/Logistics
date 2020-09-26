package com.javayjx.controller.houtai.ser;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.GoodsDao;
import com.javayjx.entity.ser.Goods;
import com.javayjx.service.ser.RecordService;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/houtai/goods")
public class HouTai_Goods_Controller {
	
	@Resource
	private GoodsDao goodsDao   ; 
	@Resource
	private RecordService recordService  ; 
	/**
	 * /houtai/goods/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/goods/goods_manage.html");
		
		recordService.add(session, 5, "打开货物管理");
		return mav;
	}

    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/goods.html");
        return mav;
    }

	/**
	 * /houtai/goods/main
	 */
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/goods/goods_main.html");

		recordService.add(session, 5, "打开财务可视化");

		return mav;
	}


	/**
	 * /houtai/goods/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/goods/add");
		mav.setViewName("/admin/page/goods/add_update.html");
		

		recordService.add(session, 5, "打开添加货物");

		return mav;
	}
	
	
	/**
	 * /houtai/goods/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Goods goods = goodsDao.findId(id);
		mav.addObject("goods", goods);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/goods/update?id=" + id);
		mav.setViewName("/admin/page/goods/add_update.html");

		recordService.add(session, 5, "打开修改货物");
		
		return mav;
	}
	
	
	
	
}
