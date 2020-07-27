package com.kjtpay.exceptionTest;

/**
 * @Package: com.kjtpay.exceptionTest
 * @ClassName: exceptionTest
 * @author: caojiaqi
 * @Date: Created in 2019-06-13 10:20
 * @Description：
 * try中有return, 会先将值暂存，无论finally语句中对该值做什么处理，最终返回的都是try语句中的暂存值。
 * 当try与finally语句中均有return语句，会忽略try中return。
 */
public class ExceptionTest {
	public int div(int a, int b) {
		if (b < 0) {
			throw new MyRuntimeException("除数不能为负数啊！！");
		}

		return a / b;
	}

	public int div2(int a, int b) throws MyCheckedException {
		if (b == 0) {
			//必须要显示的throws该异常，调用方必须处理处理或继续抛出
			throw new MyCheckedException("除数不能为0！！");
		}

		return a / b;
	}

	public static void main(String[] args) {
		ExceptionTest d = new ExceptionTest();
		int x = d.div(4, -9);
		try {
			int x2 = d.div2(4, 0);
		} catch (MyCheckedException e) {
			e.printStackTrace();
		}
		System.out.println("x=" + x);
	}
}


