# 同事问我：为什么我的Service无法注入进来

## 前言

相信你在平常开发中不少遇到这种错误：

```
Field xxxService in com.xx.xx.service.impl.XxXServiceImpl required a bean of type 'com.xx.xx.service.XxxService' that could not be found.
```

这个错误其实就是这个Bean在Spring容器中找不到，发生这种错误时，常见的有两种情况

1、`@ComponentScan`注解里的扫描路径没包含这个类

2、这个类的头上没加`@Component`注解

那么问题就来了：为什么`@ComponentScan`没扫描到或者没加`@Component`注解就注入不到Spring容器中？这个问题有点无厘头(没加`@Component`注解你还想注入到Spring容器中？)

我换种问法：为什么`@ComponentScan`扫描到了并且加了`@Component`注解就能注入到Spring容器中？

当然你可以直接回答：因为Spring规定这样做的

当然我也会接着反问你：Mybatis的Mapper就没用`@Component`注解，凭啥它就能注入到Spring容器中？

回答不了了吧？回答不了就赶紧往下看吧~

## 问题分析

要回答：为什么`@ComponentScan`扫描到了并且加了`@Component`注解就能注入到Spring容器中？

我们首先需要对问题进行拆解：

1、`@ComponentScan`扫描是做了什么？

2、加了`@Component`注解又代表了什么？

回答了这两个问题我们再进行猜想：以上过程是否可以进行自定义？如何自定义？否则就没有办法说明Mapper是如何注入到Spring容器中的。

## `@ComponentScan`扫描是做了什么？

这个过程大概是这样的：Spring通过扫描指定包下的类，解析这些类的信息，转化成为BeanDefinition，注册到beanDefinitionMap中。

那么这个过程的详情情况又是如何呢？

我们先来了解一下这个过程中涉及到的角色：

1、BeanDefinition：Bean定义，内含Class的相关信息

2、ConfigurationClassPostProcessor：配置类处理器，查找配置类，创建配置类解析器

3、ConfigurationClassParser：配置类解析器，解析配置类，创建@ComponentScan注解解析器

4、ComponentScanAnnotationParser：@ComponentScan注解解析器，解析@ComponentScan注解，创建Bean定义扫描器

5、ClassPathBeanDefinitionScanner：Bean定义扫描器，扫描指定包下的所有类，将符合的类转化为BeanDefinition

6、BeanDefinitionRegistry：BeanDefinition注册器，注册BeanDefinition

从上往下看，我们可以轻易的发现，这整个过程有一种层层递进的关系：

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhxt7vl8rj30u013un2r.jpg)

下面我们再来看看这些角色的具体职责。

### 1.配置类处理器

配置类处理器主要做了3件事

1、查找配置类

2、创建配置类解析器并调用

3、加载配置类解析器所返回的@Import与@Bean注解的类

#### 1.1查找配置类

你可能会有疑惑，配置类不是我们传入的吗？为什么还需要去查找配置类呢？

这是因为Spring整个调用链路十分复杂，不可能说把配置类往下层层传递，而是一开始时就将配置类注册到BeanDefinitonMap中了。

查找配置类大致有两个过程：

1、从BeanFactory中获取到所有的BeanDefiniton信息

2、判断BeanDefiniton是否为配置类

第一步很好解决，所有的BeanDefiniton是放在BeanFactory的BeanDefinitonMap中，直接从中获取就可以了。

而对于第二点，首先我们要知道什么是配置类？

在Spring中，有两种配置类：

1、full类型：标识了`@Configuration`注解的类

2、lite类型：标识了`@Component` `@ComponentScan` `@Import` `@ImportResource` `@Bean` 注解的类(其中之一就行)

他们唯一的区别就在于：full类型的类会在后置处理步骤中进行动态代理

还记得这个例子嘛？

```java
@Configuraiton
public class MyConfiguration{
  @Bean
  public Car car(){
      return new Car(wheel());
  }
  @Bean
  public Wheel wheel(){
      return new Wheel();
  }
}
```

