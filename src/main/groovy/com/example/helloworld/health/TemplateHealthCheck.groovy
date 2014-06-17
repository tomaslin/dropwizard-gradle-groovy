package com.example.helloworld.health

import com.codahale.metrics.health.HealthCheck
import static com.codahale.metrics.health.HealthCheck.Result
import com.example.helloworld.core.Template
import com.google.common.base.Optional

public class TemplateHealthCheck extends HealthCheck {
    final Template template

    public TemplateHealthCheck(Template template) {
        this.template = template
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("woo"))
        template.render(Optional.<String>absent())
        return Result.healthy()
    }
}
