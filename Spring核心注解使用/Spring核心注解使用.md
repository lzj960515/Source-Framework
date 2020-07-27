## `xml`方式

- beans.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
        <bean id="car" class="com.my.source.spring.start.xmlconfig.Car">
              <constructor-arg name="brand" value="my"/>
              <constructor-arg name="wheel" ref="wheel"/>
              <property name="name" value="my-car"/>
        </bean>
  
        <bean id="wheel" class="com.my.source.spring.start.xmlconfig.Wheel">
              <property name="name" value="my-wheel"/>
        </bean>
  </beans>
  ```

- bean

  ```java
  public class Wheel {
  
      private String name;
  
      public void setName(String name) {
          this.name = name;
      }
  
      @Override
      public String toString() {
          return "Wheel{" +
                  "name='" + name + '\'' +
                  '}';
      }
  }
  ```

  ```java
  public class Car {
  
      private String name;
  
      private String brand;
  
      private Wheel wheel;
  
      public Car(String brand, Wheel wheel){
          this.brand = brand;
          this.wheel = wheel;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      @Override
      public String toString() {
          return "Car{" +
                  "name='" + name + '\'' +
                  ", brand='" + brand + '\'' +
                  ", wheel=" + wheel +
                  '}';
      }
  }
  ```

- Main

  ```java
  public class Main {
  
      public static void main(String[] args) {
          ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
          System.out.println(context.getBean("car"));
      }
  }
  ```

## `Java`注解方式

### @Bean

```java
public class Person {}
```

```java
public class Boy {

    public Boy(){
        System.out.println("Boy was init!");
    }
}
```
```java
public class BoyFactoryBean implements FactoryBean<Boy> {

    public BoyFactoryBean(){
        System.out.println("BoyFactoryBean was init!");
    }
    //返回Bean的对象
    @Override
    public Boy getObject() throws Exception {
        return new Boy();
    }
    
    //返回Bean的类型
    @Override
    public Class<?> getObjectType() {
        return Boy.class;
    }
    
    //是否单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
```
```java
@Configuration
public class MainConfig {

    @Bean(value = "person")
    //@Scope(value = "prototype")
    //@Lazy
    public Person person1(){
        return new Person();
    }
    
    @Bean
    public BoyFactoryBean boyFactoryBean(){
        return new BoyFactoryBean();
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("boyFactoryBean"));
    }
}
```

### @ComponentScan

```java
@Repository
public class MyDao {}
```

```java
@Service
public class MyService {}
```

```java
@Controller
public class MyController {}
```

- 一般使用

  ```java
  @ComponentScan(basePackages = {"com.my.source.spring.start.scan"})
  @Configuration
  public class MainConfig {
  }
  ```

- 使用排除过滤器

  ```java
  @ComponentScan(basePackages = {"com.my.source.spring.start.scan"},
          excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
                  @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MyService.class})})
  @Configuration
  public class MainConfig {
      //此配置将排除使用Controller注解扫描的类和MyService类
  }
  ```

  `FilterType`详细说明

  ```java
  public enum FilterType {
  	//指定注解形式
  	ANNOTATION,
  	//指定的类型
  	ASSIGNABLE_TYPE,
  	//ASPECTJ形式的
  	ASPECTJ,
      //正则
  	REGEX,
  	//自定义
  	CUSTOM
  }
  ```

- 使用自定义的过滤器

  ```java
  @Component
  public class User {}
  ```

  ```java
  public class MyTypeFilter implements TypeFilter {
      @Override
      public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
          //获取当前类的注解信息
          AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
          //获取当前类的类信息
          ClassMetadata classMetadata = metadataReader.getClassMetadata();
          //获取当前类的资源信息
          Resource resource = metadataReader.getResource();
          if ("com.my.source.spring.start.scan.User".equals(annotationMetadata.getClassName())) {
              return true;
          }
          return false;
      }
  }
  ```

  ```java
  @ComponentScan(basePackages = {"com.my.source.spring.start.scan"},
  	includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = {MyTypeFilter.class})},
          useDefaultFilters = false)//为false表示只使用上面声明了的filter,不使用Spring自带的过滤器
  @Configuration
  public class MainConfig {
  }
  ```

### @Import

@Import注解支持三种导入方式

- 普通的组件

  ```java
  public class Man {
  
      public Man(){
          System.out.println("Man was init!");
      }
  }
  ```

  ```java
  @Import({Man.class})
  @Configuration
  public class MainConfig {
  }
  ```

- 实现`ImportSelector`接口

  ```java
  public class Child {
  
      public Child(){
          System.out.println("Child was init!");
      }
  }
  ```

  ```java
  public class MyImport implements ImportSelector {
      @Override
      public String[] selectImports(AnnotationMetadata importingClassMetadata) {
          return new String[]{"com.my.source.spring.start.forimport.Child"};
      }
  }
  ```

  ```java
  @Import({MyImport.class})
  @Configuration
  public class MainConfig {
  }
  ```

- 实现`ImportBeanDefinitionRegistrar`接口

  ```java
  public class Baby {
  
      public Baby(){
          System.out.println("Baby was init!");
      }
  }
  ```

  ```java
  public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
      @Override
      public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
          BeanDefinition beanDefinition = new RootBeanDefinition(Baby.class);
          registry.registerBeanDefinition("my-baby",beanDefinition);
      }
  }
  ```

  ```java
  @Import({MyImportBeanDefinitionRegistrar.class})
  @Configuration
  public class MainConfig {
  }
  ```


### @Conditional

```java
public class Father {

