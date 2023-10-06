package com.project.property.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;

/**
 * @Author
 * @Date
 * @Description
 * 工具类
 */
public class PublicUtils {

    /**
     * 获取流文件
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取十个 1-100随机数
     */
    public static HashSet<Integer> getRandomNum() {
        HashSet<Integer> set = new HashSet<>(10);
        while(set.size() != 10) {
            set.add((int)(Math.random()*100));
        }
        return set;
    }
}
