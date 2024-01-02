package com.hthk.calypso.cas.dao;

import com.hthk.calypsox.model.trade.criteria.CriteriaGetTrade;

import java.util.List;

/**
 * @Author: Rock CHEN
 * @Date: 2024/1/2 19:28
 */
public interface TradeDAO {

    List<Object> get(CriteriaGetTrade criteria);

}
