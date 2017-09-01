package com.happylifeplat.transaction.tx.manager.eureka;

import com.happylifeplat.transaction.tx.manager.config.Constant;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: .</p>
 *
 * <p>Copyright: 2015-2017 happylifeplat.com All Rights Reserved</p>
 *
 * @author yu.xiao@happylifeplat.com
 * @version 1.0
 * @date 2017/7/10 19:21
 * @since JDK 1.8
 */
@Service
public class DiscoveryService {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryService.class);

    private final EurekaClient eurekaClient;

    @Autowired(required = false)
    public DiscoveryService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public List<InstanceInfo> getConfigServiceInstances() {
        Application application = eurekaClient.getApplication(Constant.applicationName);
        if (application == null) {
            LOGGER.error("获取eureka服务失败！");
        }
        return application != null ? application.getInstances() : new ArrayList<>();
    }
}
