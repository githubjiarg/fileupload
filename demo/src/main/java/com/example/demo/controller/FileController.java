package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *  文件操作
 * @author jiaruiguo
 */
@RestController
@RequestMapping("fileController")
public class FileController {

    @Value("${local.file.path}")
    private String filePath;

    /**
     *  下载文件
     */
    @GetMapping("downFile")
    public void downFile(HttpServletResponse response,String fileId){
        File file = new File(filePath);
        if ( !file.exists() ) {
            throw new RuntimeException("文件不存在");
        }
        int len = 0;
        byte [] bytes = new byte[1024 * 2];
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        // 截取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        try {
            fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition","attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            while ( (len = bufferedInputStream.read(bytes)) != -1 ) {
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
