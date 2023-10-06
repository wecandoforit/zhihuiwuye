package com.project.property.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
public class HttpUtils {

    /**
     * 从指定地址下载视频
     * @param httpUrl
     * @param saveFile
     * @return
     */
    public static boolean httpDownload(String httpUrl, String saveFile) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            // 判断文件是否存在
            File file = new File(saveFile);
            if (!file.exists()) {
                File fileParent =new File(file.getParent());
                fileParent.mkdirs();
            }
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送POST请求, JSON作为参数
     * @param url
     * @param jsonParam
     * @return
     */
    public static String httpPostWithJSON(String url, JSONObject jsonParam) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        // json方式
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpResponse resp = client.execute(httpPost);
        HttpEntity he = resp.getEntity();
        respContent = EntityUtils.toString(he,"UTF-8");
        return respContent;
    }

    /**
     * 发送POST请求, 表单作为参数
     * @param url
     * @param pairList
     * @return
     */
    public static String httpPostWithForm(String url, List<BasicNameValuePair> pairList) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        //表单方式
        // List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
	        /*
	        pairList.add(new BasicNameValuePair("pageNo", "2"));
	        pairList.add(new BasicNameValuePair("ptypekey", "p"));
	        pairList.add(new BasicNameValuePair("areaid", "120000"));
	        pairList.add(new BasicNameValuePair("sortkey", ""));
	        pairList.add(new BasicNameValuePair("deptidkey", "00012533X"));
	        pairList.add(new BasicNameValuePair("xzxkkey", ""));
	        pairList.add(new BasicNameValuePair("selname", ""));
	        */
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

        HttpResponse resp = client.execute(httpPost);
        HttpEntity he = resp.getEntity();
        respContent = EntityUtils.toString(he,"UTF-8");
        return respContent;
    }


    /**
     * 发送POST请求
     * @param url       发送请求的地址
     * @param data      发送的数据(JSON字符串)
     * @return
     */
    public static String sendPostRequest(String url, String data) {
        try {
            // 创建URL对象
            URL urlObj = new URL(url);
            // 打开连接
            URLConnection urlConnection = urlObj.openConnection();
            // 需要设置为可发送数据状态,默认为false
            urlConnection.setDoOutput(true);
            // 获取一个输出流
            OutputStream outputStream = urlConnection.getOutputStream();
            // 将数据写出
            outputStream.write(data.getBytes());
            // 关闭输出流
            outputStream.flush();
            outputStream.close();
            // 获取输入流读取数据
            InputStream inputStream = urlConnection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            // 关闭输入流
            inputStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送GET请求
     * @param url   像指定地址发送GET请求
     * @return
     */
    public static String sendGetRequest(String url){
        try {
            // 创建URL对象
            URL urlObj = new URL(url);
            // 打开连接
            URLConnection urlConnection = urlObj.openConnection();
            // 获取输入流读取数据
            InputStream inputStream = urlConnection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            // 返回的数据
            StringBuilder sb = new StringBuilder();
            while((len = inputStream.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            inputStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
