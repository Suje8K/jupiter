package in.sujeetk.jupiter.repository.auth;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Repository;

@Repository
public interface RegClientRepository extends ReactiveMongoRepository<RegisteredClient, String> {
}
