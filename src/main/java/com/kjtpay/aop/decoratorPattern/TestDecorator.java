package com.kjtpay.aop.decoratorPattern;

/**
 * @Package: com.kjtpay.aop.decoratorPattern
 * @ClassName: TestDecorator
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 17:03
 * @Description： 测试类
 */
public class TestDecorator {
	public static void main(String[] args) {
		Component component = new Window(); //定义具体构件
		Component logDecorator = new LogDecorator(component); //定义装饰后的构件
		//与静态代理相比由于增强功能由具体的装饰者子类进行的增强，可以定义多个装饰者，形成装饰者链
		logDecorator = new Log2Decorator(logDecorator);
		logDecorator.display();

	}
}
