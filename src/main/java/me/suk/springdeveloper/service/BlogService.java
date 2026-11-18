package me.suk.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.suk.springdeveloper.domain.Article;
import me.suk.springdeveloper.dto.AddArticleReguest;
import me.suk.springdeveloper.dto.UpdateArticleReguest;
import me.suk.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    //@Autowired

    private final BlogRepository blogRepository;

    public Article save(AddArticleReguest dto) {
        return blogRepository.save(dto.toEntity());

    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public List<Article> getAll() {
        return blogRepository.findAll();
    }

    public void deleteArticle(Long id) {
        blogRepository.deleteById(id);
    }
    public Article updateArticle(Long id, UpdateArticleReguest reg) {
        // id로 기존 글 찾기
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

       article.update(reg.getTitle(), reg.getContent());

        // 변경한 글 저장
        return blogRepository.save(article);
    }


}

