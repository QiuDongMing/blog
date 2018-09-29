package com.codermi.blog.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiudm
 * @date 2018/9/28 18:41
 * @desc
 */
public interface IFileService {


    String uploadFile(MultipartFile file);



}
