package com.mqgroup24.lectura360.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCommentBookFormDTO {

    private Long userId;

    private Long bookId;

    private String comment;

}
