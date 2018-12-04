package coffee.controller;

import coffee.*;
import coffee.data.ArticleRepository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController 
@RequestMapping(path="articles",
				produces="application/json",
				consumes="application/json")
@CrossOrigin(origins="*")
public class ArticleController {

	private ArticleRepository articleRepo;

	public ArticleController(ArticleRepository articleRepo) {
		this.articleRepo = articleRepo;
	}

	@GetMapping
	public Iterable<Article> getArticles() {
		return articleRepo.findAllByOrderByPosition();
	}

	@GetMapping("/{articleId}")
	public ResponseEntity<Article> aboutById(@PathVariable("articleId") Long id) {
		Optional<Article> optArticle = articleRepo.findById(id);
		if (optArticle.isPresent()){
			return new ResponseEntity<>(optArticle.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Article postArticle(@RequestBody Article Article) {
		return articleRepo.save(Article);
	}

	@PatchMapping("/{articleId}")
	public Article patchArticle(@PathVariable("articleId") Long articleId, @RequestBody Article patch) {
		
		Article article = articleRepo.findById(articleId).get();
		if (patch.getPosition() != null) {
			article.setPosition(patch.getPosition());
		}
		return articleRepo.save(article);
	}

	@DeleteMapping("/{articleId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteArticle(@PathVariable("articleId") Long id) {
		try {
			articleRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
