package coffee.controller;

import coffee.*;
import coffee.MenuItem.Type;
import coffee.data.MenuItemRepository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="menuItems",
				produces="application/json",
				consumes="application/json")
@CrossOrigin(origins="*")
public class MenuItemController {

	private MenuItemRepository menuItemRepo;
	
	public MenuItemController(MenuItemRepository menuItemRepo) {
		this.menuItemRepo = menuItemRepo;
	}
	
	@GetMapping("types/{menuItemType}")
	public Iterable<MenuItem> getMenuItems(@PathVariable("menuItemType") String type) {
		return menuItemRepo.findAllByTypeOrderByPosition(Type.valueOf(type));
	}
	
	@GetMapping("/{menuItemId}")
	public ResponseEntity<MenuItem> aboutById(@PathVariable("menuItemId") Long id) {
		Optional<MenuItem> optMenuItem = menuItemRepo.findById(id);
		if (optMenuItem.isPresent()){
			return new ResponseEntity<>(optMenuItem.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MenuItem postMenu(@RequestBody MenuItem menuItem) {
		return menuItemRepo.save(menuItem);
	}
	
	@PatchMapping("/{menuItemId}")
	public MenuItem patchMenuItem(@PathVariable("menuItemId") Long menuItemId, @RequestBody MenuItem patch) {
		
		MenuItem menuItem = menuItemRepo.findById(menuItemId).get();
		if (patch.getPosition() != null) {
			menuItem.setPosition(patch.getPosition());
		}
		return menuItemRepo.save(menuItem);
	}
	
	@DeleteMapping("/{menuItemId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMenuItem (@PathVariable("menuItemId") Long menuItemId) {
		try {
			menuItemRepo.deleteById(menuItemId);
		}catch (EmptyResultDataAccessException e) {
		}
	}
}