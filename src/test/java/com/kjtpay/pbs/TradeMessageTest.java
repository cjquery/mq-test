/*
package com.kjtpay.pbs;

import com.kjtpay.rocketmq.v2.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

*/
/**
 * @Package: com.kjtpay.pbs
 * @ClassName: TradeMessageTest
 * @author: caojiaqi
 * @Date: Created in 2018-12-26 10:13
 * @Description： TODO
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-test.xml"})
public class TradeMessageTest {
	@Resource(name = "mqProducer")
	MQProducer mqProducer;
	static String topicName = "kjtpay_newunibill_topic";
	@Test
	public void testPbsJSON_FOUR_AMOUNT() throws Exception  {

		TradeMessage msg=new TradeMessage();
		msg.setSource("TSSTEST2");

		try {
			mqProducer.send(topicName,"TSSTEST2","{source:tss}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/
