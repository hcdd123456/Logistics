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
import com.javayjx.entity.ser.Car;
import com.javayjx.entity.ser.Goods;
import com.javayjx.entity.ser.Warehouse;
import com.javayjx.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.StorageDao;
import com.javayjx.entity.ser.Storage;
import com.javayjx.service.ser.StorageService;
import com.javayjx.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/storage")
public class Admin_Storage_Controller {
	
	@Resource
	private StorageDao storageDao ;
	@Resource
	private StorageService storageService ;
	
	/**
	 * /admin/storage/add
	 */
	@ResponseBody
	@RequestMapping("/add")
    @SysLog(modelType = "仓库模块",operateType = "添加",descrption = "添加")
	public JSONObject add(@Valid Storage storage    ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();

		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}
		else{
			storage.setNumber(DateUtil.formatDate(new Date(), "hhmmssSSSyyyyMMdd"));


			/*for(int i=0;i<storage.getNum();i++) {
				Storage temp = new Storage();
				temp.setNumber(storage.getNumber());
				storageDao.save(temp);
			}*/
			storageDao.save(storage);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/storage/update
	 */
	@ResponseBody
	@RequestMapping("/update")
    @SysLog(modelType = "仓库模块",operateType = "修改",descrption = "修改")
    public JSONObject update( @Valid  Storage storage        ,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			storageService.update(storage);
			result.put("success", true);
			result.put("msg", "更新成功");
			return result;
		}
	}
	
	/**
	 * /admin/storage/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param q    q
	 */
	@ResponseBody
	@RequestMapping("/list")
    @SysLog(modelType = "仓库模块",operateType = "查询",descrption = "查询")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "goodsId", required = false) Integer goodsId,
			@RequestParam(value = "warehouseId", required = false) Integer warehouseId,
			@RequestParam(value = "q", required = false) String q,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(goodsId!=null) {
			Goods goods= new Goods();
			goods.setId(goodsId);
			map.put("goods",  goods);
		}
		if(warehouseId!=null){
			Warehouse warehouse = new Warehouse();
			warehouse.setId(warehouseId);
			map.put("warehouse", warehouse);
		}
		/*if(StringUtil.isNotEmpty( q))
			map.put("q",  q);*/
		List<Storage> list = storageService.list(map, page-1, limit);
		long total = storageService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * /admin/storage/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
    @SysLog(modelType = "仓库模块",operateType = "删除",descrption = "删除")
    public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			storageDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	
	
}
