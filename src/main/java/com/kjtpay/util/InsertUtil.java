package com.kjtpay.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.kjtpay.util
 * @ClassName: InsertUtil
 * @author: caojiaqi
 * @Date: Created in 2019-08-22 17:19
 * @Description： TODO
 */
public class InsertUtil {
	public static void main(String[] args) throws IOException {

		String dir="D:/tempFile/";
		List<String> codes=readFile(dir+"1.txt");
		List<String> names=readFile(dir+"2.txt");
		List<String> result=new ArrayList<>();
		for(int i=0;i<codes.size();i++){
			String code=codes.get(i);
			String name=names.get(i);
			//String s= "insert into TTL.T_DATA_DICT(CODE, DICT_TYPE, NAME, SHORT_NAME, GMT_CREATE, GMT_MODIFY) values ('replacecode','COUNTRY_CODE','replacename',null,sysdate,sysdate);";
			String s="update fps.t_user set login_account = 'replacename',login_type='1',pay_password='replacepassword' where user_id='replacecode';";
			String replacecode = s.replace("replacecode", code);
			String resultsb = replacecode.replace("replacename", name);
			String replacepassword = resultsb.replace("replacepassword",name.substring(name.length()-4,name.length()));
			result.add(replacepassword);
		}
		FileOutputStream fos = new FileOutputStream("D:\\3.txt");
		for(String sb:result){
			String str = sb; //将list中的元素转为str遍历给String
			fos.write(str.getBytes()); //字节流转为byte数组写入
			fos.write("\r\n".getBytes()); //代表windows系统的换行。

		}
		fos.close();

	}

	public static List<String> readFile(String fileName){
		List<String> lineStrs=new ArrayList<String>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			InputStreamReader isr=new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line="";
			String str=null;
			while ((line=br.readLine())!=null) {
				str=line.trim();
				if(str.length()==0){
					break;
				}
				lineStrs.add(str);
			}
			br.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineStrs;
	}
}
