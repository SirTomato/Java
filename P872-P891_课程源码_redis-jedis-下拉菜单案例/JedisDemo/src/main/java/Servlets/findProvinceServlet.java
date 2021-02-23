package Servlets;

import Service.Impl.ProvincesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findProvinceServlet")
public class findProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        //调用service查询
        ProvincesService service = new ProvincesService();
        List<Province> list = service.findAll();
*//*        //设置响应的数据格式为json
        response.setContentType("application/json;charset=utf-8");
        //序列化list为json
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),list);*//*


        //序列化list为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);*/


        //调用service中findAllJson方法
        ProvincesServiceImpl service = new ProvincesServiceImpl();
        String json = service.findAllJson();
        //响应结果
        //设置响应的数据格式为json
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
