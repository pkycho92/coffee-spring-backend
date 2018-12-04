package coffee;

import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OrderBy
	private Integer position;
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@Lob
	private String image;
}
