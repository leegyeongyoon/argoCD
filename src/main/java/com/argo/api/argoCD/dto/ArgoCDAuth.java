package com.argo.api.argoCD.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class ArgoCDAuth {


    @Builder
    @Getter
    public static class ArgoCDAuthVo {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ArgoCDAuthDto {
        public String token;
    }

    @Builder
    public static class ArgoCDTokenInVo {
        private String id;
        private String name;
        private String expiresIn;
    }
}
