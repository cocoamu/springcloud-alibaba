package com.cocoamu.cloud.dao;


import com.cocoamu.cloud.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <h1>EcommerceOrder Dao 接口定义</h1>
 * */
public interface OrderDao extends PagingAndSortingRepository<OrderEntity, Long> {

    /**
     * <h2>根据 userId 查询分页订单</h2>
     * select * from tSdz OrderEntity_order where user_id = ?
     *  order by ... desc/asc limit x offset y
     * */
    Page<OrderEntity> findAllByUserId(Long userId, Pageable pageable);
}
