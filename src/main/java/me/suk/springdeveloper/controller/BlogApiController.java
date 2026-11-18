package me.suk.springdeveloper.controller;


import lombok.RequiredArgsConstructor;
import me.suk.springdeveloper.domain.Article;
import me.suk.springdeveloper.dto.AddArticleReguest;
import me.suk.springdeveloper.dto.ArticleResponse;
import me.suk.springdeveloper.dto.UpdateArticleReguest;
import me.suk.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
//    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
//        Article savedArticle = blogService.save(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleReguest request){
       Article savedArticle = blogService.save(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(new ArticleResponse(savedArticle));
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles(){
//        List<Article> articleList = blogService.gettAll();
//        List<ArticleResponse> articleRespomsesList = new ArrayList<>();
//        for(Article article: articleList){
//            ArticleResponse ar = new ArticleResponse(article);
//            articleRespomsesList.add(ar);
//        }
        List<ArticleResponse> articleRespomsesList = blogService.getAll()
                .stream()
                //.map(a -> new ArticleResponse(a))
                .map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleRespomsesList);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable("id")Long id){
    Article article = blogService.findById(id);
    return ResponseEntity.ok().body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void>deleteArticle(@PathVariable("id") Long id){
        blogService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @RequestBody UpdateArticleReguest reg) {

        Article article = blogService.updateArticle(id, reg);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // 다음주 현해 체킹 ㅋ

}
