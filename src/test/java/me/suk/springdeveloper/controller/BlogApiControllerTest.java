package me.suk.springdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.suk.springdeveloper.domain.Article;
import me.suk.springdeveloper.dto.AddArticleReguest;
import me.suk.springdeveloper.dto.UpdateArticleReguest;
import me.suk.springdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private BlogRepository blogRepository;

    @BeforeEach
    void cleanUp() {
        blogRepository.deleteAll();
    }

    @Test
    @DisplayName("글을 등록한다")
    void addArticle() throws Exception {
        var request = new AddArticleReguest("새 제목", "새 내용");

        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("새 제목"))
                .andExpect(jsonPath("$.content").value("새 내용"));

        assertThat(blogRepository.findAll()).singleElement().satisfies(article -> {
            assertThat(article.getTitle()).isEqualTo("새 제목");
            assertThat(article.getContent()).isEqualTo("새 내용");
        });
    }

    @Test
    @DisplayName("글 목록을 조회한다")
    void getAllArticles() throws Exception {
        blogRepository.save(Article.builder().title("첫 번째").content("첫 내용").build());
        blogRepository.save(Article.builder().title("두 번째").content("둘째 내용").build());

        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].title", org.hamcrest.Matchers.containsInAnyOrder("첫 번째", "두 번째")));
    }

    @Test
    @DisplayName("ID로 글 하나를 조회한다")
    void getArticle() throws Exception {
        Article saved = blogRepository.save(Article.builder().title("조회 제목").content("조회 내용").build());

        mockMvc.perform(get("/api/articles/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("조회 제목"))
                .andExpect(jsonPath("$.content").value("조회 내용"));
    }

    @Test
    @DisplayName("글을 삭제한다")
    void deleteArticle() throws Exception {
        Article saved = blogRepository.save(Article.builder().title("삭제 제목").content("삭제 내용").build());

        mockMvc.perform(delete("/api/articles/{id}", saved.getId()))
                .andExpect(status().isOk());

        assertThat(blogRepository.findById(saved.getId())).isEmpty();
    }

    @Test
    @DisplayName("기존 글을 수정한다")
    void updateArticle() throws Exception {
        Article saved = blogRepository.save(Article.builder().title("이전 제목").content("이전 내용").build());
        var request = new UpdateArticleReguest("수정 제목", "수정 내용");

        mockMvc.perform(put("/api/articles/{id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("수정 제목"))
                .andExpect(jsonPath("$.content").value("수정 내용"));

        assertThat(blogRepository.count()).isEqualTo(1);
        Article updated = blogRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo("수정 제목");
        assertThat(updated.getContent()).isEqualTo("수정 내용");
    }
}
