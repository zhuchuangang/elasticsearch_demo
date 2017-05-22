package com.szss.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;


/**
 * Created by zcg on 2017/5/15.
 */
@Data @NoArgsConstructor @Document(indexName = "message_index", type = "message_type")
public class Message {
    @Id private Long id;
    @Field(index = FieldIndex.no) private String username;
    private String context;
    //@Field(type = FieldType.Date, index = FieldIndex.not_analyzed, format = DateFormat.date_hour_minute_second_millis)
    //解决date保存到es中变成long类型的问题
    //http://stackoverflow.com/questions/32042430/elasticsearch-spring-data-date-format-always-is-long
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date date;

    public Message(Long id, String username, String context, Date date) {
        this.id = id;
        this.username = username;
        this.context = context;
        this.date = date;
    }
}
