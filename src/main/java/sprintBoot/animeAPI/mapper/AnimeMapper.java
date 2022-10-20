package sprintBoot.animeAPI.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import sprintBoot.animeAPI.endpoints.Anime;
import sprintBoot.animeAPI.requests.AnimePostResquestBody;
import sprintBoot.animeAPI.requests.AnimePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTACE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostResquestBody AnimePostRequestBody); 
    
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
    
}
