package com.cocoamu.cloud.dao;

import com.cocoamu.cloud.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>Balance Dao 接口定义</h1>
 * */
public interface BalanceDao extends JpaRepository<BalanceEntity, Long> {

    /** 根据 userId 查询 Balance 对象 */
    BalanceEntity findByUserId(Long userId);
}
