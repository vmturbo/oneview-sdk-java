/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.JsonRequest;

/**
 * The FcNetwork data transfer object (DTO) contains the information used to
 * represent a fiber channel path in the system. It is passed in to the
 * add/update fcnetwork REST api, as well as the add/update fcnetwork through
 * java client api.
 */

public class FcNetwork implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     * (Required)
     *
     */
    private FcNetwork.FabricType fabricType = FcNetwork.FabricType.fromValue("FabricAttach");
    /**
     *
     * (Required)
     *
     */
    private String connectionTemplateUri;
    /**
     *
     * (Required)
     *
     */
    private Integer linkStabilityTime = 30;
    private String managedSanUri;
    /**
     *
     * (Required)
     *
     */
    private Boolean autoLoginRedistribution = false;
    private String description;
    private String status;
    private String name;
    private String state;
    private String eTag;
    private String created;
    private String modified;
    private String category;
    private String uri;
    private String type;

    private transient JsonRequest jsonRequest;

    /**
     *
     * (Required)
     *
     * @return The fabricType
     */
    public FcNetwork.FabricType getFabricType() {
        return fabricType;
    }

    /**
     *
     * (Required)
     *
     * @param fabricType
     *            The fabricType
     */
    public void setFabricType(final FcNetwork.FabricType fabricType) {
        this.fabricType = fabricType;
    }

    /**
     *
     * (Required)
     *
     * @return The connectionTemplateUri
     */
    public String getConnectionTemplateUri() {
        return connectionTemplateUri;
    }

    /**
     *
     * (Required)
     *
     * @param connectionTemplateUri
     *            The connectionTemplateUri
     */
    public void setConnectionTemplateUri(final String connectionTemplateUri) {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    /**
     *
     * (Required)
     *
     * @return The linkStabilityTime
     */
    public Integer getLinkStabilityTime() {
        return linkStabilityTime;
    }

    /**
     *
     * (Required)
     *
     * @param linkStabilityTime
     *            The linkStabilityTime
     */
    public void setLinkStabilityTime(final Integer linkStabilityTime) {
        this.linkStabilityTime = linkStabilityTime;
    }

    /**
     *
     * @return The managedSanUri
     */
    public String getManagedSanUri() {
        return managedSanUri;
    }

    /**
     *
     * @param managedSanUri
     *            The managedSanUri
     */
    public void setManagedSanUri(final String managedSanUri) {
        this.managedSanUri = managedSanUri;
    }

    /**
     *
     * (Required)
     *
     * @return The autoLoginRedistribution
     */
    public Boolean getAutoLoginRedistribution() {
        return autoLoginRedistribution;
    }

    /**
     *
     * (Required)
     *
     * @param autoLoginRedistribution
     *            The autoLoginRedistribution
     */
    public void setAutoLoginRedistribution(final Boolean autoLoginRedistribution) {
        this.autoLoginRedistribution = autoLoginRedistribution;
    }

    /**
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *            The description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     *            The state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     *
     * @return The eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     *
     * @param eTag
     *            The eTag
     */
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     *
     * @return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *            The created
     */
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     *
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     *            The modified
     */
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *            The category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     *
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     *            The uri
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *            The type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @return The jsonRequest
     */
    public JsonRequest getJsonRequest() {
        return jsonRequest;
    }

    /**
     *
     * @param jsonRequest
     *            The jsonRequest
     */
    public void setJsonRequest(final JsonRequest jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(jsonRequest).append(type).append(fabricType).append(connectionTemplateUri)
                .append(linkStabilityTime).append(managedSanUri).append(autoLoginRedistribution).append(description).append(status)
                .append(name).append(state).append(eTag).append(created).append(modified).append(category).append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FcNetwork) == false) {
            return false;
        }
        final FcNetwork rhs = ((FcNetwork) other);
        return new EqualsBuilder().append(jsonRequest, rhs.jsonRequest).append(type, rhs.type).append(fabricType, rhs.fabricType)
                .append(connectionTemplateUri, rhs.connectionTemplateUri).append(linkStabilityTime, rhs.linkStabilityTime)
                .append(managedSanUri, rhs.managedSanUri).append(autoLoginRedistribution, rhs.autoLoginRedistribution)
                .append(description, rhs.description).append(status, rhs.status).append(name, rhs.name).append(state, rhs.state)
                .append(eTag, rhs.eTag).append(created, rhs.created).append(modified, rhs.modified).append(category, rhs.category)
                .append(uri, rhs.uri).isEquals();
    }

    public static enum FabricType {

        FabricAttach("FabricAttach"), DirectAttach("DirectAttach");
        private final String value;
        private static Map<String, FcNetwork.FabricType> constants = new HashMap<String, FcNetwork.FabricType>();

        static {
            for (final FcNetwork.FabricType c : values()) {
                constants.put(c.value, c);
            }
        }

        private FabricType(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static FcNetwork.FabricType fromValue(final String value) {
            final FcNetwork.FabricType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
