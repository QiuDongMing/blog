//package com.codermi.blog.controller.mq;
//
//import com.codermi.blog.mq.producer.Sender;
//import com.codermi.common.base.utils.JsonResult;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * @author qiudm
// * @date 2018/8/1 16:07
// * @desc
// */
//@Api(value = "mq发送相关")
////@RequestMapping("mqfanout")
//@RestController
//public class SenderController {
//
//    @Autowired
//    private Sender sender;
//
//    @ApiOperation(value = "发送消息", httpMethod = "POST" )
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "access-token", value = "token", required = true, dataType = "String", paramType = "header"),
//            @ApiImplicitParam(name = "msg", value = "消息内容", dataType = "string", paramType = "query")
//    })
//    @PostMapping("/send")
//    public JsonResult publish(String msg) {
//        sender.send(msg);
//        return JsonResult.SUCCESS();
//    }
//
//
//
//}
