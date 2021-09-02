package com.etaskify.mailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDto {

    private String firstName;
    private String lastName;
    private String email;
    private String taskTitle;
}
