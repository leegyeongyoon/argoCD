package com.argo.api.argoCD.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArgoCdManagedResourceVo {
    private List<ArgoCdManagedResource> items;

    @Getter
    @Setter
    private static class ArgoCdManagedResource {
        private String group;
        private String kind;
        private String namespace;
        private String targetState;
        private String livestate;
        private String normalizedLiveState;
        private String predictedLiveState;
        private String resourceVersion;
    }

}