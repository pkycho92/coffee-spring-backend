package coffee.data;

import org.springframework.data.repository.CrudRepository;

import coffee.*;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
	
}
