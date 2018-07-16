package com.epam.chatbotserver.models;


import com.epam.chatbotserver.utility.IntentTypes;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Victor on 05.07.2018.
 */
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reply;

    @Transient
    private Map<String, String> requestParams;

    @Enumerated
    private IntentTypes intentType;

    @Transient
    private boolean confirmed;

    public Reply() {
        requestParams = new HashMap<>();
        confirmed = false;
    }

    public Reply(String reply, IntentTypes intentType) {
        this.reply = reply;
        this.intentType = intentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public IntentTypes getIntentType() {
        return intentType;
    }

    public void setIntentType(IntentTypes intentType) {
        this.intentType = intentType;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }
}
