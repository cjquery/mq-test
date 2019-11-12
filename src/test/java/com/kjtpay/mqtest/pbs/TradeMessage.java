package com.kjtpay.mqtest.pbs;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * @Package: com.netfinworks.pbs.bos.ext.service.message
 * @ClassName: TradeMessage
 * @author: caojiaqi
 * @Date: Created in 2018-12-17 15:35
 * @Description： TODO
 */
public class TradeMessage {
	/**交易类型*/
	private String source;
	/**交易凭证号*/
	private String tradeVoucherNo;
	/**支付凭证号*/
	private String paymentVoucherNo;
	/**交易状态*/
	private String status;
	/**交易订单创建时间*/
	private Date gmtCreate;
	/**交易订单完成时间*/
	private Date gmtTradeFinish;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTradeVoucherNo() {
		return tradeVoucherNo;
	}

	public void setTradeVoucherNo(String tradeVoucherNo) {
		this.tradeVoucherNo = tradeVoucherNo;
	}

	public String getPaymentVoucherNo() {
		return paymentVoucherNo;
	}

	public void setPaymentVoucherNo(String paymentVoucherNo) {
		this.paymentVoucherNo = paymentVoucherNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtTradeFinish() {
		return gmtTradeFinish;
	}

	public void setGmtTradeFinish(Date gmtTradeFinish) {
		this.gmtTradeFinish = gmtTradeFinish;
	}

/*	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}*/
}
