package com.hthk.calypso.cas.action;

import com.hthk.fintech.action.basic.AbstractApplication;
import com.hthk.fintech.config.AppConfig;
import com.hthk.fintech.config.FintechStaticData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Author: Rock CHEN
 * @Date: 2023/12/20 16:55
 */
@SpringBootApplication(scanBasePackages = "com.hthk")
public class CalypsoAgentServiceApplication extends AbstractApplication {

    private final static Logger logger = LoggerFactory.getLogger(CalypsoAgentServiceApplication.class);

    private void start(String[] args) {

        String configPath =
                new StringBuilder("spring.config.location=")
                        .append("classpath:/")
                        .append(FintechStaticData.DEFAULT_CLASS_PATH)
                        .append("/")
                        .toString();

        String appName = null;
        try {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(CalypsoAgentServiceApplication.class)
                    .properties(configPath);
            builder.run(args);
            logger.info("{} kick-off", builder.context().getBean(AppConfig.class).getAppName());
        } catch (Throwable e) {
            logger.error("{} exit with Exception:\r\n{}", appName, e.getMessage(), e);
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        new CalypsoAgentServiceApplication().start(args);
    }

}
