package com.codermi.blog.controller.perms;

import com.codermi.blog.user.data.request.PermissionRequest;
import com.codermi.blog.user.service.IPermissionService;
import com.codermi.common.base.utils.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiudm
 * @date 2018/11/14 16:04
 * @desc
 */
@Api(value = "权限相关")
@RestController
@RequestMapping("/perm")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('perm:add')")
    public JsonResult add(@RequestBody @Validated PermissionRequest request) {
        permissionService.addPermission(request);
        return JsonResult.SUCCESS();
    }


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('perm:list')")
    public JsonResult list() {
        return JsonResult.SUCCESS(permissionService.getPermsList());
    }







}
