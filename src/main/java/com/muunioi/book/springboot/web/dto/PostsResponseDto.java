package com.muunioi.book.springboot.web.dto;

import com.muunioi.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto { //Entity 일부 필드만 사용

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
