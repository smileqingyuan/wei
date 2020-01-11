package win.smile.partterns.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author weifw
 * @date 2019-05-18-17:25
 */
public class Client {

    public static void main(String[] args) {

        //需要真正代理的对象
        Person student = new Student();

        //构造出代理的handler
        MyInvocationHandler<Person> personHandler = new MyInvocationHandler<>();

        //构造出真正的代理
        Person person = (Person) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Person.class}, personHandler);

        String food = person.eat("food");
        System.out.println(food);
        //执行方法
//        person.eat("food");

//        打印出生成的代理类
//        Class<?> proxyClass = Proxy.getProxyClass(Thread.currentThread().getContextClassLoader(), new Class[]{Person.class});
//        System.out.println(proxyClass);
//
//        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Student.class.getInterfaces());
//        String path = "C:/temp/StuProxy.class";
//        try(FileOutputStream fos = new FileOutputStream(path)) {
//            fos.write(classFile);
//            fos.flush();
//            System.out.println("代理类class文件写入成功");
//        } catch (Exception e) {
//            System.out.println("写文件错误");
//        }


    }


}
