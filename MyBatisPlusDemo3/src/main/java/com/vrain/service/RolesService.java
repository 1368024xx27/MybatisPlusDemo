package com.vrain.service;

import com.vrain.dto.PageResultDto;
import com.vrain.entity.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vrain
 * @since 2019-04-03
 */
public interface RolesService extends IService<Roles> {

    /**
     * 获取角色列表
     * @throws Exception
     */
    public PageResultDto<Roles> findRolesList(int parentId) throws Exception;

}
