package com.kjtpay.alipractice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Package: com.kjtpay.alipractice
 * @ClassName: FileSystem
 * @author: caojiaqi
 * @Date: Created in 2022/9/27 14:40
 * @Description： TODO
 */
public class FileSystem {
    private HashMap<String,Set<String>> files;
    private HashMap<String,String> dirs;
    public String ls(String path){
        if(path.indexOf(".")!=-1){
            return path.substring(path.lastIndexOf("/"));
        }
        String fileName;
        Set<String> files = files.get(path);
        for(String file :files){
            fileName = file +"--";
        }
        return fileName;

    }

    public void mkdir(String path){
        String dir="";

        while (path.indexOf("/")!=-1){
            dir = path.substring(0,path.indexOf("/"));
            path.substring(path.indexOf("/"),path.length());
            dirs.put(dir, path.substring(path.indexOf("/"),path.length()));
            path =path.substring(path.indexOf("/"),path.length());
        }



    }
}
