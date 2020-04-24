package me.hoonick.springdataelasticsearchtest.config;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.EntityMapper;

@Slf4j
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

//    @Value("${elasticsearch.host}")
//    private String host;
//
//    @Value("${elasticsearch.tcp_port}")
//    private int tcpPort;
//    @Value("${elasticsearch.cluster_name}")
//    private String clusterName;


    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9300")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    @Override
    public ElasticsearchOperations elasticsearchOperations() {
        return super.elasticsearchOperations();
    }


    @Bean
    @Override
    public EntityMapper entityMapper() {
        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(),
                new DefaultConversionService());
        entityMapper.setConversions(elasticsearchCustomConversions());

        return entityMapper;
    }


}
