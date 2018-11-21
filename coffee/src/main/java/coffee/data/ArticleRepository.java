package coffee.data;

import org.springframework.data.repository.CrudRepository;

import coffee.*;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	
}
