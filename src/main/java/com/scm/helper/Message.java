package com.scm.helper;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message {

    private String content;
    private MessageType type = MessageType.blue;



}


