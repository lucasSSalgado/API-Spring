package sprintBoot.animeAPI.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import sprintBoot.animeAPI.endpoints.Anime;
import sprintBoot.animeAPI.exceptions.BadRequestException;
import sprintBoot.animeAPI.mapper.AnimeMapper;
import sprintBoot.animeAPI.repositories.AnimeRepository;
import sprintBoot.animeAPI.requests.AnimePostResquestBody;
import sprintBoot.animeAPI.requests.AnimePutRequestBody;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AnimeService {
    
    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    public Anime save(AnimePostResquestBody animePostResquestBody) {
        return animeRepository.save(AnimeMapper.INSTACE.toAnime(animePostResquestBody));
        
    }        

    public void deleteById(long id) {
        animeRepository.deleteById(id);
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime idAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTACE.toAnime(animePutRequestBody);
        anime.setId(idAnime.getId());
        animeRepository.save(anime);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }
}
