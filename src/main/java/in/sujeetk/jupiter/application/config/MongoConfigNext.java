package in.sujeetk.jupiter.application.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

    @Value("${mongodb.uri}")
    private String mongoUri;

    @Value("${mongodb.dbname-next}")
    private String dbNext;

    @Value("${mongodb.dbname-auth}")
    private String dbAuth;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

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

    @Primary
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory mongoFactory) {
        return new ReactiveMongoTemplate(mongoFactory);
    }

    @Primary
    @Bean
    public ReactiveMongoDatabaseFactory mongoFactoryAuth(MongoClient mongoClient) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, dbAuth);
    }

    @Primary
    @Bean
    public MappingMongoConverter mongoConverter(ReactiveMongoDatabaseFactory mongoFactory,
                                                MongoMappingContext mongoMappingContext,
                                                MongoCustomConversions conversions) {
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, mongoMappingContext);
        converter.setCustomConversions(conversions);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null)); // Removes _class field from MongoDB documents
        return converter;
    }
}
