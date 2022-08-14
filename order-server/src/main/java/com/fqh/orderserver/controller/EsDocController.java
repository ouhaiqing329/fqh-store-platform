package com.fqh.orderserver.controller;

import com.fqh.orderserver.service.EsDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * es文档相关
 *
 * @author fqh
 * @date 2022/08/13
 */
@RestController
@RequestMapping("/order/elastic/doc")
@Slf4j
public class EsDocController {

    @Autowired
    private EsDocumentService esDocumentService;

    @GetMapping("/add")
    public void test(){
        esDocumentService.save();
    }

}
