package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    String receiverEmail;
    String subject;
    String message;
}
