package com.fqh.order.service.impl;

import com.fqh.order.document.UserDocument;
import com.fqh.order.service.EsDocumentService;
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
