package com.kjtpay.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestTxtSingleLineCompareList {


	public static void main(String[] args) {

		String dir="D:/tempFile/";
		List<String> orgis=readFile(dir+"fee.txt");
		List<String> news=readFile(dir+"tss.txt");

		for(Iterator<String> newsIte = news.iterator(); newsIte.hasNext();){
			String newSb=newsIte.next();
			for(Iterator<String> orgiIte=orgis.iterator();orgiIte.hasNext();){
				String orgiSb=orgiIte.next();
				if(newSb.equals(orgiSb)){
					orgiIte.remove();
					newsIte.remove();
					break;
				}
			}
		}

		for(Iterator<String> newsIte=news.iterator();newsIte.hasNext();){
			String newSb=newsIte.next();
			for(Iterator<String> orgiIte=orgis.iterator();orgiIte.hasNext();){
				String orgiSb=orgiIte.next();
				if(newSb.equals(orgiSb)){
					orgiIte.remove();
					newsIte.remove();
					break;
				}
			}
		}
		System.out.println("----------org------------");

		for(String sb:orgis){
			System.out.println(sb);
		}
		System.out.println("-----------new-----------");
		for(String newS:news){
			System.out.println(newS);
		}
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
