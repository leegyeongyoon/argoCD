package com.argo.common.config.argoCD;

import com.argo.api.argoCD.dto.*;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
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
    ArgoCdManagedResourceVo selectApplicationManagedResource(@PathVariable String applicationName, @RequestParam String kind);

    @PostMapping(value = "/api/v1/repositories/{repoURL:.+}/appdetails", produces = MediaType.APPLICATION_JSON_VALUE)
    ArgoCDAppDetailsResponseVo selectApplicationRepository(@PathVariable String repoURL, @RequestBody ArgoCDAppDetailsRequestBodyVo argoCDAppDetailsRequestBodyVo);

    @GetMapping(value = "/api/v1/applications/{applicationName}/events", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ArgoCDEventResponseVo selectEventList(@PathVariable String applicationName, @RequestParam ArgoCDEventRequestBodyVo argoCDEventRequestBodyVo);
}
