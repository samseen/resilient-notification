package com.samseen.resilientnotification.gateway;

import com.samseen.resilientnotification.model.SMSRequest;
import com.samseen.resilientnotification.model.SMSResponse;

public interface SMSGateway {
    SMSResponse sendSMS(SMSRequest smsRequest);
}
