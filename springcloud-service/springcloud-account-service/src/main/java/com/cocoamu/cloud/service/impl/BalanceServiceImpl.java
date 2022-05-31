package com.cocoamu.cloud.service.impl;

import com.cocoamu.cloud.account.BalanceInfo;
import com.cocoamu.cloud.dao.BalanceDao;
import com.cocoamu.cloud.entity.BalanceEntity;
import com.cocoamu.cloud.filter.AccessContext;
import com.cocoamu.cloud.service.IBalanceService;
import com.cocoamu.cloud.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>用于余额相关服务接口实现</h1>
 * */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BalanceServiceImpl implements IBalanceService {

    private final BalanceDao balanceDao;

    public BalanceServiceImpl(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    @Override
    public BalanceInfo getCurrentUserBalanceInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        BalanceInfo balanceInfo = new BalanceInfo(
                loginUserInfo.getId(), 0L
        );

        BalanceEntity ecommerceBalance =
                balanceDao.findByUserId(loginUserInfo.getId());
        if (null != ecommerceBalance) {
            balanceInfo.setBalance(ecommerceBalance.getBalance());
        } else {
            // 如果还没有用户余额记录, 这里创建出来，余额设定为0即可
            BalanceEntity newBalance = new BalanceEntity();
            newBalance.setUserId(loginUserInfo.getId());
            newBalance.setBalance(0L);
            log.info("init user balance record: [{}]",
                    balanceDao.save(newBalance).getId());
        }

        return balanceInfo;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 扣减用户余额的一个基本原则: 扣减额 <= 当前用户余额
        BalanceEntity balanceEntity =
                balanceDao.findByUserId(loginUserInfo.getId());
        if (null == balanceEntity
                || balanceEntity.getBalance() - balanceInfo.getBalance() < 0
        ) {
            throw new RuntimeException("user balance is not enough!");
        }

        Long sourceBalance = balanceEntity.getBalance();
        balanceEntity.setBalance(balanceEntity.getBalance() - balanceInfo.getBalance());
        log.info("deduct balance: [{}], [{}], [{}]",
                balanceDao.save(balanceEntity).getId(), sourceBalance,
                balanceInfo.getBalance());

        return new BalanceInfo(
                balanceEntity.getUserId(),
                balanceEntity.getBalance()
        );
    }
}
