package com.argo.api.argoCD.controller;

import com.argo.api.argoCD.dto.*;
import com.argo.api.argoCD.service.ArgoCDService;
import com.argo.common.model.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/argocd")
public class ArgoCDController {

    private final ArgoCDService argoCDService;

    @CrossOrigin("*")
    @GetMapping("/repository/list")
    public ResponseDto<String> getRepositoryList() {
        return ResponseDto.<String>builder()
                .data(argoCDService.getReposotiryList())
                .build();
    }

    @CrossOrigin("*")
    @GetMapping("/applications")
    public ResponseDto<List<ArgoCdApplicationVo>> getApplicationList() {
        return ResponseDto.<List<ArgoCdApplicationVo>>builder()
                .data(argoCDService.getApplicationList())
                .build();
    }

    @CrossOrigin("*")
    @GetMapping("/application/{serviceName}")
    public ResponseDto<ArgoCdApplicationResponseVo> getApplicationList(@PathVariable String serviceName) {
        return ResponseDto.<ArgoCdApplicationResponseVo>builder()
                .data(argoCDService.getApplication(serviceName))
                .build();
    }

    @CrossOrigin("*")
    @GetMapping("/resource-tree/{serviceName}")
    public ResponseDto<List<ArgoCDTreeVo>> getResourceTree(@PathVariable String serviceName) {
        return ResponseDto.<List<ArgoCDTreeVo>>builder()
                .data(argoCDService.getApplicationResourceTree(serviceName))
                .build();
    }

    @CrossOrigin("*")
    @GetMapping("/managed-resources/{serviceName}")
    public ResponseDto<ArgoCdManagedResourceVo> getManagedResource(@PathVariable String serviceName, @RequestParam String kind) {
        return ResponseDto.<ArgoCdManagedResourceVo>builder()
                .data(argoCDService.getApplicationManagedResource(serviceName, kind))
                .build();
    }
    @CrossOrigin("*")
    @GetMapping("/appDetails")
    public ResponseDto<ArgoCDAppDetailsResponseVo> getManagedResource1(@RequestParam ArgoCDAppDetailsRequestBodyVo argoCDAppDetailsRequestBodyVo) {
        return ResponseDto.<ArgoCDAppDetailsResponseVo>builder()
                .data(argoCDService.getAppdetails(argoCDAppDetailsRequestBodyVo))
                .build();
    }

}
