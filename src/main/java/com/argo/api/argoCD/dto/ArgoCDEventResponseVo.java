package com.argo.api.argoCD.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArgoCDEventResponseVo {
    private Metadata metadata;
    private List<EventItem> items;
}

@Data
@NoArgsConstructor
class Metadata {
    private String resourceVersion;
}

@Data
@NoArgsConstructor
class EventItem {
    private Metadata metadata;
    private InvolvedObject involvedObject;
    private String reason;
    private String message;
    private Source source;
    private String firstTimestamp;
    private String lastTimestamp;
    private int count;
    private String type;
    private String reportingComponent;
    private String reportingInstance;
}

@Data
@NoArgsConstructor
class InvolvedObject {
    private String kind;
    private String namespace;
    private String name;
    private String uid;
    private String apiVersion;
    private String resourceVersion;
}

@Data
@NoArgsConstructor
class Source {
    private String component;
}