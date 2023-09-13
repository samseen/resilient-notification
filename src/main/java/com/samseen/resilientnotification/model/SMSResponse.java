package com.samseen.resilientnotification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SMSResponse {
    private String message;
    private Status status;

    public static SMSResponse of(Status status) {
        return new SMSResponse("Failed", status);
    }
}
