package com.javayjx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.javayjx.dao.base.MenuDao;
import com.javayjx.entity.base.Menu;
import com.javayjx.entity.base.RoleMenu;
import com.javayjx.entity.base.User;
import com.javayjx.service.base.MenuService;
import com.javayjx.service.base.RoleMenuService;
import com.javayjx.util.BrowserUtil;
import net.sf.json.JSONObject;


@Controller
public class IndexController {
	
	@Resource
	private RoleMenuService roleMenuService;
	@Resource
	private MenuService menuService;
	@Resource
	private MenuDao menuDao;
	 
	@Autowired 
	private ServletContext servletContext;
	
	/**
	 *# 请求首页  
	 */
	@RequestMapping("/")
	public String  index_1(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		return "redirect:/login";
	}
	
	/**
	 *   #请求首页  /index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		return "redirect:/login";
	}
	
	
	/**
	 *    /login
	 *    #后台 用户电脑登陆
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		String UserAgent = req.getHeader("User-Agent");
		//判断AppleWebKit 和  Firefox    
		if(BrowserUtil.checkUserAgent(UserAgent)){
			mav.setViewName("/pc/login/login2");
		}else{
			mav.setViewName("/common/s_mode");
		}
		return mav;
	}
	
	
	
	/**
	 * 后台主页
	 */
	@RequestMapping("/admin/main")
	public ModelAndView admin_main(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		String UserAgent = req.getHeader("User-Agent");
		if(BrowserUtil.checkUserAgent(UserAgent)){
			mav.setViewName("/admin/main");
		}else{
			mav.setViewName("/common/s_mode");
			return mav;
		}
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"); 
		if(currentUser.getRole()==null){
			return mav;
		}
		
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", -1);
		List<Menu> menuList = menuService.list(map , 0, 200);
		for (Menu menu : menuList) {
			RoleMenu roleMenu=	roleMenuService.findByRoleIdAndMenuId(currentUser.getRole().getId(), menu.getId());
			if(roleMenu!=null){
				JSONObject node = new JSONObject();
				node.put("id", menu.getId());
				node.put("text", menu.getName());
				node.put("url", menu.getUrl());
				node.put("type", menu.getType());
				node.put("icon", menu.getIcon());
				node.put("divId", menu.getDivId());
				node.put("children", getChildren(menu.getId(),currentUser.getRole().getId()));
				list.add(node);
			}
		}
		mav.addObject("list", list);
		return mav;
	}
	
	/**
	 * 辅助方法  辅助 上面的 admin_main（getCheckedTreeMenu）
	 * @param pId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> getChildren(Integer pId, Integer roleId)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", pId);
		List<Menu> menuList = menuService.list(map , 0, 100);
		
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		for (Menu menu : menuList) {
			RoleMenu roleMenu=	roleMenuService.findByRoleIdAndMenuId(roleId, menu.getId());
			if(roleMenu!=null){
				JSONObject node = new JSONObject();
				node.put("id", menu.getId());
				node.put("text", menu.getName());
				node.put("url", menu.getUrl());
				node.put("type", menu.getType());
				node.put("icon", menu.getIcon());
				node.put("divId", menu.getDivId());
				list.add(node);
			}
		}
		return list;
	}
	
	 
	
}
