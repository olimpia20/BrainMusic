package music;

import music.model.Content;
import music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.ContentRepository;
import repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/courses/")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "Hello") String name) {
        return name.toUpperCase();
    }

    @GetMapping("/find/{nb_cours}/{part}")
    public ResponseEntity<Content> getContentByCoursPart(@PathVariable("nb_cours") int nb_cours, @PathVariable("part") int part) {
        Content content = contentRepository.findByCoursPart(nb_cours, part);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping("/find/{nb_cours}")
    public ResponseEntity<List<Content>> getContentByCours(@PathVariable("nb_cours") int nb_cours) {
        List<Content> cours = contentRepository.findByCours(nb_cours);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }
}
