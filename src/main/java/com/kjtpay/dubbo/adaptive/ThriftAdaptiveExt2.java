package com.kjtpay.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;

/**
 * @author linyang on 18/4/20.
 */
//@Adaptive
public class ThriftAdaptiveExt2 implements AdaptiveExt2 {

    public String echo(String msg, URL url) {
        return "thrift";
    }
}
