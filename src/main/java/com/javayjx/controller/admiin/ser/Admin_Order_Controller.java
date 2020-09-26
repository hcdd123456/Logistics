package com.javayjx.controller.admiin.ser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.javayjx.annotation.SysLog;
import com.javayjx.entity.ser.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.OrderDao;
import com.javayjx.entity.ser.Order;
import com.javayjx.service.ser.OrderService;
import com.javayjx.util.DateUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/order")
public class Admin_Order_Controller {

	@Resource
	private OrderService orderService  ;
	@Resource
	private OrderDao orderDao  ;
	
	/**
	 * /admin/order/add
	 */
	@ResponseBody
	@RequestMapping("/add")
    @SysLog(modelType = "订单模块",operateType = "添加",descrption = "添加")
	public JSONObject add(@Valid Order order   ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			order.setCreateDateTime(new Date());
			order.setState(0);
			order.setNumber(DateUtil.formatDate(new Date(), "yyyyMMddhhmmssSSS"));
			orderDao.save(order);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/order/update
	 */
	@ResponseBody
	@RequestMapping("/update")
    @SysLog(modelType = "订单模块",operateType = "修改",descrption = "修改")
	public JSONObject update( @Valid Order order   ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			orderService.update(order);
			result.put("success", true);
			result.put("msg", "更新成功");
			return result;
		}
	}
	
	/**
	 * /admin/order/update2
	 */
	@ResponseBody
	@RequestMapping("/update2")
    @SysLog(modelType = "订单模块",operateType = "修改",descrption = "修改")
	public JSONObject update2(  Order order   )throws Exception {
		JSONObject result = new JSONObject();
		
		orderService.update(order);
		result.put("success", true);
		result.put("msg", "更新成功");
		return result;
	}
	
	
	/**
	 * /admin/order/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param q    q
	 */
	@ResponseBody
	@RequestMapping("/list")
    @SysLog(modelType = "订单模块",operateType = "查询",descrption = "查询")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "staffId", required = false) Integer staffId,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();


		if(staffId!=null) {
			Staff staff= new Staff();
			staff.setId(staffId);
			map.put("staff",  staff);
		}
		/*if(StringUtil.isNotEmpty( q))
			map.put("q",  q);*/
		List<Order> list = orderService.list(map, page-1, limit);
		long total = orderService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * /admin/order/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
    @SysLog(modelType = "订单模块",operateType = "删除",descrption = "删除")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			orderDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	
	
	
}
