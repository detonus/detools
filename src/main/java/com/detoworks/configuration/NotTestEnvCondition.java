package com.detoworks.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

/**
 * Created by Banach on 2017-01-26.
 */
public class NotTestEnvCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !Arrays.stream(context.getEnvironment().getActiveProfiles()).filter(el -> "travis".equals(el)).findFirst().isPresent();
    }
}
