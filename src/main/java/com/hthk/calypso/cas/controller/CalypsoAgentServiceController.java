package com.hthk.calypso.cas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hthk.calypso.cas.service.ServiceManager;
import com.hthk.fintech.controller.basic.AbstractController;
import com.hthk.fintech.exception.ServiceInternalException;
import com.hthk.fintech.exception.ServiceInvalidException;
import com.hthk.fintech.exception.ServiceNotSupportedException;
import com.hthk.fintech.model.web.http.HttpResponse;
import com.hthk.fintech.model.web.http.HttpServiceRequest;
import com.hthk.fintech.utils.HttpResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hthk.fintech.config.FintechStaticData.KW_HTTP_REQUEST;
import static com.hthk.fintech.config.FintechStaticData.LOG_WRAP;

/**
 * @Author: Rock CHEN
 * @Date: 2023/12/20 17:16
 */
@RestController
@RequestMapping("/")
public class CalypsoAgentServiceController extends AbstractController {

    private final static Logger logger = LoggerFactory.getLogger(CalypsoAgentServiceController.class);

    @Autowired
    private ServiceManager serviceManager;

    @PostMapping(value = "/services")
    public <P, C, R> HttpResponse<?> post(
            @RequestBody HttpServiceRequest<P, C> request
    ) throws JsonProcessingException, ServiceInvalidException, ServiceNotSupportedException, ServiceInternalException {

        logger.info(LOG_WRAP, KW_HTTP_REQUEST, getDefaultObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
        R responseData = serviceManager.process(request);
        return HttpResponseUtils.success(responseData);
    }

}
