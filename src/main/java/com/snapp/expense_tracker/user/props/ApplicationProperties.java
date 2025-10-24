package com.snapp.expense_tracker.user.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private Long accessTokenTtl;
    private Long refreshTokenTtl;
    private String secret;

    public Long getAccessTokenTtl() {
        return accessTokenTtl;
    }

    public void setAccessTokenTtl(Long accessTokenTtl) {
        this.accessTokenTtl = accessTokenTtl;
    }

    public Long getRefreshTokenTtl() {
        return refreshTokenTtl;
    }

    public void setRefreshTokenTtl(Long refreshTokenTtl) {
        this.refreshTokenTtl = refreshTokenTtl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
