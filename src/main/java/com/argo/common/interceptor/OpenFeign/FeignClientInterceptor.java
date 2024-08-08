package com.argo.common.interceptor.OpenFeign;

import com.argo.api.argoCD.dto.ArgoCDAuth;
import com.argo.common.config.argoCD.FeignSSLConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class FeignClientInterceptor implements RequestInterceptor {

    @Value("${argoCD.url}")
    String argoCDUrl;

    @Value("${argoCD.username}")
    String argoCDUsername;

    @Value("${argoCD.password}")
    String argoCDPassword;

    private final RestTemplate restTemplate;

    public FeignClientInterceptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("================template URL :: " + requestTemplate.url());

        String requestUrl = requestTemplate.feignTarget().url();

        if (requestUrl.contains(argoCDUrl)) {
            log.info("================request URL :: " + requestUrl);
            ArgoCDAuth.ArgoCDAuthVo argoCDAuthVo = ArgoCDAuth.ArgoCDAuthVo.builder()
                    .username(argoCDUsername)
                    .password(argoCDPassword)
                    .build();

            ArgoCDAuth.ArgoCDAuthDto argoCDAuthDto = restTemplate.postForObject(argoCDUrl + "/api/v1/session", argoCDAuthVo, ArgoCDAuth.ArgoCDAuthDto.class);

            if (argoCDAuthDto != null)
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + argoCDAuthDto.getToken());

        }

    }
}
