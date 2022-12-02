package com.kjtpay.aop.decoratorPattern;

/**
 * @Package: com.kjtpay.aop.decoratorPattern
 * @ClassName: LogDecorator
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 16:57
 * @Description： 具体装饰类-增加打印日志功能2
 */
public class Log2Decorator extends  ComponentDecorator{
	public Log2Decorator(Component component) {
		super(component);
	}
	public void display()
	{
		super.display();
		this.addLog2();
	}

	public  void addLog2()
	{

		System.out.println("再次display！");
	}

}