问：Wheel对象在Spring启动时，被new了几次？

答案是一次，因为MyConfiguration对象实际上会被进行cglib动态代理，所以就算被`this.`的方式调用依旧会触发代理逻辑

> 只有在这个情况下是这样，平常我们进行cglib代理时this调用依旧直接调用本类方法。

当查找出所有的配置类信息之后，紧接着就是创建**配置类解析器**，并将所有的配置类交由**配置类解析器**进行解析

#### 1.2流程图

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhxtfy64zj30vk0qkq6f.jpg)

### 2.配置类解析器

配置类解析器的职责如下

1、判断该类是否应该跳过解析

2、解析内部类信息

3、解析`@PropertySources`注解信息

4、解析`@ComponentScan`注解信息

5、解析`@Import`注解信息

6、解析`@Bean`注解信息

#### 2.1判断该类是否应该跳过解析

所谓判断类是否应该跳过解析，其实就是判断类是否标识了`@Conditional`注解并且是否满足该条件。如果标识了该注解并且不满足条件，那么则跳过解析步骤。

如我们常见的`@Profile`,`@ConditionalOnMissBean`等都是由此控制。

#### 2.2解析内部类信息

有时候我们的配置类里面有内部类，并且内部类也是个配置类，那么就需要用此方式进行解析。

#### 2.3解析`@ComponentScan`注解信息

该步骤主要是利用**@ComponentScan注解解析器**进行解析`@ComponentScan`注解，从而获取到BeanDefinition列表，再判断这些BeanDefinition是否是个配置类，是则再次调用**配置类解析器**进行递归解析。

#### 流程图

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhxtka6trj30yn0u0q7o.jpg)

### 3.@ComponentScan注解解析器

在该步骤中，Spring会将我们配置在`@ComponentScan`注解上的所有信息提取出来，存入到**Bean定义扫描器**中，再利用**Bean定义扫描器**得到符合条件的BeanDefiniton。

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhxtpa124j30rm0q8n0b.jpg)

excludeFilter和includeFilter用于扫描时判断class是否符合要求。

默认的excludeFilter：扫描时排除掉自己这个class

默认的includeFilter: 扫描时判断该class是否标识@Component注解

### 4.Bean定义扫描器

BeanDefinitionScanner主要做了三件事：

1、扫描包路径下的类

2、给BeanDefiniton设值

3、使用BeanDefinition注册器将BeanDefiniton注册到容器中

#### 4.1扫描包路径下的类

扫描包路径的步骤可以简单理解为遍历class文件的过程，遍历包下的每个class，判断该class是否满足条件——标识了`@Component`注解，将满足条件的class转化为BeanDefiniton，此时BeanDefiniton只有metedata信息，还没有具体设值。

#### 4.2给BeanDefiniton设值

如果我们在类上加了类似这些注解：@Lazy @Primary @DependsOn，那么就需要将这些注解转化为实际的属性设到BeanDefiniton中。

#### 4.3流程图

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhxtu0cykj30ze0u0dkt.jpg)

### 5.BeanDefinition注册器

BeanDefinitionRegistry的作用就是将BeanDefiniton放到BeanDefinitonMap中

## 思考

现在我们已经知道了扫描包的整体过程，再来回顾一下这个问题：Mybatis的Mapper是怎么注入到Spring容器中的？

像这种问题咋一看很难理解，常常在面试的情况发生，因为面试官是拿着答案问问题。

但是我们思考的话，就应该换个角度：怎么才能让Mapper注册到Spring中 -> 怎么才能让自定义的注解标识的Class注册到Spring中？

不知道这样问是否简单些呢？

## 方法

### 1.使用TypeFilter

我们知道`@Component`注解是和默认注册的IncludeFilter配套使用的，那么同样我们也可以使用一个自定义的IncludeFilter与我们的自定义注解配套使用

