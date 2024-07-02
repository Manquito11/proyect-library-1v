package com.mqgroup24.lectura360.mapper;

import com.mqgroup24.lectura360.dao.NewCommentBookDTO;
import com.mqgroup24.lectura360.entity.CommentBook;
import org.springframework.stereotype.Component;

@Component
public class CommentBookMapper {

    public static CommentBook toConvertCommentBook (NewCommentBookDTO newCommentBookDTO) {
        return new CommentBook(
                newCommentBookDTO.getUser(),
                newCommentBookDTO.getBook(),
                newCommentBookDTO.getComment()
        );
    }

}
