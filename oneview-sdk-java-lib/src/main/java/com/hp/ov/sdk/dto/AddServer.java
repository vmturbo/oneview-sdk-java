/*
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
 */
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class AddServer implements Serializable {

    private static final long serialVersionUID = -8835431804296816200L;

    private ConfigurationState configurationState;
    private Boolean force;
    private String hostname;
    private LicensingIntent licensingIntent;
    private String password;
    private Boolean restore;
    private String username;

    public ConfigurationState getConfigurationState() {
        return configurationState;
    }

    public void setConfigurationState(ConfigurationState configurationState) {
        this.configurationState = configurationState;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    public void setLicensingIntent(LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRestore() {
        return restore;
    }

    public void setRestore(Boolean restore) {
        this.restore = restore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
