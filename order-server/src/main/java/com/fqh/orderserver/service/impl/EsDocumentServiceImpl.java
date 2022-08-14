package com.fqh.orderserver.service.impl;

import com.fqh.orderserver.document.UserDocument;
import com.fqh.orderserver.service.EsDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

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
}