自定义Mapper注解

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapper {
}
```

使用Mapper

```java
@Mapper
public class MyMapper {

	public void hello(){
		System.out.println("myMapper hello");
	}
}
```

测试

添加一个自定义的IncludeFilter进行测试

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhuhwcyqxj31t80jq420.jpg)

**注意：**此方式只能支持自定义注解标识在实体类的情况，如果将Mapper注解加在接口上，则你会收获一个异常：No bean named 'myMapper' available

答案很简单，因为接口不能实例化，所以Spring默认判断如果该类非实体类，则不注册到容器中。

那么我们怎么才能让加了Mapper注解的接口能注册到Spring中呢？

### 2.自定义扫描器

既然Spring的扫描器无法支持接口，那么我们就重写它——的判断逻辑。

> 开源框架扩展心得：继承整体逻辑，重写一小块逻辑。

所以我们方式很简单：继承ClassPathBeanDefinitionScanner，重写判断Class是否符合的逻辑

```java
public class ClassPathMapperScanner extends ClassPathBeanDefinitionScanner {

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    // 重写判断beanDefinition是否为接口逻辑，改为只有类为接口时才允许注册
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}
  //省略构造方法
}
```

逻辑已经改好了，现在迎来一个新问题：怎么让Spring使用它？

通过整体流程我们知道，**Bean定义扫描器**是在**@ComponentScan注解解析器**的解析流程中创建(new)出来的，我们又不能更改这个流程，所以, Game Over?

但，为什么一定要在Spring的扫描流程中使用我们的扫描器呢？我们可以在Spring的扫描流程结束后，再扫描一遍不就好了吗？

还记得有什么方式可以做到这件事吗？后置处理器！

### 3.使用后置处理器

我们通过使用BeanDefinitionRegistryPostProcessor，让Spring的扫描流程结束之后，进行一次后置处理。在后置处理中，创建出自定义的扫描器，进行第二次扫描。

```java
@Component
public class MapperScannerProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    // 创建出自定义的扫描器
		ClassPathMapperScanner classPathMapperScanner = new ClassPathMapperScanner(registry, false);
		// 添加filter，class添加了Mapper注解才注册到Spring中
		classPathMapperScanner.addIncludeFilter(new AnnotationTypeFilter(Mapper.class));
    // 这里可以改为从外部设值，不必写死
		classPathMapperScanner.scan("com.my.spring.test.custom");
	}
}
```

使用这种方式，你会发现，我们的接口确实注册到BeanDefinitionMap中了。

![](https://tva1.sinaimg.cn/large/008i3skNly1gyhwz11o0rj31je0jawjw.jpg)

但是，你仍然会收到一个错误：Failed to instantiate [com.my.spring.test.custom.InterfaceMapper]: Specified class is an interface

接口确实是无法实例化的，虽然我们把它注册到了Spring中。但Mybatis又是怎么做的呢？

答案是替换，Mybatis将图中的beanClass替换成了FactoryBean: `MapperFactoryBean`，然后将原有的beanClass放入了它的mapperInterface属性中

它的getObject方法长这样

```java
public T getObject() throws Exception {
  return getSqlSession().getMapper(this.mapperInterface);
}
```

> 如果你还记得Mybatis的原始使用方式，应该对这行代码并不陌生。

好了，关于思考的内容就到这里，我们只是借用Mybatis的现象进行思考，再深入就是Mybatis的内容了。

## 小结

本文借助一个开发时常见的问题进行分析，介绍了Spring的配置类解析与扫描过程，同时，还借助了Mybatis中的现象，思考**怎么才能让自定义的注解标识Class注册到Spring中**这一问题，并使用案例给出了一份较好的答案，希望大家能够通过案例更加深入的了解该流程。

同样，通过本次学习，来回答一下以下问题吧

1、什么是配置类？Spring中有哪几种配置类？有什么区别？

2、BeanDefinitionRegistryPostProcessor有什么用？你知道哪些案例吗？



