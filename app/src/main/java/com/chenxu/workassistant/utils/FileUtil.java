package com.chenxu.workassistant.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.chenxu.workassistant.R;
import com.chenxu.workassistant.config.Constant;
import com.chenxu.workassistant.fileMenage.FileBean;
import com.chenxu.workassistant.fileSearch.SearchBean;
import com.chenxu.workassistant.setting.SettingActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Android on 2018/3/26.
 */

public class FileUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化文件大小
     *
     * @param size 文件大小
     * @return
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    public static String convertFileTime(long time){
        return dateFormat.format(new Date(time));
    }

    /**
     * 文件排序
     *
     * @param fileArrayList 文件集
     * @return
     */
    public static ArrayList<FileBean> FilesSort(ArrayList<FileBean> fileArrayList) {
        //排序文件夹
        for (int i = 0; i < fileArrayList.size() - 1; i++) {
            for (int j = i + 1; j < fileArrayList.size(); j++) {
                char char1 = fileArrayList.get(i).getFile().getName().charAt(0);
                char char2 = fileArrayList.get(j).getFile().getName().charAt(0);
                if (char1 > char2 && fileArrayList.get(i).getFile().isDirectory() && fileArrayList.get(j).getFile().isDirectory()) {
                    FileBean temp = fileArrayList.get(i);
                    fileArrayList.set(i, fileArrayList.get(j));
                    fileArrayList.set(j, temp);
                }
            }
        }
        //文件夹在前，文件在后
        for (int i = 0; i < fileArrayList.size() - 1; i++) {
            for (int j = i + 1; j < fileArrayList.size(); j++) {
                if (fileArrayList.get(i).getFile().isFile() && fileArrayList.get(j).getFile().isDirectory()) {
                    FileBean temp = fileArrayList.get(i);
                    fileArrayList.set(i, fileArrayList.get(j));
                    fileArrayList.set(j, temp);
                }
            }
        }

        for (int i = 0; i < fileArrayList.size() - 1; i++) {
            for (int j = i + 1; j < fileArrayList.size(); j++) {
                char char1 = fileArrayList.get(i).getFile().getName().charAt(0);
                char char2 = fileArrayList.get(j).getFile().getName().charAt(0);
                if (char1 > char2 && fileArrayList.get(i).getFile().isFile() && fileArrayList.get(j).getFile().isFile()) {
                    FileBean temp = fileArrayList.get(i);
                    fileArrayList.set(i, fileArrayList.get(j));
                    fileArrayList.set(j, temp);
                }
            }
        }
        return fileArrayList;
    }

    /**
     * 1文件夹、2音乐、3文件、4代码、5Excel、6图片、7PDF、8PPT、9TXT、10视频、11Word、12压缩
     *
     * @param file
     */
    public static int fileType(File file) {
        if (file.isDirectory()) {
            return 1;
        } else {
            String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            int type;
            switch (suffix) {
                case "mp3":
                    type = 2;
                    break;
                case "wav":
                    type = 2;
                    break;
                case "flac":
                    type = 2;
                    break;
                case "ape":
                    type = 2;
                    break;

                case "java":
                    type = 4;
                    break;
                case "html":
                    type = 4;
                    break;
                case "js":
                    type = 4;
                    break;
                case "css":
                    type = 4;
                    break;
                case "json":
                    type = 4;
                    break;
                case "xml":
                    type = 4;
                    break;

                case "xlsx":
                    type = 5;
                    break;
                case "xls":
                    type = 5;
                    break;

                case "png":
                    type = 6;
                    break;
                case "jpg":
                    type = 6;
                    break;
                case "gif":
                    type = 6;
                    break;
                case "bmp":
                    type = 6;
                    break;

                case "pdf":
                    type = 7;
                    break;

                case "ppt":
                    type = 8;
                    break;
                case "pptx":
                    type = 8;
                    break;

                case "txt":
                    type = 9;
                    break;

                case "mp4":
                    type = 10;
                    break;
                case "flv":
                    type = 10;
                    break;
                case "avi":
                    type = 10;
                    break;
                case "3gp":
                    type = 10;
                    break;
                case "mkv":
                    type = 10;
                    break;
                case "rmvb":
                    type = 10;
                    break;
                case "wmv":
                    type = 10;
                    break;

                case "doc":
                    type = 11;
                    break;
                case "docx":
                    type = 11;
                    break;

                case "rar":
                    type = 12;
                    break;
                case "zip":
                    type = 12;
                    break;

                default:
                    type = 3;
                    break;
            }
            return type;
        }
    }

    public static int fileIconForType(File file) {
        if (file.isDirectory()) {
            return R.drawable.file_folder;
        } else {
            String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            int type;
            switch (suffix) {
                case "mp3":
                    type = R.drawable.file_audio;
                    break;
                case "wav":
                    type = R.drawable.file_audio;
                    break;
                case "flac":
                    type = R.drawable.file_audio;
                    break;
                case "ape":
                    type = R.drawable.file_audio;
                    break;

                case "java":
                    type = R.drawable.file_code;
                    break;
                case "html":
                    type = R.drawable.file_code;
                    break;
                case "js":
                    type = R.drawable.file_code;
                    break;
                case "css":
                    type = R.drawable.file_code;
                    break;
                case "json":
                    type = R.drawable.file_code;
                    break;
                case "xml":
                    type = R.drawable.file_code;
                    break;

                case "xlsx":
                    type = R.drawable.file_excel;
                    break;
                case "xls":
                    type = R.drawable.file_excel;
                    break;

                case "png":
                    type = R.drawable.file_img;
                    break;
                case "jpg":
                    type = R.drawable.file_img;
                    break;
                case "gif":
                    type = R.drawable.file_img;
                    break;
                case "bmp":
                    type = R.drawable.file_img;
                    break;

                case "pdf":
                    type = R.drawable.file_pdf;
                    break;

                case "ppt":
                    type = R.drawable.file_ppt;
                    break;
                case "pptx":
                    type = R.drawable.file_ppt;
                    break;

                case "txt":
                    type = R.drawable.file_txt;
                    break;

                case "mp4":
                    type = R.drawable.file_video;
                    break;
                case "flv":
                    type = R.drawable.file_video;
                    break;
                case "avi":
                    type = R.drawable.file_video;
                    break;
                case "3gp":
                    type = R.drawable.file_video;
                    break;
                case "mkv":
                    type = R.drawable.file_video;
                    break;
                case "rmvb":
                    type = R.drawable.file_video;
                    break;
                case "wmv":
                    type = R.drawable.file_video;
                    break;

                case "doc":
                    type = R.drawable.file_word;
                    break;
                case "docx":
                    type = R.drawable.file_word;
                    break;

                case "rar":
                    type = R.drawable.file_zip;
                    break;
                case "zip":
                    type = R.drawable.file_zip;
                    break;

                default:
                    type = R.drawable.file_blank;
                    break;
            }
            return type;
        }
    }

    public static String repeatFileName(String filename){
        String name = filename.substring(0,filename.lastIndexOf("."));
        String suffix = filename.substring(filename.lastIndexOf("."));
        return name + "(1)" + suffix;
    }

    public static int fileIconForType(String filename) {
        if (filename.indexOf(".") == -1) {
            return R.drawable.file_blank;
        } else {
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            int type;
            switch (suffix) {
                case "mp3":
                    type = R.drawable.file_audio;
                    break;
                case "wav":
                    type = R.drawable.file_audio;
                    break;
                case "flac":
                    type = R.drawable.file_audio;
                    break;
                case "ape":
                    type = R.drawable.file_audio;
                    break;

                case "java":
                    type = R.drawable.file_code;
                    break;
                case "html":
                    type = R.drawable.file_code;
                    break;
                case "js":
                    type = R.drawable.file_code;
                    break;
                case "css":
                    type = R.drawable.file_code;
                    break;
                case "json":
                    type = R.drawable.file_code;
                    break;
                case "xml":
                    type = R.drawable.file_code;
                    break;

                case "xlsx":
                    type = R.drawable.file_excel;
                    break;
                case "xls":
                    type = R.drawable.file_excel;
                    break;

                case "png":
                    type = R.drawable.file_img;
                    break;
                case "jpg":
                    type = R.drawable.file_img;
                    break;
                case "gif":
                    type = R.drawable.file_img;
                    break;
                case "bmp":
                    type = R.drawable.file_img;
                    break;

                case "pdf":
                    type = R.drawable.file_pdf;
                    break;

                case "ppt":
                    type = R.drawable.file_ppt;
                    break;
                case "pptx":
                    type = R.drawable.file_ppt;
                    break;

                case "txt":
                    type = R.drawable.file_txt;
                    break;

                case "mp4":
                    type = R.drawable.file_video;
                    break;
                case "flv":
                    type = R.drawable.file_video;
                    break;
                case "avi":
                    type = R.drawable.file_video;
                    break;
                case "3gp":
                    type = R.drawable.file_video;
                    break;
                case "mkv":
                    type = R.drawable.file_video;
                    break;
                case "rmvb":
                    type = R.drawable.file_video;
                    break;
                case "wmv":
                    type = R.drawable.file_video;
                    break;

                case "doc":
                    type = R.drawable.file_word;
                    break;
                case "docx":
                    type = R.drawable.file_word;
                    break;

                case "rar":
                    type = R.drawable.file_zip;
                    break;
                case "zip":
                    type = R.drawable.file_zip;
                    break;

                default:
                    type = R.drawable.file_blank;
                    break;
            }
            return type;
        }
    }


    /**
     * 删除文件
     *
     * @param file 文件
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }


    public static File copyFile(File oldFile,File newPath){
        if (oldFile.isDirectory()){//是目录
            File file = new File(newPath.getAbsolutePath()+"/"+oldFile.getName());
            file.mkdirs();
            File[] files = oldFile.listFiles();
            for (File item: files){
                copyFile(item,file);
            }
            return file;
        }else { //是文件
            return copy(oldFile.getAbsolutePath(),newPath.getAbsolutePath()+"/"+oldFile.getName());
        }
    }

    public static File copy (String start,String end){
        File staFile = new File(start);
        File endFile = new File(end);
        try {
            InputStream fosfrom = new FileInputStream(staFile);
            OutputStream fosto = new FileOutputStream(endFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosto.flush();
            fosfrom.close();
            fosto.close();
        }catch (Exception e){

        }
        return endFile;
    }


    /**
     * 递归查找文件
     * @param baseDirName  查找的文件夹路径
     * @param targetFileName  需要查找的文件名
     * @param fileList  查找到的文件集合
     */
    public synchronized static void findFiles(String baseDirName, String targetFileName,boolean filter, List<SearchBean> fileList) {

        File baseDir = new File(baseDirName);       // 创建一个File对象
        String tempName = null;
        //判断目录是否存在
        File tempFile;
        File[] files = baseDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            tempName = tempFile.getName();
            if(tempFile.isDirectory()){
                if (tempName.startsWith(".")){
                    continue;
                }
                if (filter){
                    if (tempName.indexOf("Android") != -1 ||tempName.indexOf("cache") != -1||tempName.indexOf("Cache") != -1||tempName.indexOf("temp") != -1||tempName.indexOf("Temp") != -1||tempName.indexOf("tencent") != -1||tempName.indexOf("Tencent") != -1){
                        continue;
                    }else {
                        findFiles(tempFile.getAbsolutePath(), targetFileName,filter, fileList);
                    }
                }else {
                    findFiles(tempFile.getAbsolutePath(), targetFileName,filter, fileList);
                }
            }else if(tempFile.isFile()){
                if(tempName.indexOf(targetFileName) != -1){
                    // 匹配成功，将文件名添加到结果集
                    fileList.add(new SearchBean(tempFile,i));
                    Log.e("findFile:",tempName);
                }
            }
        }
    }

    /**
     * 递归查找文件
     * @param baseDirName  查找的文件夹路径
     * @param type  需要查找的文件类型
     * @param fileList  查找到的文件集合
     */
    public synchronized static void findFilesBySuffix(String baseDirName,int type,boolean filter, List<SearchBean> fileList) {

        File baseDir = new File(baseDirName);       // 创建一个File对象
        String tempName = null;
        //判断目录是否存在
        File tempFile;
        File[] files = baseDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            tempName = tempFile.getName();
            if(tempFile.isDirectory()){
                if (tempName.startsWith(".")){
                    continue;
                }
                if (filter){
                    if (tempName.indexOf("Android") != -1 ||tempName.indexOf("cache") != -1||tempName.indexOf("Cache") != -1||tempName.indexOf("temp") != -1||tempName.indexOf("Temp") != -1||tempName.indexOf("tencent") != -1||tempName.indexOf("Tencent") != -1){
                        continue;
                    }else {
                        findFilesBySuffix(tempFile.getAbsolutePath(), type,filter, fileList);
                    }
                }else {
                    findFilesBySuffix(tempFile.getAbsolutePath(), type,filter, fileList);
                }
            }else if(tempFile.isFile()){
                switch (type){
                    case 1:
                        if(tempName.endsWith(".doc")||tempName.endsWith(".docx")){
                            // 匹配成功，将文件名添加到结果集
                            fileList.add(new SearchBean(tempFile,i));
                        }
                        break;
                    case 2:
                        if(tempName.endsWith(".xls")||tempName.endsWith(".xlsx")){
                            // 匹配成功，将文件名添加到结果集
                            fileList.add(new SearchBean(tempFile,i));
                        }
                        break;
                    case 3:
                        if(tempName.endsWith(".ppt")||tempName.endsWith(".pptx")){
                            // 匹配成功，将文件名添加到结果集
                            fileList.add(new SearchBean(tempFile,i));
                        }
                        break;
                }
            }
        }
    }

