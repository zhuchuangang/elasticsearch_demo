package com.szss.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;


/**
 * Created by zcg on 2017/5/15.
 */
@Data
@NoArgsConstructor
@Document(indexName = "message_index", type = "message_type")
public class Message {
    @Id
    private Long id;
    private String username;
    private String context;
    private long date;

    public Message(Long id, String username, String context, long date) {
        this.id = id;
        this.username = username;
        this.context = context;
        this.date = date;
    }
}
