package me.hoonick.springdataelasticsearchtest.config;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchConfigTest {

    @Autowired
    RestHighLevelClient highLevelClient;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Test
    public void test_client() throws Exception {
        IndexRequest request = new IndexRequest("spring-data", "elasticsearch", "1")
                .source(singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

        IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);

    }

    @Test
    public void elasticsearchOperations_test() {
//        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId("2")
//                .withObject(singletonMap("feature", "high-level-rest-client"))
//                .build();
//        String documentId = elasticsearchOperations.index(indexQuery);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("spring-data").withTypes("elasticsearch")
                .withQuery(boolQuery().filter(matchQuery("feature", "high-level-rest-client")))
                .build();

        elasticsearchOperations.query(searchQuery, SearchResponse::getHits);


//        assertEquals(documentId, "2");
    }


}