package com.cocoamu.cloud.service.impl;

import com.alibaba.fastjson.JSON;

import com.cocoamu.cloud.account.AddressInfo;
import com.cocoamu.cloud.common.TableId;
import com.cocoamu.cloud.dao.AddressDao;
import com.cocoamu.cloud.entity.AddressEntity;
import com.cocoamu.cloud.filter.AccessContext;
import com.cocoamu.cloud.service.IAddressService;
import com.cocoamu.cloud.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>用户地址相关服务接口实现</h1>
 * */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl implements IAddressService {

    private final AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    /**
     * <h2>存储多个地址信息</h2>
     * */
    @Override
    public TableId createAddressInfo(AddressInfo addressInfo) {

        // 不能直接从参数中获取用户的 id 信息
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 将传递的参数转换成实体对象
        List<AddressEntity> ecommerceAddresses = addressInfo.getAddressItems().stream()
                .map(a -> AddressEntity.to(loginUserInfo.getId(), a))
                .collect(Collectors.toList());

        // 保存到数据表并把返回记录的 id 给调用方
        List<AddressEntity> savedRecords = addressDao.saveAll(ecommerceAddresses);
        List<Long> ids = savedRecords.stream()
                .map(AddressEntity::getId).collect(Collectors.toList());
        log.info("create address info: [{}], [{}]", loginUserInfo.getId(),
                JSON.toJSONString(ids));

        return new TableId(
                ids.stream().map(TableId.Id::new).collect(Collectors.toList())
        );
    }

    @Override
    public AddressInfo getCurrentAddressInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 根据 userId 查询到用户的地址信息, 再实现转换
        List<AddressEntity> ecommerceAddresses = addressDao.findAllByUserId(
                loginUserInfo.getId()
        );
        List<AddressInfo.AddressItem> addressItems = ecommerceAddresses.stream()
                .map(AddressEntity::toAddressItem)
                .collect(Collectors.toList());

        return new AddressInfo(loginUserInfo.getId(), addressItems);
    }

    @Override
    public AddressInfo getAddressInfoById(Long id) {

        AddressEntity ecommerceAddress = addressDao.findById(id).orElse(null);
        if (null == ecommerceAddress) {
            throw new RuntimeException("address is not exist");
        }

        return new AddressInfo(
                ecommerceAddress.getUserId(),
                Collections.singletonList(ecommerceAddress.toAddressItem())
        );
    }

    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {

        List<Long> ids = tableId.getIds().stream()
                .map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get address info by table id: [{}]", JSON.toJSONString(ids));

        List<AddressEntity> addressEntityList = addressDao.findAllById(ids);
        if (CollectionUtils.isEmpty(addressEntityList)) {
            return new AddressInfo(-1L, Collections.emptyList());
        }

        List<AddressInfo.AddressItem> addressItems = addressEntityList.stream()
                .map(AddressEntity::toAddressItem)
                .collect(Collectors.toList());

        return new AddressInfo(
                addressEntityList.get(0).getUserId(), addressItems
        );
    }
}
