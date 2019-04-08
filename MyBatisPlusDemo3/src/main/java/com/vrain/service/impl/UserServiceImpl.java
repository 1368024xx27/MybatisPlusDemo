package com.vrain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vrain.dto.PageResultDto;
import com.vrain.dto.PageStatusEnumDto;
import com.vrain.dto.RequestPageDto;
import com.vrain.entity.User;
import com.vrain.mapper.UserMapper;
import com.vrain.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vrain
 * @since 2019-04-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${USER_DEFAULT_PAGE_NUM}")
    private String USER_DEFAULT_PAGE_NUM;
    @Value("${USER_DEFAULT_PAGE_SIZE}")
    private String USER_DEFAULT_PAGE_SIZE;

    @Override
    public PageResultDto<User> findUserList(RequestPageDto requestPage, User user) throws Exception {
        int pageNum = Integer.parseInt(USER_DEFAULT_PAGE_NUM);
        int pageSize = Integer.parseInt(USER_DEFAULT_PAGE_SIZE);
        String order = null;
        String sort = null;
        //空值判断
        if (requestPage != null) {
            if (requestPage.getPage() > 0) {
                pageNum = requestPage.getPage();
            }
            if (requestPage.getRows() > 0) {
                pageSize = requestPage.getRows();
            }
            if (requestPage.getOrder() != null) {
                order = requestPage.getOrder();
            }
            if (requestPage.getSort() != null) {
                sort = requestPage.getSort();
            }
        }
        //设置查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        if (user != null) {
            String userName = user.getUserName();
            Integer rolesId = user.getRolesId();
            String userAddress = user.getUserAddress();
            Integer userAgeStart = user.getUserAgeStart();
            Integer userAgeEnd = user.getUserAgeEnd();
            if (null != userName && !userName.trim().equals("")) {
                wrapper.like("user_name", userName);
            }
            if (null != rolesId && rolesId > 0) {
                wrapper.eq("roles_id", rolesId);
            }
            if (null != userAddress && !userAddress.trim().equals("")) {
                wrapper.like("user_address", userAddress);
            }
            if (null != userAgeStart && null == userAgeEnd) {
                if (userAgeStart > 0) {
                    //有开始年龄，没有截止年龄，则是查大于开始年龄的用户
                    wrapper.gt("user_age", userAgeStart);
                }
            } else if (null == userAgeStart && null != userAgeEnd) {
                if (userAgeEnd > 0) {
                    //没有开始年龄，有截止年龄，则是查小于截止年龄的用户
                    wrapper.lt("user_age", userAgeEnd);
                }
            } else if (null != userAgeStart && null != userAgeEnd) {
                if (userAgeStart > 0 && userAgeEnd > 0) {
                    //两个年龄都输入了，则要比较大小，保证在两个年龄之间
                    if (userAgeStart > userAgeEnd) {
                        userAgeEnd = userAgeStart + userAgeEnd;
                        userAgeStart = userAgeEnd - userAgeStart;
                        userAgeEnd = userAgeEnd - userAgeStart;
                    }
                    wrapper.between("user_age", userAgeStart, userAgeEnd);
                }
            }
        }
        //设置排序
        if(order!=null){
            boolean isAsc = true;
            if(sort!=null){
                if(!sort.toLowerCase().equals("asc")){
                    isAsc=false;
                }
            }
            wrapper.orderBy(true,isAsc,order);
        }
        //操作数据
        IPage<User> userPage = baseMapper.selectPage(new Page(pageNum, pageSize), wrapper);
        //封装结果，并返回
        if (userPage!=null){
            List<User> records = userPage.getRecords();
            long total = userPage.getTotal();
            if(records!=null&&records.size()>0){
                return new PageResultDto<User>(PageStatusEnumDto.SUCCESS,records,total);
            }else{
                return new PageResultDto<User>(PageStatusEnumDto.INNER_EMPTY);
            }
        }
        return new PageResultDto<User>(PageStatusEnumDto.INNER_ERROR);
    }

    @Override
    public PageResultDto findUser(int userId) throws Exception {
        return null;
    }
}
