package com.small.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String FTP_IP = PropertiesUtil.getProperty("ftp.server.ip");
    private static String FTP_USER = PropertiesUtil.getProperty("ftp.user");
    private static String FTP_PASSWORD = PropertiesUtil.getProperty("ftp.pass");

    private String ip;
    private int port;
    private String user;
    private String password;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public static boolean uploadFile(String remotePath, List<File> fileList) throws Exception {
        FTPUtil ftpUtil = new FTPUtil(FTP_IP, 21, FTP_USER, FTP_PASSWORD);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.FtpUploadFile(remotePath, fileList);

        logger.info("开始连接ftp服务器,结束上传,上传结果:{}");
        return result;
    }

    private boolean FtpUploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean isUpload = true;
        FileInputStream fileInputStream = null;
        //连接FTP服务器
        if (connectFtpServer(this.ip, this.port,this.user,this.password)) {
            try {
                if (!ftpClient.changeWorkingDirectory(remotePath)) {
                    if (!ftpClient.makeDirectory(remotePath)){
                        isUpload = false;
                    } else {
                        ftpClient.changeWorkingDirectory(remotePath);
                        logger.info("remotePath:{}", remotePath);
                    }
                }
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList) {
                    fileInputStream = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fileInputStream);
                }
            } catch (IOException e) {
                logger.error("上传文件异常, e");
                isUpload = false;
                e.printStackTrace();
            } finally {
                fileInputStream.close();
                ftpClient.disconnect();
            }
        }

        return isUpload;
    }

    private boolean connectFtpServer(String ip, int port, String user, String password) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, password);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
