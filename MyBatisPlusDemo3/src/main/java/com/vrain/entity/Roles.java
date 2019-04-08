package com.vrain.entity;

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
 * @since 2019-04-03
 */
@TableName("tb_roles")
public class Roles extends Model<Roles> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roles_id", type = IdType.AUTO)
    private Integer rolesId;

    private String rolesName;

    private Integer rolesParent;
    
    
    public Integer getRolesParent() {
		return rolesParent;
	}

	public void setRolesParent(Integer rolesParent) {
		this.rolesParent = rolesParent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    protected Serializable pkVal() {
        return this.rolesId;
    }

	@Override
	public String toString() {
		return "Roles [rolesId=" + rolesId + ", rolesName=" + rolesName + ", rolesParent=" + rolesParent + "]";
	}

    
    
}
