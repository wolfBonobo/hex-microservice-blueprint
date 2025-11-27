package com.wolfbonobo.hex.blueprint.application.query.service;

import com.wolfbonobo.hex.blueprint.application.query.ports.GetHealthQueryPort;
import org.springframework.stereotype.Service;

@Service
public class GetHealthQueryService implements GetHealthQueryPort {

    @Override
    public String getHealthStatus() {
        return "OK";
    }
}
