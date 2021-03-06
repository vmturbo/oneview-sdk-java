/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogicalSwitchDomainInfo implements Serializable {

    private static final long serialVersionUID = -2430541306887013619L;

    private String domainId;
    private String masterMacAddress;
    private List<LogicalSwitchDomainPerSwitch> perSwitchDomain;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getMasterMacAddress() {
        return masterMacAddress;
    }

    public void setMasterMacAddress(String masterMacAddress) {
        this.masterMacAddress = masterMacAddress;
    }

    public List<LogicalSwitchDomainPerSwitch> getPerSwitchDomain() {
        return perSwitchDomain;
    }

    public void setPerSwitchDomain(List<LogicalSwitchDomainPerSwitch> perSwitchDomain) {
        this.perSwitchDomain = perSwitchDomain;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        LogicalSwitchDomainInfo that = (LogicalSwitchDomainInfo) obj;

        return new EqualsBuilder()
                .append(domainId, that.domainId)
                .append(masterMacAddress, that.masterMacAddress)
                .append(perSwitchDomain, that.perSwitchDomain)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(domainId)
                .append(masterMacAddress)
                .append(perSwitchDomain)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("domainId", domainId)
                .append("masterMacAddress", masterMacAddress)
                .append("perSwitchDomain", perSwitchDomain)
                .toString();
    }
}
