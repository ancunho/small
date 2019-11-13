package com.small.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 单文件上传
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file, String path);

}
