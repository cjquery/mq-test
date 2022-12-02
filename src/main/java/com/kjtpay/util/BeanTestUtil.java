package com.kjtpay.util;

import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Package: com.kjtpay.trademng.util
 * @ClassName: BeanTestUtil
 * @author: caojiaqi
 * @Date: Created in 2019-07-15 16:42
 * @Description： 测试实体类
 */
public class BeanTestUtil {
	public static final int NUMBER_VALUE = 1;

	public static final boolean BOOLEAN_VALUE = true;

	public static final String STRING_VALUE = "StringValue";

	public static final String DateStr = "20190101";

	private static final String SET = "set";

	private static final String GET = "get";

	private static final String IS = "is";

	private static final int THREE = 3;

	private static final int TWO = 2;


	public void testBean(Class cls) {
		try {
			Object currentObj = cls.newInstance();

			Map filedMap = getFields(cls);

			Method[] methods = cls.getMethods();

			invokeSetter(currentObj, filedMap, methods);

			invokeGetter(currentObj, filedMap, methods);

			testMethod("toString", cls, currentObj);

		} catch (Exception ex) {
			Assert.fail(ex.toString());
		}
	}

	private void testMethod(String methodName, Class cls, Object currentObj) throws Exception {
		Method method = null;
		try {
			method = cls.getMethod(methodName, null);
		} catch (SecurityException e) {
			return;
		} catch (NoSuchMethodException e) {
			return;
		}

		if (null != method) {
			Object result = method.invoke(currentObj, null);
			if (null != result) {
				Assert.assertNotNull(result);
				return;
			}

			Assert.assertNull(result);
		}
	}

	public void testBeans(Class[] clss) {
		for (int i = 0; i < clss.length; i++) {
			this.testBean(clss[i]);
		}
	}

	private Object getValueByType(Object type) {
		String temp = type.toString().trim();

		if ("int".equals(temp) || "java.lang.Integer".equals(temp)) {
			return new Integer(NUMBER_VALUE);
		} else if ("double".equals(temp) || "java.lang.Double".equals(temp)) {
			return new Double(NUMBER_VALUE);
		} else if ("boolean".equals(temp) || "java.lang.Boolean".equals(temp)) {
			return new Boolean(BOOLEAN_VALUE);
		} else if ("java.lang.String".equals(temp)) {
			return STRING_VALUE;
		} else if ("long".equals(temp) || "java.lang.Long".equals(temp)) {
			return new Long(NUMBER_VALUE);
		} else if ("java.util.Date".equals(temp)) {
			return DatesUtil.parseNoPoint(DateStr);
		} else if ("java.util.List".equals(temp) || "java.util.ArrayList".equals(temp)) {
			return new ArrayList();
		}
		return null;
	}

	private Map getFields(Class cls) {
		Field[] fields = cls.getDeclaredFields();

		Field field = null;
		Map filedMap = new HashMap();
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			filedMap.put(field.getName(), field.getType());
		}
		return filedMap;
	}

	private String startCharToLowerCase(String str) {
		str = str.replaceFirst(str.substring(0, 1),
				str.substring(0, 1).toLowerCase(Locale.US));
		return str;
	}

	private void invokeGetter(Object currentObj, Map filedMap, Method[] methods)
			throws Exception {
		Method method;
		String methodName;
		Object getResult = null;
		Class returnType = null;
		String returnTypeName = null;
		String fieldName = null;
		for (int i = 0; i < methods.length; i++) {
			method = methods[i];
			methodName = method.getName();

			if (!methodName.startsWith(GET) && !methodName.startsWith(IS)) {
				continue;
			}

			if (methodName.startsWith(GET)) {
				fieldName = methodName.substring(THREE, methodName.length());
			} else {
				fieldName = methodName.substring(TWO, methodName.length());
			}

			fieldName = startCharToLowerCase(fieldName);

			if (!filedMap.containsKey(fieldName)) {
				continue;
			}

			getResult = method.invoke(currentObj, null);

			returnType = method.getReturnType();
			returnTypeName = returnType.getName().trim();

			testResult(getResult, returnTypeName);
		}
	}

	private void testResult(Object getResult, String returnTypeName) {
		if ("int".equalsIgnoreCase(returnTypeName) || "java.lang.Integer".equalsIgnoreCase(returnTypeName)) {
			assertEquals(NUMBER_VALUE, ((Integer) getResult).intValue());
		} else if ("long".equalsIgnoreCase(returnTypeName) || "java.lang.Long".equalsIgnoreCase(returnTypeName)) {
			assertEquals(NUMBER_VALUE, ((Long) getResult).intValue());
		} else if ("double".equalsIgnoreCase(returnTypeName) || "java.lang.Double".equalsIgnoreCase(returnTypeName)) {
			assertEquals(NUMBER_VALUE, ((Double) getResult).intValue());
		} else if ("boolean".equalsIgnoreCase(returnTypeName) || "java.lang.Boolean".equalsIgnoreCase(returnTypeName)) {
			assertEquals(BOOLEAN_VALUE, ((Boolean) getResult).booleanValue());
		} else if ("java.lang.String".equalsIgnoreCase(returnTypeName)) {
			assertNotNull("notnull", getResult);
		} else if ("java.util.Date".equalsIgnoreCase(returnTypeName)) {
			assertEquals(DateStr, DatesUtil.printNoPoint((Date) getResult));
		} else {
			if (null == getResult) {
				assertEquals(null, getResult);
			} else {
				assertNotNull("notnull", getResult);
			}
		}
	}

	private void invokeSetter(Object currentObj, Map filedMap, Method[] methods)
			throws Exception {
		Method method;
		String methodName;

		Object[] values = new Object[1];
		String fieldName = null;
		for (int i = 0; i < methods.length; i++) {
			method = methods[i];
			methodName = method.getName();
			if (!methodName.startsWith(SET)) {
				continue;
			}

			fieldName = methodName.substring(THREE, methodName.length());

			fieldName = startCharToLowerCase(fieldName);

			if (!filedMap.containsKey(fieldName)) {
				continue;
			}


			Class[] types = method.getParameterTypes();
			Class type = types[0];

			if (type.isArray()) {
				values[0] = null;
			} else {
				values[0] = getValueByType(type.getName());
			}

			method.invoke(currentObj, values);
		}
	}

	@Test
	public void test() {
		Class[] classes = new Class[]{
				String.class
		};
		this.testBeans(classes);
	}
}
