package in.sujeetk.jupiter.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document("Companion")
public class Companion {
    @Id
    String companionId;
    String userId;
    String userName;
    String src;
    String name;
    String description;
    String instructions;
    String seed;
    Instant createdAt;
    Instant updatedAt;
    String categoryId;
    String modeldesc;
    String modelname;
    Long messageCount;
}
