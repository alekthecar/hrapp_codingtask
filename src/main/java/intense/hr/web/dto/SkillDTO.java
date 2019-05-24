package intense.hr.web.dto;

import org.hibernate.validator.constraints.NotBlank;

public class SkillDTO {

	private Integer id;

	@NotBlank
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
