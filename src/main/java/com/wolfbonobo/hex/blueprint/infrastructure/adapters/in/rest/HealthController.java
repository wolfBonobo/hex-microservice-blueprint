package com.wolfbonobo.hex.blueprint.infrastructure.adapters.in.rest;

import com.wolfbonobo.hex.blueprint.application.query.ports.GetHealthQueryPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final GetHealthQueryPort getHealthQueryPort;

    public HealthController(GetHealthQueryPort getHealthQueryPort) {
        this.getHealthQueryPort = getHealthQueryPort;
    }

    @GetMapping("/health")
    public String health() {
        return getHealthQueryPort.getHealthStatus();
    }
}
