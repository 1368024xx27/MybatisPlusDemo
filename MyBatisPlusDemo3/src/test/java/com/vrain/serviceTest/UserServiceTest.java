package com.vrain.serviceTest;

import com.vrain.dto.PageResultDto;
import com.vrain.dto.PageStatusEnumDto;
import com.vrain.dto.RequestPageDto;
import com.vrain.entity.User;
import com.vrain.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author vrain
 * @create 2019-04-02 14:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testFindUserList() throws Exception {
        //设置分页与排序
        RequestPageDto requestPage = new RequestPageDto();
        requestPage.setPage(1);
        requestPage.setRows(5);
        requestPage.setOrder("user_age");
        requestPage.setSort("desc");

        //设置查询条件
        User user = new User();
        user.setUserName("张");
        user.setUserAddress("大");
        user.setUserAgeStart(12);
        user.setUserAgeEnd(40);
        PageResultDto<User> pageResult = userService.findUserList(requestPage, null);

        if(pageResult!=null){
            if(pageResult.getState()== PageStatusEnumDto.SUCCESS.getState()){
                System.out.println("总记录数为："+pageResult.getCount());
                List<User> users = pageResult.getInfoList();
                for (User u : users) {
                    System.out.println(u);
                }
            }
        }
    }


}
