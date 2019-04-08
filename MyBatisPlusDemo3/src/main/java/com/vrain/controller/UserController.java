package com.vrain.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vrain.dto.PageResultDto;
import com.vrain.dto.PageStatusEnumDto;
import com.vrain.dto.RequestPageDto;
import com.vrain.entity.Roles;
import com.vrain.entity.User;
import com.vrain.service.RolesService;
import com.vrain.service.UserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author vrain
 * @since 2019-04-02
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RolesService rolesService;

	/**
	 * 获取用户列表
	 * 
	 * @param requestPage
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserList(RequestPageDto requestPage, User user) throws UnsupportedEncodingException {
		Map<String, Object> modelMap = new HashMap<>();
		System.out.println("传入的用户信息为：" + user);
		System.out.println("传入的分页信息为：" + requestPage);
		if (user != null) {
			//System.out.println(new String(user.getUserName().getBytes("iso8859-1"), "utf-8"));
		}
		try {
			PageResultDto<User> pageResult = userService.findUserList(requestPage, user);
			if (pageResult != null) {
				List<User> userList = pageResult.getInfoList();
				for (User u : userList) {
					Roles roles = rolesService.getById(u.getRolesId());
					u.setRolesName(roles.getRolesName());
				}
				modelMap.put("total", pageResult.getCount());
				modelMap.put("rows", userList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userAdd(User user) {
		Map<String, Object> modelMap = new HashMap<>();
		if (user != null) {
			// 传入的user用户是不是空值
			boolean result = user.insert();
			if (result) {
				modelMap.put("success", true);
			} else {
				modelMap.put("msg", PageStatusEnumDto.INNER_ERROR.getStateInfo());
			}
		} else {
			// 传入的user用户是空值
			modelMap.put("success", false);
			modelMap.put("msg", PageStatusEnumDto.EMPTY.getStateInfo());
		}
		return modelMap;
	}

}
