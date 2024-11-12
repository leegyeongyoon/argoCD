package com.argo.api.argoCD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArgoCDEventRequestBodyVo {
    private String resourceNamespace;
    private String resourceName;
    private String resourceUID;
    private String appNamespace;
}