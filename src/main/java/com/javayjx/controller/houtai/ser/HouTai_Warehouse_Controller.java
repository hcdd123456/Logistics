package com.javayjx.controller.houtai.ser;

import com.javayjx.entity.ser.Goods;
import com.javayjx.entity.ser.Storage;
import com.javayjx.entity.ser.Warehouse;
import com.javayjx.service.ser.RecordService;
import com.javayjx.service.ser.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/24 0024 14:58
 */
@Controller
@RequestMapping("/houtai/warehouse")
public class HouTai_Warehouse_Controller {
    @Autowired
    private WarehouseService warehouseService;
    @Resource
    private RecordService recordService  ;

    @ResponseBody
    @RequestMapping("selOne/{id}")
    public Warehouse selOne(@PathVariable Integer id){
        return warehouseService.selOne(id);
    }

    @RequestMapping("/manage")
    public ModelAndView manage(HttpSession session ) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/page/warehouse/warehouse_manage.html");

        Map<String, Object> map =new HashMap<String, Object>();
        List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
        mav.addObject("warehouseList", warehouseList);

        recordService.add(session, 5, "打开仓库列表");

        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/warehouse/add");
        mav.setViewName("/admin/page/warehouse/add_update.html");

        Map<String, Object> map =new HashMap<String, Object>();
        List<Warehouse> warehouseList = warehouseService.list(map , 0, 200);
        mav.addObject("warehouseList", warehouseList);

        recordService.add(session, 5, "打开添加仓库");

        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        Warehouse warehouse = warehouseService.selOne(id);
        mav.addObject("warehouse", warehouse);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/warehouse/update?id=" + id);
        mav.setViewName("/admin/page/warehouse/add_update.html");

//        Map<String, Object> map = new HashMap<String, Object>();
////        List<Goods> goodsList = goodsService.list(map , 0, 100);
////        mav.addObject("goodsList",goodsList);

        recordService.add(session, 5, "打开修改仓库");

        return mav;
    }

}
