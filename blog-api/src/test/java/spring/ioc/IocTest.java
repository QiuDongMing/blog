package spring.ioc;

import data.Person;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author qiudm
 * @date 2018/9/27 21:40
 * @desc
 */
public class IocTest {


    @Test
    public void testIOC() throws Exception {

        String className = "data.Person";
        Class<?> aClass = Class.forName(className);
        Person person = (Person) aClass.newInstance();
        Method method = aClass.getMethod("setName", String.class);
        method.invoke(person, "kk");
        System.out.println("person = " + person.getName());



    }







}
