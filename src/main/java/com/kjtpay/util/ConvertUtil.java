package com.kjtpay.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

	public static <E, T> T convert(E e, Class<T> target) {
		if(e==null) {
			return null;
		}
		T t = BeanUtils.instantiate(target);
		BeanUtils.copyProperties(e, t);
		return t;
	}

	public static <E, T> List<T> convert(List<E> sourceList, Class<T> target) {
		if (sourceList == null) {
			return null;
		}
		List<T> list = new ArrayList<T>(sourceList.size());
		for (E source : sourceList) {
			T t = BeanUtils.instantiate(target);
			BeanUtils.copyProperties(source, t);
			list.add(t);
		}
		return list;
	}
}
