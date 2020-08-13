package com.hangil.book.springboot.domain.posts;


import com.hangil.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter   //롬복 어노테이   //클래스 내의 모든 필드에 Getter 메소드 자동 생성
@NoArgsConstructor// 롬복 어노테이션  기본 생성자 자동 추가 기능 public Posts() {}
@Entity   //JPA annotation
public class Posts extends BaseTimeEntity {

@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

@Column(length=500,nullable=false)
private String title;

@Column(columnDefinition="TEXT",nullable =false)
    private String content;

private String author;

@Builder
public Posts(String title, String content, String author){

    this.title=title;
    this.content=content;
    this.author=author;
}

public void update(String title, String content)
{
    this.title=title;
    this.content=content;
}

}
