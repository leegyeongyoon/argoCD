package com.argo.api.argoCD.dto;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArgoCdApplicationVo {
    private String applicationName;
    private String projectName;
    private String labels;
    private String sync;
    private String health;
    private String repositoryUrl;
    private String targetRevision;
    private String path;
    private String destination;
    private String namespace;
    private String createdAt;
    private String lastSyncAt;
    private String images;
    private String cluster;

    public static ArgoCdApplicationVo setApplicationVo(ArgoCdApplicationResponseVo argoCdApplicationResponseVo) {
        ArgoCdApplicationResponseVo.Metadata metadata = argoCdApplicationResponseVo.getMetadata();
        ArgoCdApplicationResponseVo.Spec spec = argoCdApplicationResponseVo.getSpec();
        ArgoCdApplicationResponseVo.Status status = argoCdApplicationResponseVo.getStatus();

        return ArgoCdApplicationVo.builder()
                .applicationName(metadata.getName())
                .projectName(spec.getProject())
                .sync(status.getSync().getStatus())
                .health(status.getHealth().getStatus())
                .namespace(metadata.getNamespace())
                .repositoryUrl(spec.getSource().getRepoURL())
                .path(spec.getSource().getPath())
                .targetRevision(spec.getSource().getTargetRevision())
                .destination(Objects.nonNull(spec.getDestination().getServer()) ? spec.getDestination().getServer() : spec.getDestination().getName())
                .createdAt(metadata.getCreationTimestamp())
                .lastSyncAt(status.getReconciledAt())
                .cluster(spec.getDestination().getServer())
                .images(status.getSummary().getImages().get(0))
                .build();
    }

}