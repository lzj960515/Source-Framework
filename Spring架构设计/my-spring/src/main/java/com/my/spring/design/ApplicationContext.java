package com.my.spring.design;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
public class ApplicationContext {

    private final Map<String, Class<?>> classMap = new ConcurrentHashMap<>(16);
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>(16);
    private final ClassLoader classLoader = ApplicationContext.class.getClassLoader();

    public ApplicationContext(Class<?> configClass) {
        // 1.扫描配置信息中指定包下的类
        this.scan(configClass);
        // 2.实例化扫描到的类
        this.instantiateBean();
    }

    private void instantiateBean() {
        for (String beanName : classMap.keySet()) {
            getBean(beanName);
        }
    }

    public Object getBean(Class<?> clazz){
        String beanName = this.generateBeanName(clazz);
        return this.getBean(beanName);
    }

    public Object getBean(String beanName){
        // 先从缓存中获取
        Object bean = beanMap.get(beanName);
        if(bean != null){
            return bean;
        }
        return this.createBean(beanName);
    }

    private Object createBean(String beanName){
        Class<?> clazz = classMap.get(beanName);
        try {
            // 创建bean
            Object bean = this.doCreateBean(clazz);
            // 将bean存到容器中
            beanMap.put(beanName, bean);
            return bean;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object doCreateBean(Class<?> clazz) throws IllegalAccessException {
        // 实例化bean
        Object bean = this.newInstance(clazz);
        // 填充属性，将字段设值
        this.populateBean(bean, clazz);
        return bean;
    }

    private Object newInstance(Class<?> clazz){
        try {
            // 这里只支持默认构造器
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateBean(Object bean, Class<?> clazz) throws IllegalAccessException {
        // 解析class信息，判断类中是否有需要进行依赖注入的字段
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Autowired autowired = field.getAnnotation(Autowired.class);
            if(autowired != null){
                // 获取bean
                Object value = this.resolveBean(field.getType());
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
    }

    private Object resolveBean(Class<?> clazz){
        // 先判断clazz是否为一个接口，是则判断classMap中是否存在子类
        if(clazz.isInterface()){
            // 暂时只支持classMap只有一个子类的情况
            for (Map.Entry<String, Class<?>> entry : classMap.entrySet()) {
                if (clazz.isAssignableFrom(entry.getValue())) {
                    return getBean(entry.getValue());
                }
            }
            throw new RuntimeException("找不到可以进行依赖注入的bean");
        }else {
            return getBean(clazz);
        }
    }



    private void scan(Class<?> configClass) {
        // 解析配置类，获取到扫描包路径
        String basePackages = this.getBasePackages(configClass);
        // 使用扫描包路径进行文件遍历操作
        this.doScan(basePackages);
    }

    @SuppressWarnings(value = "all")
    private void doScan(String basePackages) {
        // 获取资源信息
        URI resource = this.getResource(basePackages);

        File dir = new File(resource.getPath());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                // 递归扫描
                doScan(basePackages + "." + file.getName());
            }
            else {
                // com.my.spring.example + . + Boy.class -> com.my.spring.example.Boy
                String className = basePackages + "." + file.getName().replace(".class", "");
                // 将class存放到classMap中
                this.registerClass(className);
            }
        }
    }

    private void registerClass(String className){
        try {
            // 加载类信息
            Class<?> clazz = classLoader.loadClass(className);
            // 判断是否标识Component注解
            if(clazz.isAnnotationPresent(Component.class)){
                // 生成beanName com.my.spring.example.Boy -> boy
                String beanName = this.generateBeanName(clazz);
                // car: com.my.spring.example.Car
                classMap.put(beanName, clazz);
            }
        } catch (ClassNotFoundException ignore) {}
    }

    private URI getResource(String basePackages){
        // 将包格式替换为文件路径格式
        String packageSearchPath = basePackages.replace(".", "/");
        try {
            // 根据路径获取资源
            return classLoader.getResource(packageSearchPath).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateBeanName(Class<?> clazz){
        return Introspector.decapitalize(clazz.getSimpleName());
    }

    private String getBasePackages(Class<?> configClass) {
        // 从ComponentScan注解中获取扫描包路径
        ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
        return componentScan.basePackages();
    }
}
