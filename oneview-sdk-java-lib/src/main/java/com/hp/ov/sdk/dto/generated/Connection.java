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
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class Connection implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    private Double connectionId;
    /**
     * 
     * (Required)
     * 
     */
    private String status;
    /**
     * 
     * (Required)
     * 
     */
    private List<Message> messages = new ArrayList<Message>();

    /**
     * 
     * (Required)
     * 
     * @return The connectionId
     */
    public Double getConnectionId() {
        return connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @param connectionId
     *            The connectionId
     */
    public void setConnectionId(final Double connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * (Required)
     * 
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * (Required)
     * 
     * @return The messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * 
     * (Required)
     * 
     * @param messages
     *            The messages
     */
    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(connectionId).append(status).append(messages).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Connection) == false) {
            return false;
        }
        final Connection rhs = ((Connection) other);
        return new EqualsBuilder().append(connectionId, rhs.connectionId).append(status, rhs.status).append(messages, rhs.messages)
                .isEquals();
    }

}