    public Father(){
        System.out.println("Father was init!");
    }
}
```

```java
public class Son {

    public Son(Father father){
        System.out.println("has father! " + father);
        System.out.println("Son was init!");
    }
}
```

```java
public class MyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getBeanFactory().containsBean("father");
    }
}
```

```java
@Configuration
public class MainConfig {

//    @Bean
    public Father father(){
        return new Father();
    }

    @Bean
    @Conditional(MyCondition.class)
    public Son son(Father father){
        return new Son(father);
    }
}
```

> 当Father注入到容器中时，Son才会注入

### @Autowired

```java
@Repository
public class UserDao {
}
```

```java
@Service
public class UserService {
	//自动注入
    @Autowired
    private UserDao userDao;

    @Override
    public String toString() {
        return "UserService{" +
                "userDao=" + userDao +
                '}';
    }
}
```

```java
@Controller
public class UserController {

    private UserService userService;
	
    //set方式注入
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    //构造器注入
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Override
    public String toString() {
        return "UserController{" +
                "userService=" + userService +
                '}';
    }
}
```

### @PropertySource

- student.properties

```properties
name=张三
age=18
score=91.5
```

```java
public class Student {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private int age;
    @Value("${score}")
    private BigDecimal score;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
```

```java
@Configuration
@PropertySource(value = {"classpath:student.properties"} , encoding = "UTF-8")
public class MainConfig {

    @Bean
    public Student student(){
        return new Student();
    }
}
```

### 实现xxxAware接口获取Spring 底层组件

```java
@Component
public class SpringContextUtil implements ApplicationContextAware, BeanNameAware {

    public ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("current bean name is :【"+name+"】");
    }
}
```

### Bean的初始化和销毁方法

- 使用@Bean注解指定init和destroy方法

  ```java
  public class People {
  
      public void init(){
          System.out.println("people be born !");
      }
      public People(){
          System.out.println("People.People");
      }
      public void destroy(){
          System.out.println("people die !");
      }
  }
  ```

  ```java
  @Configuration
  public class MainConfig {
  
      @Bean(initMethod = "init", destroyMethod = "destroy")
      public People people() {
          return new People();
      }
  }
  ```

- 实现InitializingBean, DisposableBean接口

  ```java
  public class Person implements InitializingBean, DisposableBean {
      
      @Override
      public void afterPropertiesSet() throws Exception {
          System.out.println("Person be born !");
      }
      
      public Person(){
          System.out.println("Person.Person");
      }
      
      @Override
      public void destroy() throws Exception {
          System.out.println("Person die !");
      }
  }
  ```

- 使用@PostConstruct和@PreDestroy注解

  ```java
  public class Banana {
  
      @PostConstruct
      public void init(){
          System.out.println("Banana.init");
      }
  
      public Banana(){
          System.out.println("Banana.Banana");
      }
  
      @PreDestroy
      public void destroy(){
          System.out.println("Banana.destroy");
      }
  }
  ```

- 实现BeanPostProcessor接口在bean初始化前后实现自定义逻辑

  ```java
  public class MyBeanPostProcessor implements BeanPostProcessor {
  
      /**
       * 在init方法前调用
       */
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
          System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization ===== "+ beanName);
          return bean;
      }
      /**
       * 在init方法后调用
       */
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
          System.out.println("MyBeanPostProcessor.postProcessAfterInitialization ===== " + beanName);
          return bean;
      }
  }
  ```

  Spring bean初始化时的部分源码

  ```java
  		if (mbd == null || !mbd.isSynthetic()) {
              //调用postProcessBeforeInitialization方法
  			wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
  		}
  
  		try {
              //执行初始化方法
  			invokeInitMethods(beanName, wrappedBean, mbd);
  		}
  		catch (Throwable ex) {
  			throw new BeanCreationException(
  					(mbd != null ? mbd.getResourceDescription() : null),
  					beanName, "Invocation of init method failed", ex);
  		}
  		if (mbd == null || !mbd.isSynthetic()) {
              //调用postProcessAfterInitialization方法
  			wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
  		}
  ```

### 通过依赖查找的方式一次性注入多个组件

```java
public interface MyService {

    void doService();
}
```

```java
@Service
public class OneMyServiceImpl implements MyService {
    @Override
    public void doService() {
        System.out.println("OneServiceImpl.doService");
    }
}
```

```java
@Service
public class TwoMyServiceImpl implements MyService {
    @Override
    public void doService() {
        System.out.println("TwoServiceImpl.doService");
    }
}
```

```java
@Service
public class ThreeMyServiceImpl implements MyService {
    @Override
    public void doService() {
        System.out.println("ThreeServiceImpl.doService");
    }
}
```

```java
@Component
public class Business {

    private final Map<String, MyService> serviceMap;

    public Business(Map<String, MyService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public void doBusiness(){
        serviceMap.forEach((key,service)->{
            service.doService();
        });
    }
}
```

