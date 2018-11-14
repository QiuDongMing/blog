package com.codermi.blog.user.enums;

/**
 * @author qiudm
 * @date 2018/11/14 15:06
 * @desc 权限相关
 */
public class PermissionEnums {


    /**
     * 权限类型
     */
    public enum PermissionType {

        DIRECTORY(1, "目录"),
        MENU(2, "菜单"),
        BUTTON(3, "按钮");

        private int type;
        private String name;

        PermissionType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }
    }














}
