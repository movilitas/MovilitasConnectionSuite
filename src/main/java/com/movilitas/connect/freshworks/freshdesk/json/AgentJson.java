
package com.movilitas.connect.freshworks.freshdesk.json;

import java.util.HashMap;
import java.util.List;
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
    "available",
    "occasional",
    "id",
    "ticket_scope",
    "signature",
    "group_ids",
    "role_ids",
    "skill_ids",
    "contact",
    "created_at",
    "updated_at",
    "type"
})
@Generated("jsonschema2pojo")
public class AgentJson {

    @JsonProperty("available")
    private Boolean available;
    @JsonProperty("occasional")
    private Boolean occasional;
    @JsonProperty("id")
    private String id;
    @JsonProperty("ticket_scope")
    private String ticketScope;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("group_ids")
    private List<String> groupIds = null;
    @JsonProperty("role_ids")
    private List<String> roleIds = null;
    @JsonProperty("skill_ids")
    private List<Object> skillIds = null;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("available")
    public Boolean getAvailable() {
        return available;
    }

    @JsonProperty("available")
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @JsonProperty("occasional")
    public Boolean getOccasional() {
        return occasional;
    }

    @JsonProperty("occasional")
    public void setOccasional(Boolean occasional) {
        this.occasional = occasional;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("ticket_scope")
    public String getTicketScope() {
        return ticketScope;
    }

    @JsonProperty("ticket_scope")
    public void setTicketScope(String ticketScope) {
        this.ticketScope = ticketScope;
    }

    @JsonProperty("signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty("group_ids")
    public List<String> getGroupIds() {
        return groupIds;
    }

    @JsonProperty("group_ids")
    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    @JsonProperty("role_ids")
    public List<String> getRoleIds() {
        return roleIds;
    }

    @JsonProperty("role_ids")
    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @JsonProperty("skill_ids")
    public List<Object> getSkillIds() {
        return skillIds;
    }

    @JsonProperty("skill_ids")
    public void setSkillIds(List<Object> skillIds) {
        this.skillIds = skillIds;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
