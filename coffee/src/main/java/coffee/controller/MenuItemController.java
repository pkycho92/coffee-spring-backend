package coffee.controller;

import coffee.*;
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
	
	@GetMapping
	public Iterable<MenuItem> getMenuItems() {
		return menuItemRepo.findAll();
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
	
	@PutMapping("/{menuMenuId}")
	public MenuItem putAbout(@RequestBody MenuItem menuItem) {
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