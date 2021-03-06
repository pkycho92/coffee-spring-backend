package coffee;

import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@RequiredArgsConstructor
@Entity
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private final Long id;
	@OrderBy
	private Integer position;
	private Type type;
	@NotEmpty
	private final String name;
	@NotEmpty
	private final String description;
	@Lob
	private String image;
	
	  public static enum Type {
		    DRINK, DESSERT, SANDWICH
		  }
}