package com.javayjx.controller;


import com.javayjx.entity.base.User;
import com.javayjx.entity.ser.KeyBoard;
import com.javayjx.entity.ser.KeyBoardInfo;
import com.javayjx.entity.ser.Order;
import com.javayjx.entity.ser.Staff;
import com.javayjx.service.ser.KeyBoardService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author l1nker4
 */
@Controller
@RequestMapping("keyboard")
public class KeyBoardController {

    @Autowired
    private KeyBoardService keyBoardService;

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @GetMapping("add")
    public String addKeyBoard(String value){
        HttpSession session = request.getSession();
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        if (currentUser == null){
            return "no";
        }
        keyBoardService.insert(value,currentUser.getName(),ip);
        return "yes";
    }

    @RequestMapping("/list")
    public ModelAndView manage(HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/page/keyboard/list.html");

        Map<String, Object> map = new HashMap<String, Object>();
//        List<Staff> staffList = keyBoardService.list(map , 0, 100);
        mav.addObject("staffList",null);

        return mav;
    }

    @ResponseBody
    @RequestMapping("/list1")
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
        List<KeyBoard> list = keyBoardService.list(map, page-1, limit);
        long total = keyBoardService.getTotal(map);
        map.clear();
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData() {
        return keyBoardService.getReport();
    }

    @RequestMapping(value = "/getData1")
    @ResponseBody
    public List<KeyBoardInfo> getData1() {
        return keyBoardService.getData();
    }
}
