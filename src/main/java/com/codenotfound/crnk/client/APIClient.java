package com.codenotfound.crnk.client;

import io.crnk.client.CrnkClient;
import io.crnk.client.http.HttpAdapterProvider;
import io.crnk.client.http.apache.HttpClientAdapter;
import io.crnk.client.http.apache.HttpClientAdapterProvider;
import io.crnk.core.queryspec.mapper.DefaultQuerySpecUrlMapper;

public class APIClient extends CrnkClient {

    public APIClient() {
        this(System.getProperty("API_URL", "http://localhost:9090/codenotfound/api"));
    }

    public APIClient(String host) {
        super(host);
        if (getUrlMapper() instanceof DefaultQuerySpecUrlMapper) {
            ((DefaultQuerySpecUrlMapper) getUrlMapper()).setAllowUnknownAttributes(true);
        }
        initialize();
    }

    @Override
    public void registerHttpAdapterProvider(HttpAdapterProvider httpAdapterProvider) {
        if (httpAdapterProvider instanceof HttpClientAdapterProvider) {
            super.registerHttpAdapterProvider(httpAdapterProvider);
        }
    }

    private void initialize() {
        HttpClientAdapter adapter = (HttpClientAdapter) getHttpAdapter();
    }
}
