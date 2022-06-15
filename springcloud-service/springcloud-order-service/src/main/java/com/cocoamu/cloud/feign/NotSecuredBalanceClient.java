package com.cocoamu.cloud.feign;

import com.cocoamu.cloud.account.BalanceInfo;
import com.cocoamu.cloud.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <h1>用户账户服务 Feign 接口</h1>
 * */
@FeignClient(
        contextId = "NotSecuredBalanceClient",
        value = "springcloud-account-service"
)
public interface NotSecuredBalanceClient {

    @RequestMapping(
            value = "/springcloud-service/balance/deduct-balance",
            method = RequestMethod.PUT
    )
    CommonResponse<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo);
}
