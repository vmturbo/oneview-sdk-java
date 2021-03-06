/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class QosClassificationMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Integer> dot1pClassMapping = new ArrayList<Integer>();
    private List<String> dscpClassMapping = new ArrayList<String>();

    /**
     *
     * @return The dot1pClassMapping
     */
    public List<Integer> getDot1pClassMapping() {
        return dot1pClassMapping;
    }

    /**
     *
     * @param dot1pClassMapping
     *            The dot1pClassMapping
     */
    public void setDot1pClassMapping(List<Integer> dot1pClassMapping) {
        this.dot1pClassMapping = dot1pClassMapping;
    }

    /**
     *
     * @return The dscpClassMapping
     */
    public List<String> getDscpClassMapping() {
        return dscpClassMapping;
    }

    /**
     *
     * @param dscpClassMapping
     *            The dscpClassMapping
     */
    public void setDscpClassMapping(List<String> dscpClassMapping) {
        this.dscpClassMapping = dscpClassMapping;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(dot1pClassMapping)
                .append(dscpClassMapping)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosClassificationMapping) == false) {
            return false;
        }
        QosClassificationMapping rhs = ((QosClassificationMapping) other);
        return new EqualsBuilder()
                .append(dot1pClassMapping, rhs.dot1pClassMapping)
                .append(dscpClassMapping, rhs.dscpClassMapping)
                .isEquals();
    }

}
