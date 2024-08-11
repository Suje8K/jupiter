package in.sujeetk.jupiter.repository;

import in.sujeetk.jupiter.model.Oauth2Authorization;
import in.sujeetk.jupiter.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Oauth2AuthorizationRepository extends MongoRepository<Oauth2Authorization, String> {

}
