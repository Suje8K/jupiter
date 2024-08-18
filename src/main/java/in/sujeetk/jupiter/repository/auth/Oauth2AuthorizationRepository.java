package in.sujeetk.jupiter.repository.auth;

import in.sujeetk.jupiter.model.Oauth2Authorization;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Oauth2AuthorizationRepository extends ReactiveMongoRepository<Oauth2Authorization, String> {

}
