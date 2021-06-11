package com.wusy.wusylibrary.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileUtil {
    /**
     * 获取APP可操作的文件夹
     */
    public static String getFileDir(Context context) {
        String filePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            filePath = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
        } else {
            //外部存储不可用
            filePath = context.getFilesDir().getPath();
        }
        return filePath;
    }
    /**
     * 创建apk文件
     *
     * @param name void
     * @throws
     * @since 1.0.0
     */
    public static void createFile(String name) {

        if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
                .getExternalStorageState())) {
            File updateDir = new File(Environment.getExternalStorageDirectory()
                    + "/" + "workdb/");
            File updateFile = new File(updateDir + "/" + name);
            if (!updateDir.exists()) {
                updateDir.mkdirs();
            }
            if (!updateFile.exists()) {
                try {
                    updateFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /**
     * 文件读取
     *
     * @param fileDir
     * @param fileName
     * @return
     */
    public static String readFileContent(String fileDir, String fileName) {
        File file = new File(fileDir, fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 从文件中读取内容
     * @param fileDir
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFileContentBig(String fileDir, String fileName) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        StringBuffer sbf = new StringBuffer();
        try {
            inputStream = new FileInputStream(new File(fileDir, fileName));
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                sbf.append(line);
            }
        } catch (IOException e) {

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return sbf.toString();
    }
}
