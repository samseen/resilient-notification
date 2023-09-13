package com.samseen.resilientnotification.service;

import com.samseen.resilientnotification.config.Bitrix24Config;
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
public class Bitrix24Service implements SMSGateway {

    private final Bitrix24Config bitrix24Config;
    private final JsonService jsonService;
    private final OkHttpClient client;

    public Bitrix24Service(Bitrix24Config bitrix24Config, JsonService jsonService) {
        this.bitrix24Config = bitrix24Config;
        this.jsonService = jsonService;
        this.client = new OkHttpClient.Builder().build();
    }

    @Override
    public SMSResponse sendSMS(SMSRequest smsRequest) {
        String url = bitrix24Config.getUrl() +"/api/v1/bitrix24/send/sms";
        try(Response response = client.newCall(create(url, smsRequest)).execute()) {
            String json = response.body().string();
            log.info("Raw: [{}]", json);
            if (response.isSuccessful()) {
                return jsonService.fromJson(json, SMSResponse.class);
            }
            return SMSResponse.of(Status.FAILURE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Request create(String url, SMSRequest smsRequest) {
        String json = jsonService.toJson(smsRequest);
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();

    }

}
