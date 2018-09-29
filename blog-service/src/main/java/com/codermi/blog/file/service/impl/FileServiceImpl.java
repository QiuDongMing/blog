package com.codermi.blog.file.service.impl;

import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.file.service.IFileService;
import com.codermi.common.base.utils.QiniuUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author qiudm
 * @date 2018/9/28 18:41
 * @desc
 */
@Service
public class FileServiceImpl implements IFileService {

    @Override
    public String uploadFile(MultipartFile file) {
        String res;
        try {
            res =  QiniuUtil.upload(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("文件上传失败");
        }
        return res;
    }
}
