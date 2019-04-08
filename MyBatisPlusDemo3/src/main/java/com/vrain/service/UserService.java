package com.vrain.service;

import com.vrain.dto.PageResultDto;
import com.vrain.dto.RequestPageDto;
import com.vrain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vrain
 * @since 2019-04-02
 */
public interface UserService extends IService<User> {

    /**
     * 分页、多条件获取用户列表
     * @param requestPage
     * @param user
     * @return
     * @throws Exception
     */
    public PageResultDto<User> findUserList(RequestPageDto requestPage, User user) throws Exception;

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    public PageResultDto<User> findUser(int userId) throws Exception;

}
