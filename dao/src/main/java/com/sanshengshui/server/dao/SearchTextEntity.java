package com.sanshengshui.server.dao;

import com.sanshengshui.server.dao.model.BaseEntity;

/**
 * @author james mu
 * @date 18-12-13 下午4:05
 */
public interface SearchTextEntity<D> extends BaseEntity<D> {

    String getSearchTextSource();

    void setSearchText(String searchText);
}
