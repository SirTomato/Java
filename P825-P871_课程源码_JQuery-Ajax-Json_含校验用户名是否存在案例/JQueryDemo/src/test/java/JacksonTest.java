import Domain.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JacksonTest {
    //java对象转为JSON字符串

    @Test
    public void test1() throws IOException {
        //创建Person对象
        Person person = new Person();
        person.setName("红发");
        person.setAge(38);
        person.setGender("男");
        person.setBirthday(new Date());
        //Person{name='红发', gender='男', age=38, birthday=Tue Feb 16 17:00:06 CST 2021}
        System.out.println(person);

        //创建jackson的核心对象---ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //转换
        String json = mapper.writeValueAsString(person);
        //{"name":"红发","gender":"男","age":38,"birthday":1613466006974}//Person类中没有添加@JsonFormat注解
        //{"name":"红发","gender":"男","age":38,"birthday":"2021-02-16"}//Person类中已添加@JsonFormat注解
        System.out.println(json);
        //writeValue将数据写到指定文件中
        mapper.writeValue(new File("d://a.txt"),person);
        //writeValue将数据关联到Write中
        mapper.writeValue(new FileWriter("d://b.txt"),person);


        //复杂java对象转换
        //创建List集合
        ArrayList<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person);

        //转换
        String s = mapper.writeValueAsString(people);
        //[{"name":"红发","gender":"男","age":38,"birthday":"2021-02-16"},
        // {"name":"红发","gender":"男","age":38,"birthday":"2021-02-16"}]
        System.out.println(s);

        //创建map集合
        HashMap<String, Person> map = new HashMap<>();
        map.put("1",person);
        map.put("2",person);

        //转换
        String s1 = mapper.writeValueAsString(map);
        //{"1":{"name":"红发","gender":"男","age":38,"birthday":"2021-02-16"},
        // "2":{"name":"红发","gender":"男","age":38,"birthday":"2021-02-16"}}
        System.out.println(s1);
    }
    @Test
    public void test2() throws IOException {
        String json = "{\"name\":\"红发\",\"gender\":\"男\",\"age\":38,\"birthday\":\"2021-02-16\"}";
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        //Person{name='红发', gender='男', age=38, birthday=Tue Feb 16 08:00:00 CST 2021}
        System.out.println(person);
    }
}
