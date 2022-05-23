
package com.movilitas.connect.freshworks.freshdesk.json;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customer",
    "cf_demo_system",
    "cf_productive_cloud",
    "cf_technology"
})
@Generated("jsonschema2pojo")
public class CustomFields {

    @JsonProperty("customer")
    private String customer;
    @JsonProperty("cf_demo_system")
    private Boolean cfDemoSystem;
    @JsonProperty("cf_productive_cloud")
    private Boolean cfProductiveCloud;
    @JsonProperty("cf_technology")
    private String cfTechnology;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customer")
    public String getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @JsonProperty("cf_demo_system")
    public Boolean getCfDemoSystem() {
        return cfDemoSystem;
    }

    @JsonProperty("cf_demo_system")
    public void setCfDemoSystem(Boolean cfDemoSystem) {
        this.cfDemoSystem = cfDemoSystem;
    }

    @JsonProperty("cf_productive_cloud")
    public Boolean getCfProductiveCloud() {
        return cfProductiveCloud;
    }

    @JsonProperty("cf_productive_cloud")
    public void setCfProductiveCloud(Boolean cfProductiveCloud) {
        this.cfProductiveCloud = cfProductiveCloud;
    }

    @JsonProperty("cf_technology")
    public String getCfTechnology() {
        return cfTechnology;
    }

    @JsonProperty("cf_technology")
    public void setCfTechnology(String cfTechnology) {
        this.cfTechnology = cfTechnology;
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
