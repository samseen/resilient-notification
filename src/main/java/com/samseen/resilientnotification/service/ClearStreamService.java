package com.samseen.resilientnotification.service;

import com.samseen.resilientnotification.config.ClearStreamConfig;
import com.samseen.resilientnotification.gateway.SMSGateway;
import com.samseen.resilientnotification.model.SMSRequest;
import com.samseen.resilientnotification.model.SMSResponse;
import com.samseen.resilientnotification.model.Status;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ClearStreamService implements SMSGateway {

    private final ClearStreamConfig clearStreamConfig;
    private final JsonService jsonService;
    private final OkHttpClient client;

    public ClearStreamService(ClearStreamConfig clearStreamConfig, JsonService jsonService) {
        this.clearStreamConfig = clearStreamConfig;
        this.jsonService = jsonService;
        this.client = new OkHttpClient().newBuilder().build();
    }

    @Override
    public SMSResponse sendSMS(SMSRequest smsRequest) {
        String url = clearStreamConfig.getUrl() + "/api/v1/clearstream/send/sms";
        try (Response response = client.newCall(createRequest(url, smsRequest)).execute()) {
            if (response.isSuccessful()) {
                String json = response.body().toString();
                log.info("Raw Response: [{}]", json);
                return jsonService.fromJson(json, SMSResponse.class);
            }
            return SMSResponse.of(Status.FAILURE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Request createRequest(String url, SMSRequest smsRequest) {
        String json = jsonService.toJson(smsRequest);
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();
    }
}
