package com.example.helloworld.core

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

public class Saying {
    @JsonProperty
    long id

    @JsonProperty
    @Length(max = 3)
    String content

    public Saying(long id, String content) {
        this.id = id
        this.content = content
    }

}
