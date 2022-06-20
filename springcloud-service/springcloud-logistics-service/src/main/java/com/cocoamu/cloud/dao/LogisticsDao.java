package com.cocoamu.cloud.dao;

import com.cocoamu.cloud.entity.LogisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>EcommerceLogistics Dao 接口定义</h1>
 * */
public interface LogisticsDao extends JpaRepository<LogisticsEntity, Long> {
}
