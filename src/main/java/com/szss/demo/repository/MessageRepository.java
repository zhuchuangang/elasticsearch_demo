package com.szss.demo.repository;

import com.szss.demo.entities.Message;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by zcg on 2017/5/15.
 */
public interface MessageRepository extends ElasticsearchRepository<Message, Long> {
    /**
     * spring data提供的根据方法名称的查询方式
     *
     * @param username
     * @return
     */
    List<Message> findByUsername(String username);

    /**
     * 使用Query注解指定查询语句
     *
     * @param username
     * @return
     */
    @Query("{\"bool\" : {\"must\" : [ {\"term\" : {\"username\" : \"?0\"}}，{\"term\" : {\"context\" : \"?1\"}} ]}}")
    //注意：需要替换的参数？需要加双引号；需要指定参数下标，从0开始
    List<Message> findByUsernameAndContextQuery(String username,String context);
}
