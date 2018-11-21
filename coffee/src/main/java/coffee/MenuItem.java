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
	@NotEmpty
	private final String name;
	@NotEmpty
	private final String description;
	private final byte[] image;
}
