package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //创建真实对象
        final Lenovo lenovo = new Lenovo();
        //动态代理增强lonevo对象，创建代理对象
        /**
         * 三个参数：
         * 1、类加载器：真实对象.getClass().getClassLoader()
         * 2、接口数组：真实对象.getClass().getInterfaces()
         * 3、处理器：new InvocationHandler()
         */
        SaleComputer proxy_lenovo = (SaleComputer)Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 编写代理逻辑的方法：代理对象调用任何方法时，都会触发该方法执行
             * @param proxy：代理对象
             * @param method：代理对象调用的方法，被封装的对象
             * @param args：代理对象调用方法时，传递的实际参数
             */

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //判断是否是sale方法
                if(method.getName().equals("sale")){
                    //增强参数
                    double money =(double) args[0];
                    money=money*0.85;
                    //使用真实对象调用该方法
//                    Object obj = method.invoke(lenovo,money);
                    String obj = (String)method.invoke(lenovo,money);
                    //增强返回值
                    return obj+"附送鼠标垫";
                }else {
                    //如果不是sale方法，则调用原来的方法，不增强
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }
            }
        });
        //调用方法
        String computer = proxy_lenovo.sale(8000);
        System.out.println(computer);
        proxy_lenovo.show();
    }
}
