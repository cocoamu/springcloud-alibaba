package com.cocoamu.cloud.dao;



import com.cocoamu.cloud.constant.BrandCategory;
import com.cocoamu.cloud.constant.GoodsCategory;
import com.cocoamu.cloud.entity.GoodsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * <h1>EcommerceGoods Dao 接口定义</h1>
 * */
public interface GoodsDao extends PagingAndSortingRepository<GoodsEntity, Long> {

    /**
     * <h2>根据查询条件查询商品表, 并限制返回结果</h2>
     * select * from t_ecommerce_goods where goods_category = ? and brand_category = ?
     * and goods_name = ? limit 1;
     * */
    Optional<GoodsEntity> findFirst1ByGoodsCategoryAndBrandCategoryAndGoodsName(
            GoodsCategory goodsCategory, BrandCategory brandCategory,
            String goodsName
    );
}
