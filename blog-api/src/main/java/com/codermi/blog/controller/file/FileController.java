package com.codermi.blog.controller.file;

import com.codermi.blog.file.service.IFileService;
import com.codermi.common.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiudm
 * @date 2018/9/28 18:12
 * @desc
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping("/open/upload")
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file) {
        return JsonResult.SUCCESS(fileService.uploadFile(file));
    }

}
