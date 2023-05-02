package com.zhangtao.springcloud.framework.register;


import com.zhangtao.springcloud.framework.URL;
import com.zhangtao.springcloud.provider.api.HelloService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. @ClassDescription:  远程注册中心
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月24日 20:05
 */
public class RemoteMapRegister {

    private static Map<String, List<URL>> REFISTER = new HashMap<>();

    public static void regist(String interfaceName,URL url){
        List<URL> list = REFISTER.get(interfaceName);
        if(list==null){
            list = new ArrayList<>();
        }
        list.add(url);
        REFISTER.put(interfaceName,list);
        //本机解决 注册中心不在一个进程内问题。
        saveFile();
    }

    public static List<URL> get(String interfaceName) throws IOException {
        REFISTER = getFile();
        List<URL> list = REFISTER.get(interfaceName);
        return list;
    }

    public static void saveFile(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(REFISTER);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Map<String, List<URL>> getFile() throws IOException {
        Map<String, List<URL>> result = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            result = (HashMap) inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("localhost",8080);
        RemoteMapRegister.regist(HelloService.class.getName(),url);
        System.out.println(RemoteMapRegister.getFile().keySet());


    }
}