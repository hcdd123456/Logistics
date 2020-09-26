package com.javayjx.controller.admiin.ser;

import com.javayjx.annotation.SysLog;
import com.javayjx.entity.ser.Dept;
import com.javayjx.entity.ser.Storage;
import com.javayjx.entity.ser.Warehouse;
import com.javayjx.service.ser.DeptService;
import com.javayjx.util.DateUtil;
import com.javayjx.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hc
 * @create 2020/9/26 0026 17:22
 */
@RestController
@RequestMapping("/admin/dept")
public class Admin_Dept_Controller {


    @Autowired
    private DeptService deptService;

    @RequestMapping("list")
    @SysLog(modelType = "员工模块",operateType = "查询",descrption = "查询")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "q", required = false) String q,
                                    HttpServletResponse response,
                                    HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtil.isNotEmpty( q)) {
            map.put("q", q);
        }
        List<Dept> list = deptService.list(map, page-1, limit);
        long total = deptService.getTotal(map);
        map.clear();
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @RequestMapping("/add")
    @SysLog(modelType = "员工模块",operateType = "添加",descrption = "添加")
    public JSONObject add(@Valid Dept dept    , BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();

        if(bindingResult.hasErrors()){
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        else{
            deptService.save(dept);
            result.put("success", true);
            result.put("msg", "添加成功");
            return result;
        }
    }

    @RequestMapping("/delete")
    @SysLog(modelType = "y员工模块",operateType = "删除",descrption = "删除")
    public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
            throws Exception {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            deptService.deleteById(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/update")
    @SysLog(modelType = "员工模块",operateType = "修改",descrption = "修改")
    public JSONObject update( @Valid  Dept dept        ,BindingResult bindingResult)throws Exception {
        JSONObject result = new JSONObject();
        if(bindingResult.hasErrors()){
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }else{
            deptService.update(dept);
            result.put("success", true);
            result.put("msg", "更新成功");
            return result;
        }
    }

}
