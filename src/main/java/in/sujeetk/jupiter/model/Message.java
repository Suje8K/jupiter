package in.sujeetk.jupiter.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document("Message")
public class Message {
    @Id
    String mongoId;
    String companionId;
    String userId;
    String role;
    String content;
    Instant createdAt;
    Instant updatedAt;
}
