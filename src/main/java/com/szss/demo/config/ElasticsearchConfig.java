package com.szss.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by zcg on 2017/5/15.
 */
@Configuration
//@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "com.szss.demo.repository")
public class ElasticsearchConfig {
}
