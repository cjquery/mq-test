package com.kjtpay.dubbo.activate;

import com.alibaba.dubbo.common.extension.SPI;
//用于aop
@SPI("order1")
//@SPI
public interface ActivateExt1 {
    String echo(String msg);
}
