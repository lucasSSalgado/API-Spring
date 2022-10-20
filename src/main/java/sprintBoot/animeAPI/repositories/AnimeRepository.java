package sprintBoot.animeAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sprintBoot.animeAPI.endpoints.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    public List<Anime> findByName(String name);
}
