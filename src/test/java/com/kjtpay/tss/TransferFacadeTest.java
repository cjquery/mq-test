/*
package com.kjtpay.tss;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.kjtpay.common.wrapper.Result;
import com.kjtpay.transfercore.service.facade.api.TransferFacade;
import com.kjtpay.transfercore.service.facade.request.PaymentInfo;
import com.kjtpay.transfercore.service.facade.request.TransferRequest;
import com.netfinworks.common.domain.OperationEnvironment;
import com.netfinworks.common.util.DateUtil;
import com.netfinworks.common.util.money.Money;


public class TransferFacadeTest {
	static ApplicationConfig ac = null;
	static RegistryConfig rc = null;

	static {
		try {
			ac = new ApplicationConfig();
			ac.setName("test");// 应用名字
			rc = new RegistryConfig();
			//rc.setAddress("zookeeper://192.168.180.42:2181?backup=192.168.180.43:2181,192.168.180.44:2181");// zookeeper注册中心地址
			rc.setProtocol("dubbo");// 协议
			rc.setAddress("N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static <T> T getProvider(Class<T> c, String group) {
		ReferenceConfig<T> rcc = new ReferenceConfig<T>();
		rcc.setTimeout(50000);
		rcc.setRegistry(rc);
		rcc.setApplication(ac);
		rcc.setCheck(true);
		rcc.setInterface(c);
		//rcc.setGroup(group);
		rcc.setUrl("dubbo://192.168.180.91:28000");//10.252.64.11:28080
		//rcc.setUrl("dubbo://10.252.64.11:28080");
		//rcc.setUrl("dubbo://192.168.23.182:28088");
		return rcc.get();
	}


	

	@Test
	public void  createTest()
	 {
		final TransferFacade transferFacade =  getProvider(TransferFacade.class, null);
		TransferRequest request = new TransferRequest();
		Date date = new Date();
		Random ra =new Random();
		String tvn ="TFC"+ DateUtil.format(date, DateUtil.longFormat)+ra.nextInt(1000);
		request.setAccessChannel("WEB");
		request.setBizNo("313");
		request.setBizProductCode("10310");
		request.setExtension("");
		request.setGmtSubmit(new Date());
		request.setPartnerId("100000555351");
		//request.setPartnerId("200000055673");
		request.setPartnerName("22");
		
		request.setPayeeAccountNo("200100100120000001007700001");
		request.setPayeeId("200000010077");
	*/
/*	request.setPayeeAccountNo("200100400220000005567300001");
		request.setPayeeId("200000055673");*//*

		request.setPayeeName("22");
		request.setTradeAmount(new Money("1"));
		request.setTradeMemo("备注");
		request.setTradeVoucherNo(tvn);
		request.setTradeSrcVoucherNo(tvn);
		request.setCallbackAddr("rocketmq://kjtpay_newunibill_topic");
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setPayChannel("01");
		paymentInfo.setPayerAccountNo("200100200110000049535000001");
		paymentInfo.setPayerId("100000495350");
		*/
/*paymentInfo.setPayerAccountNo("200100300120000005617100001");
		paymentInfo.setPayerId("200000056171");*//*

		paymentInfo.setPayerName("11");
		paymentInfo.setPaymentVoucherNo(tvn);
		paymentInfo.setPayMode("BALANCE");
		paymentInfo.setAccountName("1");
		paymentInfo.setBankCode("1");
		paymentInfo.setCardNo("1");
		paymentInfo.setCertNo("1");
		paymentInfo.setMobileNo("15168265869");
		request.setPaymentInfo(paymentInfo);
		System.out.println(tvn);
		Result createResult =transferFacade.createAndPay(request, new OperationEnvironment());
		System.out.println(createResult);
	}
	@Test
	public void selectByTVN(){
		final TransferFacade transferFacade =  getProvider(TransferFacade.class, null);
		Result createResult =transferFacade.selectByTVN("TFC2019030503", new OperationEnvironment());
		System.out.println(createResult);
	}
	@Test
	public void selectByPVN(){
		final TransferFacade transferFacade =  getProvider(TransferFacade.class, null);
		Result createResult =transferFacade.selectByPVN("TFC2019030503", new OperationEnvironment());
		System.out.println(createResult);
		
	}
}
*/
