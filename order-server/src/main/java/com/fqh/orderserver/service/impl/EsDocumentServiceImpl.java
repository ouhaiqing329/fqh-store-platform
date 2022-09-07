package com.fqh.orderserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqh.orderserver.document.UserDocument;
import com.fqh.orderserver.service.EsDocumentService;
import com.fqh.utils.response.UserInfo;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsDocumentServiceImpl implements EsDocumentService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public void save() {

        UserDocument entity = new UserDocument();
        entity.setId(1L);
        entity.setUsername("zs");
        entity.setAge(23);

        elasticsearchRestTemplate.save(entity);

    }

    @Override
    public UserInfo list(Long userId) {

        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("id", userId);
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQuery).build();
        elasticsearchRestTemplate.search(query,UserInfo.class);
        return null;
    }
}
