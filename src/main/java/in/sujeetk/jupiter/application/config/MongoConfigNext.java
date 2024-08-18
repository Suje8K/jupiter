package in.sujeetk.jupiter.application.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(
    basePackages = "in.sujeetk.jupiter.repository.next",
    reactiveMongoTemplateRef = "mongoTemplateNext"
)
public class MongoConfigNext {

    @Value("${mongodb.dbname-next}")
    private String dbNext;

    @Bean
    public ReactiveMongoDatabaseFactory mongoFactoryNext(MongoClient mongoClient) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, dbNext);
    }

    @Bean
    public MappingMongoConverter mongoConverterNext(@Qualifier("mongoFactoryNext") ReactiveMongoDatabaseFactory mongoFactory,
                                                    MongoMappingContext mongoMappingContext,
                                                    MongoCustomConversions conversions) {
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, mongoMappingContext);
        converter.setCustomConversions(conversions);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null)); // Removes _class field from MongoDB documents
        return converter;
    }

    @Bean
    public ReactiveMongoTemplate mongoTemplateNext(@Qualifier("mongoFactoryNext") ReactiveMongoDatabaseFactory mongoFactory) {
        return new ReactiveMongoTemplate(mongoFactory);
    }
}
