package com.vrain.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vrain.dto.EasyUITreeNode;
import com.vrain.dto.PageStatusEnumDto;
import com.vrain.entity.Roles;
import com.vrain.service.RolesService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author vrain
 * @since 2019-04-03
 */
@RestController
@RequestMapping("/roles")
public class RolesController {
	@Autowired
	private RolesService rolesService;

	// 获取角色列表
	@RequestMapping(value = "rolesList", method = RequestMethod.GET)
	public List<EasyUITreeNode> getRolesList(Integer rolesParent) {
		List<EasyUITreeNode> nodeList = null;
		if (rolesParent == null) {
			rolesParent = 0;
		}
		try {
			QueryWrapper<Roles> wrapper = new QueryWrapper<>();
			wrapper.eq("roles_parent", rolesParent);
			List<Roles> rolesList = rolesService.list(wrapper);

			System.out.println("rolesList:" + rolesList);

			if (rolesList != null && rolesList.size() != 0) {
				nodeList = new ArrayList<>();
				for (Roles roles : rolesList) {
					EasyUITreeNode node = new EasyUITreeNode();
					node.setId(roles.getRolesId().longValue());
					node.setText(roles.getRolesName());
					node.setState("close");
					// 查询该支点下有没有子节点
					List<EasyUITreeNode> rolesList2 = getRolesList(roles.getRolesId());
					if (rolesList2 != null && rolesList2.size()>0) {
						node.setChildren(rolesList2);
					}

					nodeList.add(node);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nodeList);

		return nodeList;
	}

	/**
	 * 保存角色
	 * 
	 * @param roles
	 * @return
	 */
	@RequestMapping(value = "rolesAdd", method = RequestMethod.POST)
	public Map<String, Object> addRoles(Roles roles) {
		System.out.println("前台传入的角色信息：" + roles);
		Map<String, Object> modelMap = new HashMap<>();
		if (roles != null) {
			boolean result = rolesService.save(roles);
			System.out.println("执行添加角色sql之后，返回的主键是："+roles.getRolesId());
			if (result) {
				modelMap.put("success", true);
				modelMap.put("rolesId", roles.getRolesId());
			} else {
				modelMap.put("success", false);
				modelMap.put("msg", PageStatusEnumDto.INNER_ERROR.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("msg", PageStatusEnumDto.EMPTY.getStateInfo());
		}
		return modelMap;
	}

	/**
	 * 更新角色
	 * 
	 * @param roles
	 * @return
	 */
	@RequestMapping(value = "rolesUpdate", method = RequestMethod.POST)
	public Map<String, Object> rolesUpdate(Roles roles) {
		Map<String, Object> modelMap = new HashMap<>();
		UpdateWrapper<Roles> wrapper = new UpdateWrapper<>();
		if (roles != null && roles.getRolesId() > 0) {
			wrapper.eq("roles_id", roles.getRolesId());
			boolean update = rolesService.update(roles, wrapper);
			if (update) {
				modelMap.put("status", PageStatusEnumDto.SUCCESS.getState());
			}
		} else {
			modelMap.put("status", PageStatusEnumDto.EMPTY.getState());
		}
		return modelMap;
	}

	/**
	 * 删除角色
	 * @param rolesId
	 * @return
	 */
	@RequestMapping(value = "removeRoles", method = RequestMethod.POST)
	public Map<String, Object> removeRoles(Integer rolesId) {
		Map<String, Object> modelMap = new HashMap<>();
		
		System.out.println("传入要删除的角色id是:"+rolesId);
		
		if (rolesId!=null && rolesId > 0) {
			boolean result = rolesService.removeById(rolesId);
			if (result) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("msg", PageStatusEnumDto.INNER_ERROR.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("msg", PageStatusEnumDto.EMPTY.getStateInfo());
		}
		return modelMap;
	}

}
