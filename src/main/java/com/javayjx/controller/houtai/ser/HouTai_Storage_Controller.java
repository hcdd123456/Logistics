package com.javayjx.controller.houtai.ser;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.Goods;
import com.javayjx.entity.ser.Warehouse;
import com.javayjx.service.ser.GoodsService;
import com.javayjx.service.ser.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javayjx.dao.ser.StorageDao;
import com.javayjx.entity.ser.Storage;
import com.javayjx.service.ser.RecordService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/houtai/storage")
public class HouTai_Storage_Controller {
	
	@Resource
	private StorageDao storageDao ; 
	@Resource
	private RecordService recordService  ;

	@Resource
	private GoodsService goodsService;

	@Autowired
	private WarehouseService warehouseService;
	
	/**
	 * /houtai/storage/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(HttpSession session ) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/page/storage/storage_manage.html");

		Map<String, Object> map =new HashMap<String, Object>();
		List<Goods> goodsList = goodsService.list(map , 0, 200);
		List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
		mav.addObject("warehouseList", warehouseList);
		mav.addObject("goodsList", goodsList);

		recordService.add(session, 5, "打开货物列表");
		
		return mav;
	}
	
	/**
	 * /houtai/storage/add
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/storage/add");
		mav.setViewName("/admin/page/storage/add_update.html");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Goods> goodsList = goodsService.list(map , 0, 100);
		List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
		mav.addObject("warehouseList", warehouseList);
		mav.addObject("goodsList",goodsList);

		recordService.add(session, 5, "打开添加仓库");
		return mav;
	}


    @RequestMapping("/data")
    public ModelAndView data(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();

        mav.setViewName("/admin/page/data/storage.html");
        return mav;
    }
	
	/**
	 * /houtai/storage/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Storage storage = storageDao.findId(id);
		mav.addObject("storage", storage);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/storage/update?id=" + id);
		mav.setViewName("/admin/page/storage/add_update.html");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Goods> goodsList = goodsService.list(map , 0, 100);
		List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
		mav.addObject("warehouseList", warehouseList);
		mav.addObject("goodsList",goodsList);
		
		recordService.add(session, 5, "打开修改仓库");
		
		return mav;
	}
}
