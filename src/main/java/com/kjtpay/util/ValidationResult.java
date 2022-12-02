package com.kjtpay.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Map;

/**
 * 校验结果
 * @author YHG
 */
public class ValidationResult {

	// 校验结果是否有错
	private boolean	          error;
	// 校验错误信息
	private Map<String, String>	message;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Map<String, String> getMessage() {
		return message;
	}

	public void setMessage(Map<String, String> message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		if (CollectionUtils.isNotEmpty(message.entrySet())) {
			for (Map.Entry<String, String> entry : message.entrySet()) {
				msg.append(entry.getValue()).append("|");
			}
		}
		return "ValidationResult [error=" + error + ", message=" + msg.toString() + "]";
	}

	public String getMessageString() {
		StringBuilder msg = new StringBuilder();
		if (CollectionUtils.isNotEmpty(message.entrySet())) {
			for (Map.Entry<String, String> entry : message.entrySet()) {
				msg.append(entry.getValue()).append("|");
			}
		}
		return msg.toString();
	}
}