package in.sujeetk.jupiter.repository.next;

import in.sujeetk.jupiter.model.Companion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanionRepository extends ReactiveMongoRepository<Companion, String> {

}
