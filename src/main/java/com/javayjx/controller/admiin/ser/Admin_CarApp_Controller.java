package com.javayjx.controller.admiin.ser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.CarAppDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.CarApp;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.CarAppService;
import com.javayjx.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/car/app")
public class Admin_CarApp_Controller {
	
	@Resource
	private CarAppDao carAppDao       ;
	@Resource
	private CarAppService carAppService       ;
	
	  @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	    }
	  
	  
	  
	/**
	 * /admin/car/app/add
	 */
	@ResponseBody
	@RequestMapping("/add") 
	public JSONObject add(@Valid CarApp carApp ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			carAppDao.save(carApp);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/car/app/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update( @Valid  CarApp carApp     ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			carAppService.update(carApp);
			result.put("success", true);
			result.put("msg", "更新成功");
			return result;
		}
	}
	/**
	 * /admin/car/app/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param q    q
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "staffId", required = false) Integer staffId, 
			@RequestParam(value = "carId", required = false) Integer carId, 
			@RequestParam(value = "q", required = false) String q, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty( q))
			map.put("q",  q);
		
		if(carId!=null) {
			Car   car= new Car();
			car.setId(carId);
			map.put("car",  car);
		}
		if(staffId!=null) {
			Staff   staff= new Staff();
			staff.setId(staffId);
			map.put("staff",  staff);
		}
		List<CarApp> list = carAppService.list(map, page-1, limit);
		long total = carAppService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * /admin/car/app/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			carAppDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	
	
	
}
