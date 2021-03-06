package com.leonelm.mundodisney.controller;

import com.leonelm.mundodisney.model.Character;
import com.leonelm.mundodisney.service.CharacterService;
import com.leonelm.mundodisney.util.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping
    public List<CharacterDTO> getCharacters(
            @RequestParam(value="name",required = false) String name,
            @RequestParam(value="age",required = false) Integer age,
            @RequestParam(value="movieId",required = false) Long movieId
            ){
        if(name != null && name.length() > 0){
            return characterService.getCharactersByName(name);
        } else if (age != null) {
            return characterService.getCharactersByAge(age);
        } else if (movieId != null){
            return characterService.getCharactersByMovieId(movieId);
        }
        return characterService.getCharacters();
    }

    @GetMapping(value = "/{id}")
    public Character getCharacterDetail(@PathVariable Long id){
        return characterService.getCharacterDetail(id);
    }

    @PostMapping
    public void createCharacter(@RequestBody Character character) {
        characterService.createCharacter(character);
    }

    @PutMapping(path = "/{id}")
    public void updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        characterService.updateCharacter(id, character);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCharacter(@PathVariable Long id){
        characterService.deleteCharacter(id);
    }

    @PutMapping(path= "/setAsociatedMovie")
    public void setAsociatedMovie(@RequestParam(value = "characterId") Long id, @RequestParam(value = "movieId") Long movieId){
        characterService.setAsociatedMovie(id, movieId);
    }
}
