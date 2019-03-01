package com.codermi.blog.controller.perms;

import com.codermi.blog.user.data.request.PermissionRequest;
import com.codermi.blog.user.data.response.PermissionResponse;
import com.codermi.blog.user.service.IPermissionService;
import com.codermi.common.base.utils.JsonResult;
import com.codermi.sercurity.utils.ReqUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation(value = "获取菜单列表", response = PermissionResponse.class)
    @PreAuthorize("hasAuthority('perm:list')")
    @GetMapping("/list")
    public JsonResult list() {
        ReqUtil.instance.getUserId();
        return JsonResult.SUCCESS(permissionService.getPermsList());
    }


}
