package com.kjtpay.mqtest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Package: com.kjtpay.mqtest
 * @ClassName: CustomClassLoader
 * @author: caojiaqi
 * @Date: Created in 2019-04-16 17:04
 * @Description： TODO
 */
/**
自定义类加载器作用：加密、热加载、加载其他来源的类
类加载器双亲委派模型
	加载->验证->准备->解析->初始化->使用->卸载，只有加载是我们可以掌控的，其他的阶段均是有jvm来完成的。
Bootstrp loader
Bootstrp加载器是用C++语言写的，它是在Java虚拟机启动后初始化的，它主要负责加载%JAVA_HOME%/jre/lib,-Xbootclasspath参数指定的路径以及%JAVA_HOME%/jre/classes中的类。
ExtClassLoader
Bootstrp loader加载ExtClassLoader,并且将ExtClassLoader的父加载器设置为Bootstrp loader.ExtClassLoader是用Java写的，具体来说就是 sun.misc.Launcher$ExtClassLoader，ExtClassLoader主要加载%JAVA_HOME%/jre/lib/ext，此路径下的所有classes目录以及java.ext.dirs系统变量指定的路径中类库。因为Bootstrp loader不是java写的因此，逻辑上并不存在Bootstrap Loader的类实体，getParent()方法返回的是null
AppClassLoader
Bootstrp loader加载完ExtClassLoader后，就会加载AppClassLoader,并且将AppClassLoader的父加载器指定为 ExtClassLoader。AppClassLoader也是用Java写成的，它的实现类是 sun.misc.Launcher$AppClassLoader，另外我们知道ClassLoader中有个getSystemClassLoader方法,此方法返回的正是AppclassLoader.AppClassLoader主要负责加载classpath所指定的位置的类或者是jar文档，它也是Java程序默认的类加载器。

 Java装载类使用“全盘负责委托机制”。“全盘负责”是指当一个ClassLoder装载一个类时，除非显示的使用另外一个ClassLoder，该类所依赖及引用的类也由这个ClassLoder载入；
 “委托机制”是指先委托父类装载器寻找目标类，只有在找不到的情况下才从自己的类路径中查找并装载目标类。

思考：假如我们自己写了一个java.lang.String的类，我们是否可以替换调JDK本身的类？
答案是否定的。我们不能实现。为什么呢？我看很多网上解释是说双亲委托机制解决这个问题，其实不是非常的准确。因为双亲委托机制是可以打破的，你完全可以自己写一个classLoader来加载自己写的java.lang.String类，但是你会发现也不会加载成功，具体就是因为针对java.*开头的类，jvm的实现中已经保证了必须由bootstrp来加载。

定义自已的ClassLoader
既然JVM已经提供了默认的类加载器，为什么还要定义自已的类加载器呢？
因为Java中提供的默认ClassLoader，只加载指定目录下的jar和class，如果我们想加载其它位置的类或jar时，比如：我要加载网络上的一个class文件，通过动态加载到内存之后，要调用这个类中的方法实现我的业务逻辑。在这样的情况下，默认的ClassLoader就不能满足我们的需求了，所以需要定义自己的ClassLoader。

定义自已的类加载器分为两步：

1、继承java.lang.ClassLoader

2、重写父类的findClass方法

读者可能在这里有疑问，父类有那么多方法，为什么偏偏只重写findClass方法？

因为JDK已经在loadClass方法中帮我们实现了ClassLoader搜索类的算法，当在loadClass方法中搜索不到类时，loadClass方法就会调用findClass方法来搜索类，所以我们只需重写该方法即可。如没有特殊的要求，一般不建议重写loadClass搜索类的算法。

热加载实现原理：(比如Tomcat中jsp热更新就是这样实现的)
1.对于任意一个类，都需要由加载它的类加载器（定义类加载器：实际记载的加载器）和这个类本身共同确立其在Java虚拟机中的唯一性。通俗的说，JVM中两个类是否“相等”，首先就必须是同一个类加载器加载的，否则，即使这两个类来源于同一个Class文件，被同一个虚拟机加载，只要类加载器不同，那么这两个类必定是不相等的。
这里的“相等”，包括代表类的Class对象的equals()方法、isAssignableFrom()方法、isInstance()方法的返回结果，也包括使用instanceof关键字做对象所属关系判定等情况。

2.一个类，由不同的类加载器实例加载的话，会在方法区产生两个不同的类，彼此不可见，并且在堆中生成不同Class实例。尽管不同的类加载器加载的类实例对象不是同一个，但是他们其中的一个内容发生改变，另一个也会发生改变

3.所有通过正常双亲委派模式的类加载器加载的classpath下的和ext下的所有类在方法区都是同一个类，堆中的Class实例也是同一个。因此如果要实现热加载的类在classpath路径下，自定义类加载器要打破双亲委派机制（重写loadclass方法）




解释：
一，有两个术语，一个叫“定义类加载器”，一个叫“初始类加载器”。
比如有如下的类加载器结构：
bootstrap
  ExtClassloader
    AppClassloader
    -自定义clsloadr1
    -自定义clsloadr2
如果用“自定义clsloadr1”加载java.lang.String类，那么根据双亲委派最终bootstrap会加载此类，那么bootstrap类就叫做该类的“定义类加载器”，而包括bootstrap的所有得到该类class实例的类加载器都叫做“初始类加载器”。

二，所说的“命名空间”，是指jvm为每个类加载器维护的一个“表”,这个表记录了所有以此类加载器为“初始类加载器”（而不是定义类加载器，所以一个类可以存在于很多的命名空间中）加载的类的列表，所以，题目中的问题就可以解释了：
CLTest是AppClassloader加载的，String是通过加载CLTest的类加载器也就是AppClassloader进行加载，但最终委派到bootstrap加载的（当然，String类其实早已经被加载过了，这里只是举个例子）。所以，对于String类来说，bootstrap是“定义类加载器”，
AppClassloader是“初始类加载器”。根据刚才所说，String类在AppClassloader的命名空间中（同时也在bootstrap，ExtClassloader的命名空间中，因为bootstrap，ExtClassloader也是String的初始类加载器），所以CLTest可以随便访问String类。
这样就可以解释“处在不同命名空间的类，不能直接互相访问”这句话了。

1、同一个命名空间内的类是相互可见的，即可以互相访问。
2、父加载器的命名空间对子加载器可见。
3、子加载器的命名空间对父加载器不可见。
4、如果两个加载器之间没有直接或间接的父子关系，那么它们各自加载的类相互不可见。



线程上下文加载器（TCCL），spring类加载流程：
我们把类委托给spring的加载器来加载，如果spring的jar包放在每个webapp自己的目录中，这时由WebAppClassLoader来加载是没有问题的，但是
如果有 10 个 Web 应用程序都用到了spring的话，我们把Spring的jar包放到 common 或 shared 目录下让这些程序共享，这时spring
如何去加载 /WebApp/WEB-INF 目录中的（由 WebAppClassLoader 加载的类）中的class呢？
其实spring根本不会去管自己被放在哪里，它统统使用TCCL来加载类，而TCCL默认设置为了WebAppClassLoader，
也就是说哪个WebApp应用调用了spring，spring就去取该应用自己的WebAppClassLoader

源码


public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
	try {
		// 创建WebApplicationContext
		if (this.context == null) {
			this.context = createWebApplicationContext(servletContext);
		}
		// 将其保存到该webapp的servletContext中
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
		// 获取线程上下文类加载器，默认为WebAppClassLoader
		ClassLoader ccl = Thread.currentThread().getContextClassLoader();
		// 如果spring的jar包放在每个webapp自己的目录中
		// 此时线程上下文类加载器会与本类的类加载器（加载spring的）相同，都是WebAppClassLoader
		if (ccl == ContextLoader.class.getClassLoader()) {
			currentContext = this.context;
		}
		else if (ccl != null) {
			// 如果不同，也就是上面说的那个问题的情况，那么用一个map把刚才创建的WebApplicationContext及对应的WebAppClassLoader存下来
			// 一个webapp对应一个记录，后续调用时直接根据WebAppClassLoader来取出
			currentContextPerThread.put(ccl, this.context);
		}

		return this.context;
	}
	catch (RuntimeException ex) {
		logger.error("Context initialization failed", ex);
		throw ex;
	}
	catch (Error err) {
		logger.error("Context initialization failed", err);
		throw err;
	}
}
 1.当高层提供了统一接口让低层去实现，同时又要是在高层加载（或实例化）低层的类时，必须通过线程上下文类加载器来帮助高层的ClassLoader找到并加载该类。
 2.当使用本类（spring）来托管类加载，然而加载本类的ClassLoader未知时，为了隔离不同的调用者，


参考：
 https://blog.csdn.net/yangcheng33/article/details/52631940
 https://blog.csdn.net/jslcylcy/article/details/72678432
*/

