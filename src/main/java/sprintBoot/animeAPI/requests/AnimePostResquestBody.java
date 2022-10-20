package sprintBoot.animeAPI.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnimePostResquestBody {

    @NotEmpty(message = "anime name cannot be null or empty")
    private String name;    
}
