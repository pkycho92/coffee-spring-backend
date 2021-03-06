package coffee.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import coffee.*;

public interface AboutRepository extends CrudRepository<About, Long> {
	List<About> findAllByOrderByPosition ();
}
