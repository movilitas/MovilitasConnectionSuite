
package com.movilitas.connect.freshworks.freshcaller.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "call_id",
    "caller_id",
    "caller_number",
    "participant_id",
    "participant_type",
    "connection_type",
    "call_status",
    "duration",
    "duration_unit",
    "cost",
    "cost_unit",
    "enqueued_time",
    "created_time",
    "updated_time"
})
public class Participant {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("call_id")
    private Integer callId;
    @JsonProperty("caller_id")
    private Integer callerId;
    @JsonProperty("caller_number")
    private String callerNumber;
    @JsonProperty("participant_id")
    private Integer participantId;
    @JsonProperty("participant_type")
    private String participantType;
    @JsonProperty("connection_type")
    private Integer connectionType;
    @JsonProperty("call_status")
    private Integer callStatus;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("duration_unit")
    private String durationUnit;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("cost_unit")
    private String costUnit;
    @JsonProperty("enqueued_time")
    private Object enqueuedTime;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("updated_time")
    private String updatedTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("call_id")
    public Integer getCallId() {
        return callId;
    }

    @JsonProperty("call_id")
    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    @JsonProperty("caller_id")
    public Integer getCallerId() {
        return callerId;
    }

    @JsonProperty("caller_id")
    public void setCallerId(Integer callerId) {
        this.callerId = callerId;
    }

    @JsonProperty("caller_number")
    public String getCallerNumber() {
        return callerNumber;
    }

    @JsonProperty("caller_number")
    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    @JsonProperty("participant_id")
    public Integer getParticipantId() {
        return participantId;
    }

    @JsonProperty("participant_id")
    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    @JsonProperty("participant_type")
    public String getParticipantType() {
        return participantType;
    }

    @JsonProperty("participant_type")
    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    @JsonProperty("connection_type")
    public Integer getConnectionType() {
        return connectionType;
    }

    @JsonProperty("connection_type")
    public void setConnectionType(Integer connectionType) {
        this.connectionType = connectionType;
    }

    @JsonProperty("call_status")
    public Integer getCallStatus() {
        return callStatus;
    }

    @JsonProperty("call_status")
    public void setCallStatus(Integer callStatus) {
        this.callStatus = callStatus;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("duration_unit")
    public String getDurationUnit() {
        return durationUnit;
    }

    @JsonProperty("duration_unit")
    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    @JsonProperty("cost")
    public Double getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(Double cost) {
        this.cost = cost;
    }

    @JsonProperty("cost_unit")
    public String getCostUnit() {
        return costUnit;
    }

    @JsonProperty("cost_unit")
    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    @JsonProperty("enqueued_time")
    public Object getEnqueuedTime() {
        return enqueuedTime;
    }

    @JsonProperty("enqueued_time")
    public void setEnqueuedTime(Object enqueuedTime) {
        this.enqueuedTime = enqueuedTime;
    }

    @JsonProperty("created_time")
    public String getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @JsonProperty("updated_time")
    public String getUpdatedTime() {
        return updatedTime;
    }

    @JsonProperty("updated_time")
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
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