//    /**
//     * 复制整个文件夹内容
//     *
//     * @param oldPath String 原文件路径 如：c:/fqf
//     * @param newPath String 复制后路径 如：f:/fqf/ff
//     * @return boolean
//     */
//    public void copyFolder(File oldFile, String newPath) {
//
//        try {
//            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
//            String[] file = oldFile.list();
//            File temp = null;
//            for (int i = 0; i < file.length; i++) {
//                if (oldFile.endsWith(File.separator)) {
//                    temp = new File(oldPath + file[i]);
//                } else {
//                    temp = new File(oldPath + File.separator + file[i]);
//                }
//
//                if (temp.isFile()) {
//                    FileInputStream input = new FileInputStream(temp);
//                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
//                    byte[] b = new byte[1024 * 5];
//                    int len;
//                    while ((len = input.read(b)) != -1) {
//                        output.write(b, 0, len);
//                    }
//                    output.flush();
//                    output.close();
//                    input.close();
//                }
//                if (temp.isDirectory()) {//如果是子文件夹
//                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
//                }
//            }
//        } catch (Exception e) {
//            Log.e("move", "复制整个文件夹内容操作出错");
//            e.printStackTrace();
//
//        }

    //}

    /**
     * 3.质量压缩
     * 设置bitmap options属性，降低图片的质量，像素不会减少
     * 第一个参数为需要压缩的bitmap图片对象，第二个参数为压缩后图片保存的位置
     * 设置options 属性0-100，来实现压缩
     *
     * @param bmp
     * @param file
     */
    public static void qualityCompress(Bitmap bmp, File file) {
        // 0-100 100为不压缩
        int quality = 100;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
