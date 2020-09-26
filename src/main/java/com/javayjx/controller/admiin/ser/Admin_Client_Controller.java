package com.javayjx.controller.admiin.ser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.javayjx.annotation.SysLog;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.ClientDao;
import com.javayjx.entity.ser.Client;
import com.javayjx.service.ser.ClientService;
import com.javayjx.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/client")
public class Admin_Client_Controller {
	

	@Resource
	private ClientDao clientDao     ;
	@Resource
	private ClientService clientService     ;
	
	/**
	 * /admin/client/add
	 */
	@ResponseBody
	@RequestMapping("/add")
    @SysLog(modelType = "客户模块",operateType = "添加",descrption = "添加")
	public JSONObject add(@Valid Client client     ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			clientDao.save(client);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/client/update
	 */
	@ResponseBody
	@RequestMapping("/update")
    @SysLog(modelType = "客户模块",operateType = "修改",descrption = "修改")
	public JSONObject update( @Valid Client client     ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			clientService.update(client);
			result.put("success", true);
			result.put("msg", "更新成功");
			return result;
		}
	}
	
	/**
	 * /admin/client/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param q    q
	 */
	@ResponseBody
	@RequestMapping("/list")
    @SysLog(modelType = "客户模块",operateType = "查询",descrption = "查询")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "q", required = false) String q, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty( q))
			map.put("q",  q);
		List<Client> list = clientService.list(map, page-1, limit);
		long total = clientService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /admin/client/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
    @SysLog(modelType = "客户模块",operateType = "删除",descrption = "删除")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			clientDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	
	
	
	
}
