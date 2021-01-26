package Domain;

import java.io.Serializable;

public class User implements Serializable {
    //知识点，User类和数据库字段须保持一致
    private int id;
    private String name;
    private String sex;
    private Integer age;
    private String place_of_birth;
    private String qq_number;
    private String email;

    public User() {
    }

    public User(String name, String sex, Integer age, String place_of_birth, String qq_number, String email) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.place_of_birth = place_of_birth;
        this.qq_number = qq_number;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", qq_number='" + qq_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getQq_number() {
        return qq_number;
    }

    public void setQq_number(String qq_number) {
        this.qq_number = qq_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}


//    CREATE TABLE USER(
//        id INT PRIMARY KEY AUTO_INCREMENT,
//        name VARCHAR(32) UNIQUE NOT NULL,
//    sex VARCHAR(32) NOT NULL,
//    age VARCHAR(32) NOT NULL,
//    place_of_birth VARCHAR(32) NOT NULL,
//    qq_number VARCHAR(32) NOT NULL,
//    email VARCHAR(32) NOT NULL
//);

//   insert into user_info (name,age,sex,place_of_birth,qq_number,email) VALUES("luqi","male","20","aaa","aaa","aaa");
//   insert into user_info VALUES(NULL,"luq2","male","20","aaa","aaa","aaa");
//注意最后一行SQL语句没有;
//    CREATE TABLE ADMINISTRATOR(
//        id INT PRIMARY KEY AUTO_INCREMENT,
//        username VARCHAR(32) UNIQUE NOT NULL,
//    password VARCHAR(32) NOT NULL
//);