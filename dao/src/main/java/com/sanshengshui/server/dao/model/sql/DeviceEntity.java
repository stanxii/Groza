package com.sanshengshui.server.dao.model.sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.sanshengshui.server.common.data.Device;
import com.sanshengshui.server.common.data.id.CustomerId;
import com.sanshengshui.server.common.data.id.DeviceId;
import com.sanshengshui.server.common.data.id.TenantId;
import com.sanshengshui.server.dao.BaseSqlEntity;
import com.sanshengshui.server.dao.SearchTextEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author james mu
 * @date 18-12-13 下午4:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class DeviceEntity extends BaseSqlEntity<Device> implements SearchTextEntity<Device> {

    private String tenantId;

    private String customerId;

    private String type;

    private String name;

    private String searchText;

    private JsonNode additionalInfo;

    public DeviceEntity() {
        super();
    }

    public DeviceEntity(Device device) {
        if (device.getId() != null) {
            this.setId(device.getId().getId());
        }
        if (device.getTenantId() != null) {
            this.tenantId = toString(device.getTenantId().getId());
        }
        if (device.getCustomerId() != null) {
            this.customerId = toString(device.getCustomerId().getId());
        }
        this.name = device.getName();
        this.type = device.getType();
        this.additionalInfo = device.getAdditionalInfo();
    }


    @Override
    public String getSearchTextSource() {
        return name;
    }

    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public Device toData() {
        Device device = new Device(new DeviceId(getId()));
        device.setCreatedTime(new Date().getTime());
        if (tenantId != null) {
            device.setTenantId(new TenantId(toUUID(tenantId)));
        }
        if (customerId != null) {
            device.setCustomerId(new CustomerId(toUUID(customerId)));
        }
        device.setName(name);
        device.setType(type);
        device.setAdditionalInfo(additionalInfo);
        return device;
    }
}
