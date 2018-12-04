package coffee.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import coffee.*;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findAllByOrderByPosition ();
}
