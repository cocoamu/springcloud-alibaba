package com.cocoamu.cloud.feign;

import com.cocoamu.cloud.common.TableId;
import com.cocoamu.cloud.feign.hystrix.GoodsClientHystrix;
import com.cocoamu.cloud.goods.SimpleGoodsInfo;
import com.cocoamu.cloud.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <h1>安全的商品服务 Feign 接口</h1>
 * */
@FeignClient(
        contextId = "SecuredGoodsClient",
        value = "springcloud-goods-service",
        fallback = GoodsClientHystrix.class
)
public interface SecuredGoodsClient {

    /**
     * <h2>根据 ids 查询简单的商品信息</h2>
     * */
    @RequestMapping(
            value = "/springcloud-goods-service/goods/simple-goods-info",
            method = RequestMethod.POST
    )
    CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            @RequestBody TableId tableId);
}
