package com.cashwu.todolist;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cash
 */
@Component
@ConfigurationProperties(prefix = "my.app")
@Getter
@Setter
public class MyProperties {

    private String header;
    private String footer;
    private String video;
}

