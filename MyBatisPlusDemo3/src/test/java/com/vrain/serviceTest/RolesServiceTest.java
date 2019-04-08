package com.vrain.serviceTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vrain.dto.PageResultDto;
import com.vrain.entity.Roles;
import com.vrain.service.RolesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class RolesServiceTest{

	@Autowired
	private RolesService rolesServece;
	
	@Test
	public void testFindRolesList() throws Exception {
		PageResultDto<Roles> pageResult = rolesServece.findRolesList(0);
		List<Roles> rolesList = pageResult.getInfoList();
		for (Roles roles : rolesList) {
			System.out.println(roles);
		}
	}
	
	
}
