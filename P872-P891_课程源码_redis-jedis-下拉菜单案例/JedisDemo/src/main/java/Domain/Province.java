package Domain;

public class Province {
    private Integer id;
    private String province_name;//必须和数据库的字段一模一样，否则会出现[{"id":3,"province_name":"null"},{"id":2,"province_name":"null"},{"id":1,"province_name":"null"}]

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", province_name='" + province_name + '\'' +
                '}';
    }
}
