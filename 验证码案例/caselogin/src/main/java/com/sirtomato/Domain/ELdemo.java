package com.sirtomato.Domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ELdemo {
    private String name;
    private String age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 逻辑视图
     * @return
     */

    public String getBirStr(){
        if (birthday!= null){
//            1、格式化日期对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            2、返回字符串
            return sdf.format(getBirthday());
        }
        return "";
    }

}
