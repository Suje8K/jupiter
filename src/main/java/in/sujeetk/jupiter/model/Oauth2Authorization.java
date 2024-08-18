package in.sujeetk.jupiter.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("oauth2Authorization")
public class Oauth2Authorization {

}

