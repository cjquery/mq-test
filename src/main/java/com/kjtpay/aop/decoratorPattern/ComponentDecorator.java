package com.kjtpay.aop.decoratorPattern;

/**
 * @Package: com.kjtpay.aop.decoratorPattern
 * @ClassName: ComponentDecorator
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 16:55
 * @Description： 装饰类
 */
public class ComponentDecorator  extends Component{
	private Component component;  //维持对抽象构件类型对象的引用


	public ComponentDecorator(Component  component)  //注入抽象构件类型的对象
	{
		this.component = component;
	}
	@Override
	public void display() {
		component.display();
	}
}
