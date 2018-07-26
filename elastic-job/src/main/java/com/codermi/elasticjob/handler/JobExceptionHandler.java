package com.codermi.elasticjob.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Set;

/**
 * @author qiudm
 * @date 2018/7/23 18:23
 * @desc
 */
public class JobExceptionHandler implements com.dangdang.ddframe.job.executor.handler.JobExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(JobExceptionHandler.class);
    private static String port;

    public JobExceptionHandler() {
        port = getServerPort();
    }

    public void handleException(String s, Throwable throwable) {
        logger.error("======================================");
        logger.error("Job {} exception occur in job processing ", s);

        try {
            String title = String.format("【%s】任务异常", s);
            String msg = String.format("#### 【%s】任务异常 \n > ##### address:%s:%s \n > ##### error:%s"
                    , s, InetAddress.getLocalHost().getHostAddress()
                    , port
                    , throwable.getMessage());

            logger.error("title:{} ",title);
            logger.error("msg:{}",msg);

            //dingding群通知告警
          //  DingDingMessageUtil.sendTextMessage(title,msg);
            //邮件通知告警
           // MailUtil.sendMail(ElasticJobConfiguration.getConfig().getMaintenance(), String.format("【%s】任务异常", s), msg);
        } catch (Exception e) {
            logger.error("handleException error:{}", e.getMessage());
        }
    }


    private String getServerPort() {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = null;
        try {
            objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        } catch (MalformedObjectNameException e) {
            return "";
        }
        return objectNames.iterator().next().getKeyProperty("port");
    }

}
