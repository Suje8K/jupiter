package in.sujeetk.jupiter.service;

import in.sujeetk.jupiter.model.Category;
import in.sujeetk.jupiter.model.Companion;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CompanionService {
    Mono<List<Companion>> fetchCompanion(String categoryId, String name);
    Mono<List<Category>> fetchCategory();
}
