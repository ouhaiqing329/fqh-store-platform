package com.fqh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体
 *
 * @author ouhaiqing
 * @date 2022/9/5 18:12
 */
@Data
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 4764398179543364443L;

    /**
     * 角色Id
     */
    @TableId
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型
     */
    private String type;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色组成成员[多角色Id“,”分隔]
     */
    private String member;

    /**
     * 无法指定[MEMBER:人员|ROLE:角色|UNABLE:无法指定]
     */
    private String memberType;

    /**
     * 审批用户
     */
    private Long approveUser;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private Long createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新用户
     */
    private Long updateUser;

    /**
     * 删除标记
     */
    @TableLogic
    private Integer deleteMark;
}
