package com.muunioi.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
                                /* JpaRepository<Entity 클래스, PK 타입> -> 자동 CRUD 생성
                                    Entity 클래스와 Entity Repository는 같이 위치해야 함 */
}
