package com.jini.zendesk.model;

import java.util.List;
import java.util.Objects;

public class Ticket {

    private String _id;
    private String url;
    private String external_id;
    private String created_at;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private Integer submitter_id;
    private Integer assignee_id;
    private Integer organization_id;
    private List<String> tags;
    private Boolean has_incidents;
    private String due_at;
    private String via;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSubmitter_id() {
        return submitter_id;
    }

    public void setSubmitter_id(Integer submitter_id) {
        this.submitter_id = submitter_id;
    }

    public Integer getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Integer assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getHas_incidents() {
        return has_incidents;
    }

    public void setHas_incidents(Boolean has_incidents) {
        this.has_incidents = has_incidents;
    }

    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(_id, ticket._id) &&
                Objects.equals(url, ticket.url) &&
                Objects.equals(external_id, ticket.external_id) &&
                Objects.equals(created_at, ticket.created_at) &&
                Objects.equals(type, ticket.type) &&
                Objects.equals(subject, ticket.subject) &&
                Objects.equals(description, ticket.description) &&
                Objects.equals(priority, ticket.priority) &&
                Objects.equals(status, ticket.status) &&
                Objects.equals(submitter_id, ticket.submitter_id) &&
                Objects.equals(assignee_id, ticket.assignee_id) &&
                Objects.equals(organization_id, ticket.organization_id) &&
                Objects.equals(tags, ticket.tags) &&
                Objects.equals(has_incidents, ticket.has_incidents) &&
                Objects.equals(due_at, ticket.due_at) &&
                Objects.equals(via, ticket.via);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, url, external_id, created_at, type, subject, description, priority, status, submitter_id, assignee_id, organization_id, tags, has_incidents, due_at, via);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", external_id='" + external_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", type='" + type + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", submitter_id=" + submitter_id +
                ", assignee_id=" + assignee_id +
                ", organization_id=" + organization_id +
                ", tags=" + tags +
                ", has_incidents=" + has_incidents +
                ", due_at='" + due_at + '\'' +
                ", via='" + via + '\'' +
                '}';
    }
}
