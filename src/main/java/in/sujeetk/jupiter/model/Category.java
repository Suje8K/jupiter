package in.sujeetk.jupiter.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document("Category")
public class Category {
    @Id
    String mongoId;
    String name;
}
