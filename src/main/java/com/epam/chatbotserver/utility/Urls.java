package com.epam.chatbotserver.utility;

/**
 * Created by Victor on 05.07.2018.
 * Enum to save important  urls for our app
 */
public enum Urls {
    /**
     * URl with is responsible for text analyser
     * see  https://eu.luis.ai/home for more details
     */
    RECOGNISION("https://westus.api.cognitive.microsoft.com/" +
            "luis/v2.0/apps/2b034e08-7ec0-42cc-8478-4f58693c11c4?" +
            "subscription-key=4a6d5d0627d7447380f5ce6f43c5c3ce&verbose=true&timezoneOffset=0");


    private String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
