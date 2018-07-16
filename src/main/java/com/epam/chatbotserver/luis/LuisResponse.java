package com.epam.chatbotserver.luis;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "query",
        "topScoringIntent",
        "intents",
        "entities"
})
public class LuisResponse {

    @JsonProperty("query")
    private String query;
    @JsonProperty("topScoringIntent")
    private TopScoringIntent topScoringIntent;
    @JsonProperty("intents")
    private List<Intent> intents = null;
    @JsonProperty("entities")
    private List<Entity> entities = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(String query) {
        this.query = query;
    }

    @JsonProperty("topScoringIntent")
    public TopScoringIntent getTopScoringIntent() {
        return topScoringIntent;
    }

    @JsonProperty("topScoringIntent")
    public void setTopScoringIntent(TopScoringIntent topScoringIntent) {
        this.topScoringIntent = topScoringIntent;
    }

    @JsonProperty("intents")
    public List<Intent> getIntents() {
        return intents;
    }

    @JsonProperty("intents")
    public void setIntents(List<Intent> intents) {
        this.intents = intents;
    }

    @JsonProperty("entities")
    public List<Entity> getEntities() {
        return entities;
    }

    @JsonProperty("entities")
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}