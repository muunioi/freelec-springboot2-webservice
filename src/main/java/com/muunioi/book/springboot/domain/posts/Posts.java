package com.muunioi.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // Lombok 어노테이션, getter 자동생성
@NoArgsConstructor // Lombok 어노테이션, 기본 생성자 자동 추가
@Entity // JPA 어노테이션, table과 링크 될 클래스 임을 나타냄 (보통 Entity Class 라고 함)
// -> 실제 쿼리를 날리는 것이 아니라 Entity 클래스 수정을 통해 DB 데이터 작업
public class Posts extends BaseTimeEntity{

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 생성규칙
    private Long id; //PK = Long & Auto_Increment 추천

    @Column(length = 500, nullable = false) //테이블의 컬럼, 기본 값 외의 사항 필요시 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //setter 의 역할 (값 할당)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/*
Entity 클래스에서는 절대 **Setter 메소드를 만들지 않음**
 -> 필요하다면 명확한 목적/의도를 나타낼 수 있는 메소드를 추가
 & 생성자와 public 메소드 호출을 통해 값 할당/변경 또는 @Builder를 통해 제공되는 builder class 사용
*/
