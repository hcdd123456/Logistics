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
import com.javayjx.service.ser.StaffService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.CarUseDao;
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.CarUse;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.CarUseService;
import com.javayjx.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/car/use")
public class Admin_CarUse_Controller {
	
	@Resource
	private CarUseDao carUseDao         ;
	@Resource
	private CarUseService carUseService         ;

	@Resource
	private StaffService staffService;
	
	  @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	    }
	  
	  
	  
	/**
	 * /admin/car/use/add
	 */
	@ResponseBody
	@RequestMapping("/add")
    @SysLog(modelType = "用车申请模块",operateType = "添加",descrption = "添加")
    public JSONObject add(@Valid CarUse carUse ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{

//			Staff staff = staffService.findid(carUse.getStaff().getId());
//			staff.setAmount(staff.getAmount()+1);
//			staffService.update(staff);

			carUseDao.save(carUse);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/car/use/update
	 */
	@ResponseBody
	@RequestMapping("/update")
    @SysLog(modelType = "用车申请模块",operateType = "修改",descrption = "修改")
	public JSONObject update( @Valid CarUse carUse  ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{

			carUseService.update(carUse);
			result.put("success", true);
			result.put("msg", "更新成功");
			return result;
		}
	}

	/**
	 * /admin/car/use/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
    @SysLog(modelType = "用车申请模块",operateType = "删除",descrption = "删除")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			carUseDao.deleteById(Integer.parseInt(idsStr[i]));
		}

		Staff staff = staffService.findid(Integer.parseInt(ids));
		staff.setAmount(staff.getAmount()-1);
			staffService.update(staff);

		result.put("success", true);
		return result;
	}
	
	/**
	 * /admin/car/use/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param q    q
	 */
	@ResponseBody
	@RequestMapping("/list")
    @SysLog(modelType = "用车申请模块",operateType = "查询",descrption = "查询")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "carId", required = false) Integer carId, 
			@RequestParam(value = "staffId", required = false) Integer staffId, 
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
		
		
		
		
		List<CarUse> list = carUseService.list(map, page-1, limit);
		long total = carUseService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	
	
	
}
