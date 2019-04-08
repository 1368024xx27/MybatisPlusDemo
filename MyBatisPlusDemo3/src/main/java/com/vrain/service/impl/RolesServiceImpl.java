package com.vrain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vrain.dto.PageResultDto;
import com.vrain.dto.PageStatusEnumDto;
import com.vrain.entity.Roles;
import com.vrain.mapper.RolesMapper;
import com.vrain.service.RolesService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vrain
 * @since 2019-04-03
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    //获取角色列表
    @Override
    public PageResultDto<Roles> findRolesList(int parentId) throws Exception {
    	QueryWrapper<Roles> wrapper = new 	QueryWrapper<>();
    	wrapper.eq("roles_parent", parentId);
        List<Roles> rolesList = baseMapper.selectList(wrapper);
        return new PageResultDto<Roles>(PageStatusEnumDto.SUCCESS,rolesList,rolesList.size());
    }
}
