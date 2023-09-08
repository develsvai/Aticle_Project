package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor//이거 쓰면 별도록 생성자 작성 안해두됨

@ToString // To Storing 메소스 자동 생성

//클래스
public class ArticlesForm {
    private Long id; //필드 정의
    private String title;
    private String content;


//    public ArticlesForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "ArticlesForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    //toEntity 메서드는 ArticlesForm 클래스의 인스턴스를 Article 클래스의 인스턴스로 변환
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
