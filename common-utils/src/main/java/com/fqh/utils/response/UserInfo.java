package com.fqh.utils.response;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 *
 * @author fqh
 * @date 2022/08/14
 */
@Data
public class UserInfo {

    /**
     * 用户Id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别[0-不详 1-男  2-女]
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 认证信息[对应认证信息表Id,扩展使用]
     */
    private Long authInformationId;

    /**
     * 详细信息[扩展使用]
     */
    private Long detailInformationId;

    /**
     * 最后登录时间[冗余字段，扩展使用]
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public UserInfo() {

    }

    public UserInfo(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.sex = builder.sex;
        this.birthday = builder.birthday;
        this.nickName = builder.nickName;
        this.headPortrait = builder.headPortrait;
        this.introduction = builder.introduction;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    /**
     * 自定义build
     *
     * @author ouhaiqing
     * @date 2022-09-02
     */
    public static class Builder {

        private Long id;

        /**
         * 用户账号
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * 性别[0-不详 1-男  2-女]
         */
        private Integer sex;

        /**
         * 出生日期
         */
        private Date birthday;

        /**
         * 昵称
         */
        private String nickName;

        /**
         * 头像
         */
        private String headPortrait;

        /**
         * 个人简介
         */
        private String introduction;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 更新时间
         */
        private Date updateTime;

        public Builder() {
            // TODO document why this constructor is empty
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setSex(Integer sex) {
            this.sex = sex;
            return this;
        }

        public Builder setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
            return this;
        }

        public Builder setIntroduction(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public Builder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }
    }

}
