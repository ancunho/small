package com.small.service.impl;

import com.google.common.collect.Lists;
import com.small.service.FileService;
import com.small.util.DateUtil;
import com.small.util.FTPUtil;
import com.small.util.PropertiesUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class FileServiceImpl implements FileService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 单文件上传
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file, String path) {
        //文件名
        String fileName = file.getOriginalFilename();
        //文件扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //文件新名字
        String uploadFileName = DateUtil.getTime() + "__" + UUID.randomUUID().toString() + "." + fileExtensionName;
        //文件路径 + "/"
        String remotePath = DateUtil.getDays() + File.separator;

        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            //上传到FTP
            FTPUtil.uploadFile(remotePath, Lists.newArrayList(targetFile));
            //上传到FTP后删除原来的图片
//            targetFile.delete();
        } catch (Exception e) {
            logger.error("上传文件异常：",e);
            return null;
        }
        String finalFileName = PropertiesUtil.getProperty("ftp.server.http.prefix") + remotePath + targetFile.getName();

        return finalFileName;
    }



    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
