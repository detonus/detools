package com.detoworks.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Banach on 2017-01-25.
 */
@Configuration
@PropertySource(value = "classpath:regexp.properties")
public class RegExpProps {

    @Value("${regexp.loaddbonstart}")
    private boolean loadDbOnStart;

    public boolean isLoadDbOnStart() {
        return loadDbOnStart;
    }

    public void setLoadDbOnStart(boolean loadDbOnStart) {
        this.loadDbOnStart = loadDbOnStart;
    }
}
