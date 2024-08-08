package com.argo.common.config.argoCD;

import feign.Client;
import feign.Feign;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class FeignSSLConfig {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        Client client;
        try {
            SSLContext context =
                    new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            client = new Client.Default(context.getSocketFactory(), new NoopHostnameVerifier());
        } catch (Exception e) {
            client = new Client.Default(null, null);
        }
        return Feign.builder().client(client);
    }



}