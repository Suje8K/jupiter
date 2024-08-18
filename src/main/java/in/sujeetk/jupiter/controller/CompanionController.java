package in.sujeetk.jupiter.controller;

import in.sujeetk.jupiter.dtos.ResponseDTO;
import in.sujeetk.jupiter.model.Category;
import in.sujeetk.jupiter.model.Companion;
import in.sujeetk.jupiter.service.CompanionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/companion")
public class CompanionController {

    @Autowired
    CompanionService companionService;

    @GetMapping()
    public Mono<ResponseDTO<List<Companion>>> getCompanion(@RequestParam String id, @RequestParam String name) {
        return companionService.fetchCompanion(id, name).map(resources -> new ResponseDTO<>(HttpStatus.OK.value(), resources, null));
    }

    @GetMapping("/categories")
    public Mono<ResponseDTO<List<Category>>> getCategory() {
        return companionService.fetchCategory().map(resources -> new ResponseDTO<>(HttpStatus.OK.value(), resources, null));
    }
}
