package com.codermi.blog.controller.perms;

import com.codermi.blog.user.data.request.RoleRequest;
import com.codermi.blog.user.service.IRoleService;
import com.codermi.common.base.utils.JsonResult;
import com.codermi.sercurity.utils.ReqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiudm
 * @date 2018/11/14 17:22
 * @desc
 */
@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('role:add')")
    public JsonResult add(@RequestBody @Validated RoleRequest request) {
        roleService.addRole(request, ReqUtil.instance.getUserId());
        return JsonResult.SUCCESS();
    }


}
