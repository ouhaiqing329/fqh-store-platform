package com.fqh.storeserver.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fqh.storeserver.entity.Product;
import com.fqh.storeserver.mapper.ProductManagementMapper;
import com.fqh.storeserver.service.ProductManagementService;
import com.fqh.utils.handle.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    private ProductManagementMapper productManagementMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 新增一个商品
     *
     * @param productEntity 产品实体
     * @return boolean
     */
    @Override
    @SentinelResource(value = "/addOrder", fallback = "addOrderFallback", blockHandler = "exceptionHandler")
    @Transactional(rollbackFor = Exception.class)
    public int addOrder(Product productEntity) {

        //库存不足，查询
        Product product = productManagementMapper.selectById(productEntity.getId());
        if (product.getStock() == 0) {
            throw new ServiceException("商品库存不足");
        }
        //减库存
        product.setStock(product.getStock() - 1);
        int row = productManagementMapper.update(product, Wrappers.<Product>lambdaUpdate()
                .eq(Product::getVersion, product.getVersion()).set(Product::getStock, product.getStock() - 1));
        if (row < 1) {
            //抛出异常
            throw new ServiceException("商品库存不足");
        }
        //生成订单


        return 0;
    }

    /**
     * 回调函数，服务触发熔断降级异常后触发的逻辑
     * 返回值类型必须与原函数返回值类型一致；
     * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     *
     * @param productEntity 产品实体
     * @return int
     */
    public int addOrderFallback(Product productEntity) {
        log.info("商品存储不足，下单失败");
        return -1;
    }

    /**
     * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     * 熔断降级成功抛出的异常处理
     *
     * @param productEntity
     * @param ex
     * @return
     */
    public int exceptionHandler(Product productEntity, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return -2;
    }
}
