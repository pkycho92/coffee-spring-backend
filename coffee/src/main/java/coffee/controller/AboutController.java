package coffee.controller;

import coffee.*;
import coffee.data.AboutRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="abouts",
				produces="application/json",
				consumes="application/json")
@CrossOrigin(origins="*")
public class AboutController {

	private AboutRepository aboutRepo;
	
	public AboutController(AboutRepository aboutRepo) {
		this.aboutRepo = aboutRepo;
	}
	
	@GetMapping
	public Iterable<About> getAbouts() {
		return aboutRepo.findAllByOrderByPosition();
	}
	
	@GetMapping("/{aboutId}")
	public ResponseEntity<About> aboutById(@PathVariable("aboutId") Long id) {
		Optional<About> optAbout = aboutRepo.findById(id);
		if (optAbout.isPresent()){
			return new ResponseEntity<>(optAbout.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public About postAbout(@RequestBody About about) {
		return aboutRepo.save(about);
	}
	
	@PatchMapping("/{aboutId}")
	public About patchAbout(@PathVariable("aboutId") Long aboutId, @RequestBody About patch) {
		
		About about = aboutRepo.findById(aboutId).get();
		if (patch.getPosition() != null) {
			about.setPosition(patch.getPosition());
		}
		return aboutRepo.save(about);
	}
	
	@DeleteMapping("/{aboutId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteEvent (@PathVariable("aboutId") Long aboutId) {
		try {
			aboutRepo.deleteById(aboutId);
		}catch (EmptyResultDataAccessException e) {
		}
	}
}
