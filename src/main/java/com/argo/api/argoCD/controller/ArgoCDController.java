package com.argo.api.argoCD.controller;

import com.argo.api.argoCD.service.ArgoCDService;
import com.argo.common.model.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/argocd")
public class ArgoCDController {

    private final ArgoCDService argoCDService;

    @GetMapping("/repository/list")
    public ResponseDto<String> getRepositoryList(){
        return ResponseDto.<String>builder()
                .data(argoCDService.getReposotiryList())
                .build();
    }
}
