package win.smile;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author smilewei on 2018/8/11.
 * @since 1.0.0
 */
public class SpringIocStudy {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanRegistry);
        reader.loadBeanDefinitions("");
        BeanFactory container = (BeanFactory) reader;
        Object xx = container.getBean("xx");

    }

}
