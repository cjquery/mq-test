package com.kjtpay.exceptionTest;

/**
 * @Package: com.kjtpay.exceptionTest
 * @ClassName: MyCheckedException
 * @author: caojiaqi
 * @Date: Created in 2019-06-13 15:02
 * @Description： TODO
 */
public class MyCheckedException extends Exception{
	MyCheckedException(String message){
		super(message);
	}
}
