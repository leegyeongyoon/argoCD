package com.argo.common.config.argoCD;

import com.argo.api.argoCD.dto.ArgoCDTreeNodeResponseVo;
import com.argo.api.argoCD.dto.ArgoCdApplicationResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "argoCD-feign", url = "${argoCD.url}", configuration = {FeignConfig.class, FeignSSLConfig.class})
public interface ArgoCDFeignClient {

    /**
     * selectListRepo
     */
    @GetMapping(value = "/api/v1/repositories", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> selectListRepo();

    @GetMapping(value = "/api/v1/applications", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ArgoCdApplicationResponseVo selectListApplication();

    @GetMapping(value = "/api/v1/applications/{applicationName}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ArgoCdApplicationResponseVo selectApplication(@PathVariable String applicationName);

    @GetMapping(value = "/api/v1/applications/{applicationName}/resource-tree", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ArgoCDTreeNodeResponseVo selectApplicationResourceTree(@PathVariable String applicationName);

    @GetMapping(value = "/api/v1/applications/{applicationName}/managed-resources", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> selectApplicationManagedResource(@PathVariable String applicationName, @RequestParam String kind);
}
