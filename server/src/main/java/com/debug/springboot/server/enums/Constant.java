package com.debug.springboot.server.enums;/**
 * Created by Administrator on 2019/10/27.
 */

/**
 * 系统常量
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/27 11:42
 **/
public class Constant {

    public static final String FilePrefix="E:\\srv\\dubbo\\files\\technology\\"; // windows下路径
    //public static final String FilePrefix="/srv/dubbo/files/technology/";   //linux下路径

    public static final String FilePrefix2="E:\\srv\\dubbo\\files\\technology2\\"; // windows下路径

    //系统模块
    public enum SysModule{

        ModuleItem("Item","商品模块"),
        ModulePerson("Person","用户模块")

        ;

        private String code;
        private String name;

        SysModule(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}