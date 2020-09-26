package com.javayjx.controller.admiin.ser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javayjx.dao.ser.RecordDao;
import com.javayjx.entity.ser.Record;
import com.javayjx.service.ser.RecordService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/record")
public class Admin_Record_Controller {

	@Resource
	private RecordDao recordDao;

	@Resource
	private RecordService recordService;

	/**
	 * /admin/record/list
	 * 
	 * @param page  默认1
	 * @param limit 数据多少
	 * @param q     q
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "type", required = false) Integer type, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<Record> list = recordService.list(map, page - 1, limit);
		long total = recordService.getTotal(map);
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	/**
	 * /admin/record/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			recordDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}

}
