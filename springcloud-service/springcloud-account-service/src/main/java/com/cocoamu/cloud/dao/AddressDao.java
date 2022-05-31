package com.cocoamu.cloud.dao;

import com.cocoamu.cloud.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>Address Dao 接口定义</h1>
 * */
public interface AddressDao extends JpaRepository<AddressEntity, Long> {

    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     * */
    List<AddressEntity> findAllByUserId(Long userId);
}
