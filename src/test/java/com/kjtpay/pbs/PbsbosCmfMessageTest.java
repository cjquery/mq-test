package com.kjtpay.pbs;

import com.kjtpay.rocketmq.v2.MQProducer;
import com.netfinworks.common.util.money.Money;
import com.netfinworks.pbs.bos.service.message.PbsCmfSuccessMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-test.xml"})
public class PbsbosCmfMessageTest {
    
 /*   @Autowired
    private MQService mqService;*/
	@Resource(name = "mqProducer")
 MQProducer mqProducer;
	//static String topicName = "RMQ_JMS_TEST_PRODUCE";
	static String topicName = "kjtpay_pbsbos_cmfSuccess";

	@Test
    public void testPbsJSON_FOUR_AMOUNT() throws Exception  {
		/**
		 *  select * from pbsdb.TB_CHANNEL_CALCULATE where type = 1 and id=1
		 *   select * from pbsdb.TB_CHANNEL_DETAIL where amount=100.00 order by gmt_create desc
		 */
         String pre="fourtest_oneccccc";
         PbsCmfSuccessMessage msg=new PbsCmfSuccessMessage();
        msg.setInstOrderNo(pre+System.currentTimeMillis());
        msg.setPaymentSeqNo(String.valueOf(System.currentTimeMillis()));
        msg.setAmount(new Money("100"));
        msg.setApiType("S");
        msg.setBizType("I");
        msg.setFundChannelCode("UNP60901-FV");
        msg.setGmtSubmit(new Date());
        msg.setInstCode("GZCB");
        msg.setPayDate(new Date());
        msg.setSettleBankCode("CITIC");
		msg.setPaymentOrderNo("1");
		msg.setTradeVoucherNo("2");

    	try {

		    mqProducer.send(topicName,msg.getInstOrderNo(),msg);
        } catch (Exception e) {
    	    e.printStackTrace();
        }
    }

}