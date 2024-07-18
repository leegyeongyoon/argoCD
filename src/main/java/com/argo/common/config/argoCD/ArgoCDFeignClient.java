package com.argo.common.config.argoCD;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "argoCD-feign", url = "${argoCD.url}", configuration = FeignConfig.class)
public interface ArgoCDFeignClient {

    /**
     * selectListRepo
     */
    @GetMapping("/api/v1/repositories")
    ResponseEntity<Map<String, Object>> selectListRepo();

    @GetMapping("/api/v1/applications")
    ResponseEntity<Map<String, Object>> selectListApplication();

    @GetMapping("/api/v1/applications/{applicationName}/resource-tree")
    ResponseEntity<Map<String, Object>> selectApplicationResourceTree(@PathVariable String applicationName);
}
