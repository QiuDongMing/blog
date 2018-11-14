package com.codermi.blog.user.enums;

/**
 * @author qiudm
 * @date 2018/9/6 15:08
 * @desc
 */
public class UserEnums {


    public enum UserRole {
        ROLE_USER;//普通用户
    }


    /**
     * 用户状态
     */
    public enum UserStatus {

        NORMAL(1, "正常"),
        DISABLED(2, "禁用");

        private Integer status;
        private String desc;
        UserStatus(Integer status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public Integer getStatus() {
            return status;
        }
    }

    /**
     * 用户类型
     */
    public enum UserType {

        ADMIN(0, "管理员"),
        USER(1, "普通用户");

        private Integer type;
        private String desc;
        UserType(Integer type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public Integer getType() {
            return type;
        }
    }





}
