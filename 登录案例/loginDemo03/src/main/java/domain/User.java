package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 在实体类中的基本数据类型最好定义成对应的包装类
 */
public class User implements Serializable {

    //最好用包装类：template.queryForObject如果找不到会
    private Integer id;
    private String username;
    private String password;
//    private String gender;
    private Date insertTime;

//    public String getHehe() {
//        return gender;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", insertTime=" + insertTime +
                '}';
    }

//    @Override
/*    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", insertTime=" + insertTime +
                '}';
    }*/

//    public void setHehe(String gender) {
//        this.gender = gender;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

}