public class CustomClassLoader extends ClassLoader {
	/**
	 * 不需要双亲委派加载的类集合
	 */
	private Set<String> customClasses = new HashSet<String>();

	{
		customClasses.add("com.kjtpay.mqtest.ClassLoaderTest");
	}

	@Override
	protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
		if (customClasses.contains(className)) {
			return findClass(className);
		}
		return super.loadClass(className, resolve);
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		return defineAppPathClass(className);
	}

	private Class<?> defineAppPathClass(String name) throws ClassNotFoundException {
		BufferedInputStream bis = null;
		try {
			//加载类路径下的类
			URL url = ClassLoader.getSystemClassLoader().getResource("");
			String path = url.getPath();
			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}
			String packagePath = name.replaceAll("\\.", "/");
			path = path + "/" + packagePath + ".class";
			bis = new BufferedInputStream(new FileInputStream(path));
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return defineClass(name, data, 0, data.length);
		} catch (Exception e) {
			e.printStackTrace();
			return super.findClass(name);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					//创建新的类加载器，加载Foo，执行方法
					CustomClassLoader loader = new CustomClassLoader();
					Class<?> clz = loader.loadClass("com.kjtpay.mqtest.ClassLoaderTest");
					Object instance = clz.getConstructor().newInstance();
					Method method = instance.getClass().getMethod("getValue");
					method.invoke(instance);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("自定义加载器加载类异常");
				}
			}
		}, 0, 1000);
	}


/* *
  曾遇问题java.lang.NoClassDefFoundError
  此处需要注意，defineClass的第一个参数name，这个是需要与当前包名保持一致的，
  比如我要加载的类是ClassLoaderTestDemo，packge是classloader，
  那这个name就是classloader.ClassLoaderTestDemo
  **/

}
