
package com.movilitas.connect.freshworks.freshcaller.json;

import java.util.HashMap;
import java.util.List;
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
    "direction",
    "parent_call_id",
    "root_call_id",
    "phone_number_id",
    "phone_number",
    "assigned_agent_id",
    "assigned_team_id",
    "assigned_call_queue_id",
    "assigned_call_queue_name",
    "assigned_ivr_id",
    "bill_duration",
    "bill_duration_unit",
    "created_time",
    "updated_time",
    "call_notes",
    "recording",
    "recording_to_redact",
    "integrated_resources",
    "participants"
})
public class Call {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("parent_call_id")
    private Object parentCallId;
    @JsonProperty("root_call_id")
    private Object rootCallId;
    @JsonProperty("phone_number_id")
    private Integer phoneNumberId;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("assigned_agent_id")
    private Object assignedAgentId;
    @JsonProperty("assigned_team_id")
    private String assignedTeamId;
    @JsonProperty("assigned_call_queue_id")
    private Integer assignedCallQueueId;
    @JsonProperty("assigned_call_queue_name")
    private String assignedCallQueueName;
    @JsonProperty("assigned_ivr_id")
    private Object assignedIvrId;
    @JsonProperty("bill_duration")
    private Double billDuration;
    @JsonProperty("bill_duration_unit")
    private String billDurationUnit;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("updated_time")
    private String updatedTime;
    @JsonProperty("call_notes")
    private Object callNotes;
    @JsonProperty("recording")
    private Object recording;
    @JsonProperty("recording_to_redact")
    private Object recordingToRedact;
    @JsonProperty("integrated_resources")
    private List<IntegratedResource> integratedResources = null;
    @JsonProperty("participants")
    private List<Participant> participants = null;
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

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("parent_call_id")
    public Object getParentCallId() {
        return parentCallId;
    }

    @JsonProperty("parent_call_id")
    public void setParentCallId(Object parentCallId) {
        this.parentCallId = parentCallId;
    }

    @JsonProperty("root_call_id")
    public Object getRootCallId() {
        return rootCallId;
    }

    @JsonProperty("root_call_id")
    public void setRootCallId(Object rootCallId) {
        this.rootCallId = rootCallId;
    }

    @JsonProperty("phone_number_id")
    public Integer getPhoneNumberId() {
        return phoneNumberId;
    }

    @JsonProperty("phone_number_id")
    public void setPhoneNumberId(Integer phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("assigned_agent_id")
    public Object getAssignedAgentId() {
        return assignedAgentId;
    }

    @JsonProperty("assigned_agent_id")
    public void setAssignedAgentId(Object assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    @JsonProperty("assigned_team_id")
    public String getAssignedTeamId() {
        return assignedTeamId;
    }

    @JsonProperty("assigned_team_id")
    public void setAssignedTeamId(String assignedTeamId) {
        this.assignedTeamId = assignedTeamId;
    }

    @JsonProperty("assigned_call_queue_id")
    public Integer getAssignedCallQueueId() {
        return assignedCallQueueId;
    }

    @JsonProperty("assigned_call_queue_id")
    public void setAssignedCallQueueId(Integer assignedCallQueueId) {
        this.assignedCallQueueId = assignedCallQueueId;
    }

    @JsonProperty("assigned_call_queue_name")
    public String getAssignedCallQueueName() {
        return assignedCallQueueName;
    }

    @JsonProperty("assigned_call_queue_name")
    public void setAssignedCallQueueName(String assignedCallQueueName) {
        this.assignedCallQueueName = assignedCallQueueName;
    }

    @JsonProperty("assigned_ivr_id")
    public Object getAssignedIvrId() {
        return assignedIvrId;
    }

    @JsonProperty("assigned_ivr_id")
    public void setAssignedIvrId(Object assignedIvrId) {
        this.assignedIvrId = assignedIvrId;
    }

    @JsonProperty("bill_duration")
    public Double getBillDuration() {
        return billDuration;
    }

    @JsonProperty("bill_duration")
    public void setBillDuration(Double billDuration) {
        this.billDuration = billDuration;
    }

    @JsonProperty("bill_duration_unit")
    public String getBillDurationUnit() {
        return billDurationUnit;
    }

    @JsonProperty("bill_duration_unit")
    public void setBillDurationUnit(String billDurationUnit) {
        this.billDurationUnit = billDurationUnit;
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

    @JsonProperty("call_notes")
    public Object getCallNotes() {
        return callNotes;
    }

    @JsonProperty("call_notes")
    public void setCallNotes(Object callNotes) {
        this.callNotes = callNotes;
    }

    @JsonProperty("recording")
    public Object getRecording() {
        return recording;
    }

    @JsonProperty("recording")
    public void setRecording(Object recording) {
        this.recording = recording;
    }

    @JsonProperty("recording_to_redact")
    public Object getRecordingToRedact() {
        return recordingToRedact;
    }

    @JsonProperty("recording_to_redact")
    public void setRecordingToRedact(Object recordingToRedact) {
        this.recordingToRedact = recordingToRedact;
    }

    @JsonProperty("integrated_resources")
    public List<IntegratedResource> getIntegratedResources() {
        return integratedResources;
    }

    @JsonProperty("integrated_resources")
    public void setIntegratedResources(List<IntegratedResource> integratedResources) {
        this.integratedResources = integratedResources;
    }

    @JsonProperty("participants")
    public List<Participant> getParticipants() {
        return participants;
    }

    @JsonProperty("participants")
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
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
