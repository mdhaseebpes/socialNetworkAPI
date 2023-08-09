package com.social.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;


@Setter
@Getter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MainPost {

    private int userId;
    private String title;
    private String body;
    private int id;

    public MainPost(int userId, String title, String body,int id) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public MainPost(){

    }


}
