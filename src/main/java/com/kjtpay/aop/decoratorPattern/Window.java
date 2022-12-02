package com.kjtpay.aop.decoratorPattern;

/**
 * @Package: com.kjtpay.aop.decoratorPattern
 * @ClassName: Window
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 16:54
 * @Description： 具体构件，被装饰者
 */
public class Window extends Component{
	@Override
	public void display() {
		System.out.println("显示窗体！");
	}
}
