package com.example.helloworld.health

import com.example.helloworld.core.Template
import com.google.common.base.Optional
import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result

public class TemplateHealthCheck extends HealthCheck {
    final Template template

    public TemplateHealthCheck(Template template) {
        super("template")
        this.template = template
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("woo"))
        template.render(Optional.<String>absent())
        return Result.healthy()
    }
}
