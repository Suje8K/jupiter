package in.sujeetk.jupiter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Repository;

@Repository
public interface RegClientRepository extends MongoRepository<RegisteredClient, String> {
}
