package com.argo.common.config.argoCD;

import com.argo.api.argoCD.dto.ArgoCDTreeNodeResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "argoCD-feign", url = "${argoCD.url}", configuration = FeignConfig.class)
public interface ArgoCDFeignClient {

    /**
     * selectListRepo
     */
    @GetMapping(value = "/api/v1/repositories", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> selectListRepo();

    @GetMapping(value = "/api/v1/applications", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> selectListApplication();

    @GetMapping(value = "/api/v1/applications/{applicationName}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> selectApplication(@PathVariable String applicationName);

    @GetMapping(value = "/api/v1/applications/{applicationName}/resource-tree", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> selectApplicationResourceTree(@PathVariable String applicationName);
}
