package com.zhangtao.springcloud.framework.http;

import com.zhangtao.springcloud.framework.Invocation;
import com.zhangtao.springcloud.framework.RPCException;
import org.apache.commons.io.IOUtils;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月24日 18:45
 */
public class HttpClient {

    public String send(String hostName, Integer port, Invocation invocation) throws IOException {
        String result = "";
        //用户配置
        try{
            URL url = new URL("http", hostName, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            objectOutputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            result = IOUtils.toString(inputStream,"UTF-8");
        }catch (MalformedInputException | ProfileDataException e){
            throw e;
        }catch (IOException e){
            e.printStackTrace();
            throw new RPCException();
        }
        return result;
    }
}