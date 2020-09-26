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

import com.javayjx.annotation.SysLog;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.CarDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.service.ser.CarService;
import com.javayjx.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/car")
public class Admin_Car_Controller {

	@Resource
	private CarDao carDao     ;
	@Resource
	private CarService carService     ;
	
	  @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	    }
	  
	  
	  
	/**
	 * /admin/car/add
	 */
	@ResponseBody
	@RequestMapping("/add")
    @SysLog(modelType = "车辆模块",operateType = "添加",descrption = "添加")
	public JSONObject add(@Valid Car  car       ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			carDao.save(car);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/car/update
	 */
	@ResponseBody
	@RequestMapping("/update")
    @SysLog(modelType = "车辆模块",operateType = "修改",descrption = "修改")
	public JSONObject update( @Valid  Car  car       ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			carService.update(car);
			result.put("success", true);
			result.put("msg", "更新成功");
			return result;
		}
	}
	
	/**
	 * /admin/car/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param q    q
	 */
	@ResponseBody
	@RequestMapping("/list")
    @SysLog(modelType = "车辆模块",operateType = "查询",descrption = "查询")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "q", required = false) String q, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty( q))
			map.put("q",  q);
		List<Car> list = carService.list(map, page-1, limit);
		long total = carService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /admin/car/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
    @SysLog(modelType = "车辆模块",operateType = "删除",descrption = "删除")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			carDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	
	
	
}
