package com.vrain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author vrain
 * @since 2019-04-02
 */
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private String userAddress;
    private Integer userAge;
    private String userName;
    private Integer rolesId;
    private String password;

    @TableField(exist=false)
    private Integer userAgeStart;
    @TableField(exist=false)
    private Integer userAgeEnd;
    @TableField(exist=false)
    private String rolesName;

    public Integer getUserAgeStart() {
        return userAgeStart;
    }

    public void setUserAgeStart(Integer userAgeStart) {
        this.userAgeStart = userAgeStart;
    }

    public Integer getUserAgeEnd() {
        return userAgeEnd;
    }

    public void setUserAgeEnd(Integer userAgeEnd) {
        this.userAgeEnd = userAgeEnd;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userAddress='" + userAddress + '\'' +
                ", userAge=" + userAge +
                ", userName='" + userName + '\'' +
                ", rolesId=" + rolesId +
                ", password='" + password + '\'' +
                '}';
    }
}
