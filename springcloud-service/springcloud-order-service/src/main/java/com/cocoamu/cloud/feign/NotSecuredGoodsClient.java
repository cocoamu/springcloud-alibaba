package com.cocoamu.cloud.feign;

import com.cocoamu.cloud.common.TableId;
import com.cocoamu.cloud.goods.DeductGoodsInventory;
import com.cocoamu.cloud.goods.SimpleGoodsInfo;
import com.cocoamu.cloud.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <h1>不安全的商品服务 Feign 接口</h1>
 * */
@FeignClient(
        contextId = "NotSecuredGoodsClient",
        value = "springcloud-goods-service"
)
public interface NotSecuredGoodsClient {

    /**
     * <h2>扣减商品库存</h2>
     * */
    @RequestMapping(
            value = "/springcloud-service/goods/deduct-goods-inventory",
            method = RequestMethod.PUT
    )
    CommonResponse<Boolean> deductGoodsInventory(
            @RequestBody List<DeductGoodsInventory> deductGoodsInventories);

    /**
     * <h2>根据 ids 查询简单的商品信息</h2>
     * */
    @RequestMapping(
            value = "/springcloud-service/goods/simple-goods-info",
            method = RequestMethod.POST
    )
    CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            @RequestBody TableId tableId);
}
