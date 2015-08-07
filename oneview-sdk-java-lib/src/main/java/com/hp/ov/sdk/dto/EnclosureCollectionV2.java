/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.generated.Enclosures;

public class EnclosureCollectionV2 extends BaseCollectionResource<Enclosures> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private final List<Enclosures> members = new ArrayList<Enclosures>();

    @Override
    public List<Enclosures> getMembers() {
        final List<Enclosures> _members = new ArrayList<Enclosures>();
        if (this.members != null && !this.members.isEmpty()) {
            _members.addAll(this.members);
        }
        return _members;
    }

    @Override
    public void setMembers(final List<Enclosures> _members) {
        this.members.clear();
        if (_members != null && !_members.isEmpty()) {
            this.members.addAll(_members);
            this.setCount(this.members.size());
        }
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
