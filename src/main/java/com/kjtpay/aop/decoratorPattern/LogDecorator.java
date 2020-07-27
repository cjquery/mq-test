package com.kjtpay.aop.decoratorPattern;

/**
 * @Package: com.kjtpay.aop.decoratorPattern
 * @ClassName: LogDecorator
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 16:57
 * @Description： 具体装饰类-增加打印日志功能
 */
public class LogDecorator extends  ComponentDecorator{
	public LogDecorator(Component component) {
		super(component);
	}
	public void display()
	{
		this.addLog();
		super.display();
	}

	public  void addLog()
	{

		System.out.println("开始display！");
	}

}
