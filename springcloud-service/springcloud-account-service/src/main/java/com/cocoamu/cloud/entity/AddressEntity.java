package com.cocoamu.cloud.entity;

import com.cocoamu.cloud.account.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <h1>用户地址表实体类定义</h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_address")
public class AddressEntity {

    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** 用户 id */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 用户名 */
    @Column(name = "username", nullable = false)
    private String username;

    /** 电话 */
    @Column(name = "phone", nullable = false)
    private String phone;

    /** 省 */
    @Column(name = "province", nullable = false)
    private String province;

    /** 市 */
    @Column(name = "city", nullable = false)
    private String city;

    /** 详细地址 */
    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    /** 创建时间 */
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 更新时间 */
    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * <h2>根据 userId + AddressItem 得到 UserAddress</h2>
     * */
    public static AddressEntity to(Long userId, AddressInfo.AddressItem addressItem) {

        AddressEntity userAddress = new AddressEntity();

        userAddress.setUserId(userId);
        userAddress.setUsername(addressItem.getUsername());
        userAddress.setPhone(addressItem.getPhone());
        userAddress.setProvince(addressItem.getProvince());
        userAddress.setCity(addressItem.getCity());
        userAddress.setAddressDetail(addressItem.getAddressDetail());

        return userAddress;
    }

    /**
     * <h2>将 UserAddress 对象转成 AddressInfo</h2>
     * */
    public AddressInfo.AddressItem toAddressItem() {

        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();

        addressItem.setId(this.id);
        addressItem.setUsername(this.username);
        addressItem.setPhone(this.phone);
        addressItem.setProvince(this.province);
        addressItem.setCity(this.city);
        addressItem.setAddressDetail(this.addressDetail);
        addressItem.setCreateTime(this.createTime);
        addressItem.setUpdateTime(this.updateTime);

        return addressItem;
    }
}
