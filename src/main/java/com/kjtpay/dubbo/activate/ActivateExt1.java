package com.kjtpay.dubbo.activate;

import com.alibaba.dubbo.common.extension.SPI;
//用于aop-----拦截调用，增强功能
@SPI("order1")
//@SPI
public interface ActivateExt1 {
    String echo(String msg);
}
