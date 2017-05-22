package com.szss.demo;

import com.szss.demo.entities.Message;
import com.szss.demo.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by zcg on 2017/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private MessageRepository repository;

    @Test
    public void testSave() {
        Message m = new Message(1L, "Chuangang Zhu", "大家早上好！", new Date());
        repository.save(m);
        m = new Message(2L, "Wei Ji", "今天天气真好，我们出去玩吧！", new Date());
        repository.save(m);
        m = new Message(3L, "Chuangang Zhu", "好呀，我也好久没有出去玩了，我们去哪里玩呢？去逛街还是去烧烤？", new Date());
        repository.save(m);
        m = new Message(4L, "Wei Ji", "我们去逛街吧~~~", new Date());
        repository.save(m);
    }


    @Test
    public void testSelect() {
        List<Message> list = repository.findByUsername("Wei Ji");
        list.forEach(m -> System.out.println(m));
    }

    @Test
    public void testUpdate() {
        Message m = repository.findOne(1L);
        m.setContext("Hello,早呀！");
        repository.save(m);
    }

    @Test
    public void testSearch() {
        Date from = new Date(2017 - 1900, 4, 16);
        Date to = new Date(2017 - 1900, 4, 17);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("context", "我们"))
                .withFilter(boolQuery().must(matchQuery("username", "Chuangang Zhu"))
                        .must(rangeQuery("date").from(from.getTime()).to(to.getTime())))
                .build();
        Page<Message> page = repository.search(searchQuery);
        System.out.println("total:" + page.getTotalElements());
        System.out.println("page size:" + page.getTotalPages());
        page.iterator().forEachRemaining(m -> System.out.println(m));
    }

    @Test
    public void testDelete() {
        LongStream.range(1, 4).forEach(i -> repository.delete(i));
    }

}
