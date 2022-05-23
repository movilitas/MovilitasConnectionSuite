
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
    "cc_emails",
    "fwd_emails",
    "reply_cc_emails",
    "ticket_cc_emails",
    "fr_escalated",
    "spam",
    "email_config_id",
    "group_id",
    "priority",
    "requester_id",
    "responder_id",
    "source",
    "company_id",
    "status",
    "subject",
    "support_email",
    "to_emails",
    "product_id",
    "id",
    "type",
    "due_by",
    "fr_due_by",
    "is_escalated",
    "custom_fields",
    "created_at",
    "updated_at",
    "tags",
    "deleted",
    "nr_escalated"
})
@Generated("jsonschema2pojo")
public class TicketJson {

    @JsonProperty("cc_emails")
    private List<String> ccEmails = null;
    @JsonProperty("fwd_emails")
    private List<Object> fwdEmails = null;
    @JsonProperty("reply_cc_emails")
    private List<String> replyCcEmails = null;
    @JsonProperty("ticket_cc_emails")
    private List<String> ticketCcEmails = null;
    @JsonProperty("fr_escalated")
    private Boolean frEscalated;
    @JsonProperty("spam")
    private Boolean spam;
    @JsonProperty("email_config_id")
    private String emailConfigId;
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("requester_id")
    private String requesterId;
    @JsonProperty("responder_id")
    private String responderId;
    @JsonProperty("source")
    private Integer source;
    @JsonProperty("company_id")
    private String companyId;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("support_email")
    private String supportEmail;
    @JsonProperty("to_emails")
    private List<String> toEmails = null;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("due_by")
    private String dueBy;
    @JsonProperty("fr_due_by")
    private String frDueBy;
    @JsonProperty("is_escalated")
    private Boolean isEscalated;
    @JsonProperty("custom_fields")
    private CustomFields customFields;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("nr_escalated")
    private Boolean nrEscalated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cc_emails")
    public List<String> getCcEmails() {
        return ccEmails;
    }

    @JsonProperty("cc_emails")
    public void setCcEmails(List<String> ccEmails) {
        this.ccEmails = ccEmails;
    }

    @JsonProperty("fwd_emails")
    public List<Object> getFwdEmails() {
        return fwdEmails;
    }

    @JsonProperty("fwd_emails")
    public void setFwdEmails(List<Object> fwdEmails) {
        this.fwdEmails = fwdEmails;
    }

    @JsonProperty("reply_cc_emails")
    public List<String> getReplyCcEmails() {
        return replyCcEmails;
    }

    @JsonProperty("reply_cc_emails")
    public void setReplyCcEmails(List<String> replyCcEmails) {
        this.replyCcEmails = replyCcEmails;
    }

    @JsonProperty("ticket_cc_emails")
    public List<String> getTicketCcEmails() {
        return ticketCcEmails;
    }

    @JsonProperty("ticket_cc_emails")
    public void setTicketCcEmails(List<String> ticketCcEmails) {
        this.ticketCcEmails = ticketCcEmails;
    }

    @JsonProperty("fr_escalated")
    public Boolean getFrEscalated() {
        return frEscalated;
    }

    @JsonProperty("fr_escalated")
    public void setFrEscalated(Boolean frEscalated) {
        this.frEscalated = frEscalated;
    }

    @JsonProperty("spam")
    public Boolean getSpam() {
        return spam;
    }

    @JsonProperty("spam")
    public void setSpam(Boolean spam) {
        this.spam = spam;
    }

    @JsonProperty("email_config_id")
    public String getEmailConfigId() {
        return emailConfigId;
    }

    @JsonProperty("email_config_id")
    public void setEmailConfigId(String emailConfigId) {
        this.emailConfigId = emailConfigId;
    }

    @JsonProperty("group_id")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("requester_id")
    public String getRequesterId() {
        return requesterId;
    }

    @JsonProperty("requester_id")
    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    @JsonProperty("responder_id")
    public String getResponderId() {
        return responderId;
    }

    @JsonProperty("responder_id")
    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    @JsonProperty("source")
    public Integer getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Integer source) {
        this.source = source;
    }

    @JsonProperty("company_id")
    public String getCompanyId() {
        return companyId;
    }

    @JsonProperty("company_id")
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("support_email")
    public String getSupportEmail() {
        return supportEmail;
    }

    @JsonProperty("support_email")
    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    @JsonProperty("to_emails")
    public List<String> getToEmails() {
        return toEmails;
    }

    @JsonProperty("to_emails")
    public void setToEmails(List<String> toEmails) {
        this.toEmails = toEmails;
    }

    @JsonProperty("product_id")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("due_by")
    public String getDueBy() {
        return dueBy;
    }

    @JsonProperty("due_by")
    public void setDueBy(String dueBy) {
        this.dueBy = dueBy;
    }

    @JsonProperty("fr_due_by")
    public String getFrDueBy() {
        return frDueBy;
    }

    @JsonProperty("fr_due_by")
    public void setFrDueBy(String frDueBy) {
        this.frDueBy = frDueBy;
    }

    @JsonProperty("is_escalated")
    public Boolean getIsEscalated() {
        return isEscalated;
    }

    @JsonProperty("is_escalated")
    public void setIsEscalated(Boolean isEscalated) {
        this.isEscalated = isEscalated;
    }

    @JsonProperty("custom_fields")
    public CustomFields getCustomFields() {
        return customFields;
    }

    @JsonProperty("custom_fields")
    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
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

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    
    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    @JsonProperty("nr_escalated")
    public Boolean getNrEscalated() {
        return nrEscalated;
    }

    @JsonProperty("nr_escalated")
    public void setNrEscalated(Boolean nrEscalated) {
        this.nrEscalated = nrEscalated;
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
