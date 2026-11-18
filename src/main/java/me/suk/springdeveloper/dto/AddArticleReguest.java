package me.suk.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.suk.springdeveloper.domain.Article;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleReguest {
    String title;
    private String content;

    public Article toEntity(){return Article.builder().title(title).content(content).build();
}
}
