package com.hthk.calypso.cas.service.impl;

import com.hthk.calypso.cas.service.CASService;
import com.hthk.calypsox.model.trade.TradeInfo;
import com.hthk.calypsox.model.trade.criteria.CriteriaGetTrade;
import com.hthk.fintech.model.web.http.RequestDateTime;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hthk.fintech.model.data.datacenter.query.EntityTypeEnum.TRADE;
import static com.hthk.fintech.model.web.http.ActionTypeEnum.GET;

/**
 * @Author: Rock CHEN
 * @Date: 2024/1/2 17:25
 */
@com.hthk.fintech.model.common.Service(action = GET, type = TRADE)
@Service
public class GetTradeServiceImpl implements CASService<CriteriaGetTrade, List<TradeInfo>> {

    @Override
    public List<TradeInfo> process(RequestDateTime reqDateTime, CriteriaGetTrade criteria) {
        return null;
    }

}
