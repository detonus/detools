package com.detoworks.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

/**
 * Created by Banach on 2017-01-26.
 */
public class NotH2EnvCondition implements Condition {

    Logger logger = LogManager.getLogger(NotH2EnvCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean h2Profile = Arrays.stream(context.getEnvironment().getActiveProfiles())
                .filter(el -> "h2".equals(el)).findFirst().isPresent();
        logger.info("h2ProfileActive: " + h2Profile);
        return !h2Profile;
    }
}
