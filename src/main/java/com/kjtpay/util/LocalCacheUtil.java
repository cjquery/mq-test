package com.kjtpay.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("localCacheUtil")
public class LocalCacheUtil {

    private boolean hasSetFirst = false;

    private long maxNum; // 个数
    private long expireTime; // 分钟

    private Cache<String, String> cache;

    @Value("${localCache.maxNum:1000}")
    private void setLocalCacheMaxNum(String message) {
        maxNum = Long.parseLong(message);
        initCache();
    }

    @Value("${localCache.expireTime:30}")
    private void setLocalCacheExpireTime(String message) {
        expireTime = Long.parseLong(message);
        initCache();
    }

    private void initCache() {
        if (!hasSetFirst) {
            hasSetFirst = true;
        } else {
	        cache = CacheBuilder.newBuilder().maximumSize(maxNum).expireAfterAccess(expireTime, TimeUnit.MINUTES).build();
        }
    }

    public String getByBankCode(String key) {
        return cache.getIfPresent(key);
    }

    public void pubByBankCode(String key, String value) {
	    cache.put(key, value);
    }


}
