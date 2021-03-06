package coffee.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import coffee.*;
import coffee.MenuItem.Type;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
	List<MenuItem> findAllByTypeOrderByPosition (Type type);
}
