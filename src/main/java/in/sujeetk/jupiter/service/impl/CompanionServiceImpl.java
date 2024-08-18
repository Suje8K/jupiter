package in.sujeetk.jupiter.service.impl;

import in.sujeetk.jupiter.model.Companion;
import in.sujeetk.jupiter.service.CompanionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CompanionServiceImpl implements CompanionService {

    @Autowired
    @Qualifier("mongoTemplateNext")
    private ReactiveMongoTemplate mongoTemplateNext;

    @Override
    public Mono<List<Companion>> fetchCompanion(String categoryId, String name) {
        Query query = new Query();
        if (categoryId != null && !categoryId.isEmpty()) {
            query.addCriteria(Criteria.where("categoryId").is(categoryId));
        }
        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(name, "i")); // Case insensitive search
        }
        query.with(Sort.by(Sort.Order.desc("createdAt"))); // Sorting by createdAt in descending order

        return mongoTemplateNext.find(query, Companion.class).collectList();
    }
}
