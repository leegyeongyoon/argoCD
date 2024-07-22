package com.argo.api.argoCD.controller;

import com.argo.api.argoCD.dto.ArgoCDTreeVo;
import com.argo.api.argoCD.service.ArgoCDService;
import com.argo.common.model.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/argocd")
public class ArgoCDController {

    private final ArgoCDService argoCDService;

    @CrossOrigin("*")
    @GetMapping("/repository/list")
    public ResponseDto<String> getRepositoryList(){
        return ResponseDto.<String>builder()
                .data(argoCDService.getReposotiryList())
                .build();
    }
    @CrossOrigin("*")
    @GetMapping("/resource-tree/{serviceName}")
    public ResponseDto<List<ArgoCDTreeVo>> getResourceTree(@PathVariable String serviceName){
        return ResponseDto.<List<ArgoCDTreeVo>>builder()
                .data(argoCDService.getApplicationResourceTree(serviceName))
                .build();
    }
}
