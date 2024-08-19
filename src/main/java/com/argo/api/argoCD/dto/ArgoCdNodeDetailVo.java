package com.argo.api.argoCD.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArgoCdNodeDetailVo {
    private ArgoCdApplicationVo argoCdApplicationVo;
    private ArgoCdManagedResourceVo argoCdManagedResourceVo;

    public static ArgoCdNodeDetailVo setNodeDetailVo(ArgoCdApplicationVo argoCdApplicationVo, ArgoCdManagedResourceVo argoCdManagedResourceVo) {
        return ArgoCdNodeDetailVo.builder()
                .argoCdApplicationVo(argoCdApplicationVo)
                .argoCdManagedResourceVo(argoCdManagedResourceVo)
                .build();
    }
}