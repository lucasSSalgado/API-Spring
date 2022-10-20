package sprintBoot.animeAPI.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprintBoot.animeAPI.endpoints.Anime;
import sprintBoot.animeAPI.requests.AnimePostResquestBody;
import sprintBoot.animeAPI.requests.AnimePutRequestBody;
import sprintBoot.animeAPI.services.AnimeService;

import java.util.List;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "animes")
public class AnimeControllerAula17 {

    private final AnimeService service;

    @GetMapping
    public ResponseEntity<Page<Anime>> listAll(Pageable pageable){
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostResquestBody animePostResquestBody){
        return new ResponseEntity<>(service.save(animePostResquestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody){
        service.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
