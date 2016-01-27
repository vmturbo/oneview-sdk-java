/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKApiVersionMismatchException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.rest.client.ConnectionTemplateClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.NetworkClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.StorageVolumeClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.ApplianceDetails;
import com.hp.ov.sdk.rest.login.LoginSessions;

@Component
public class SdkUtils {

    private static final Logger logger = LoggerFactory.getLogger(SdkUtils.class);

    @Autowired
    private LoginSessions loginSessions;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private NetworkClient networkClient;

    @Autowired
    private FcNetworkClient fcNetworkClient;

    @Autowired
    private InterconnectTypeClient interconnectTypeClient;

    @Autowired
    private LogicalInterconnectGroupClient logicalInterconnectGroupClient;

    @Autowired
    private ConnectionTemplateClient connectionTemplateClient;

    @Autowired
    private ServerHardwareClient serverHardwareClient;

    @Autowired
    private EnclosureGroupClient enclosureGroupClient;

    @Autowired
    private StorageVolumeClient storageVolumeClient;

    @Autowired
    private ApplianceDetails applianceDetails;

    private LogicalInterconnectGroups logicalInterconnectGroupsDto;

    public RestParams createRestParams(final RestParams params) {
        // Get version
        final ApplianceDetailsDto applianceDetailsDto = applianceDetails.getVersion(params);
        // validate the API version
        validateApiVersion(params.getApiVersion(), applianceDetailsDto.getMinimumVersion(), applianceDetailsDto.getCurrentVersion());

        // Create LoginSessionDto;
        final LoginSessionDto loginSessionDto = loginSessionAdaptor.buildDto(params);
        // Get login session
        final String sessionId = loginSessions.getLoginSessionId(params, loginSessionDto);
        params.setSessionId(sessionId);

        return params;
    }

    public RestParams createRestParamsWithoutSessionId(final RestParams params) {
        // Get version
        final ApplianceDetailsDto applianceDetailsDto = applianceDetails.getVersion(params);
        params.setApiVersion(applianceDetailsDto.getCurrentVersion());

        return params;
    }

    private void validateApiVersion(final int requestedVersion, final int applianceMinimumVersion, final int applianceCurrentVersion) {
        logger.info("########### Checking API Version Start ####################");

        if (requestedVersion >= applianceMinimumVersion && requestedVersion  <= applianceCurrentVersion) {
            logger.info("API version : You are trying to connect to appliance verion: "
                    + requestedVersion  + " and it is matching with the minimum and current version ("
                    + applianceMinimumVersion + " to " + applianceCurrentVersion + ")");

            logger.info("########### Checking API Version End ####################");
            return;
        }

        throw new SDKApiVersionMismatchException(SDKErrorEnum.apiMismatchError, null, null, null, SdkConstants.APPLIANCE, null);
    }

    public String getConnectionTemplateUri(final RestParams params, final String connectionTemplateName) {
        return connectionTemplateClient.getConnectionTemplateByName(params, connectionTemplateName).getUri();
    }

    public List<String> getNetworkUris(final RestParams params, final List<String> networkNames) {
        final List<String> networkUris = new ArrayList<String>();
        String networkUri = null;
        for (int i = 0; i < networkNames.size(); i++) {
            final Network dto = networkClient.getNetworkByName(params, networkNames.get(i));
            if (dto.getUri() != null) {
                networkUri = dto.getUri();
                networkUris.add(networkUri);
            }
        }
        return networkUris;
    }

    public String getNetworkUri(final RestParams params, final String networkName) {
        return networkClient.getNetworkByName(params, networkName).getUri();
    }

    public String getFcNetworkUri(final RestParams params, final String networkName) {
        return fcNetworkClient.getFcNetworkByName(params, networkName).getUri();
    }

    public List<String> getFcNetworkUris(final RestParams params, final List<String> networkNames) {
        final List<String> networkUris = new ArrayList<String>();
        FcNetwork dto = null;
        String fcNetowrkUri = null;
        for (int i = 0; i < networkNames.size(); i++) {
            dto = fcNetworkClient.getFcNetworkByName(params, networkNames.get(i));
            if (null != dto.getUri()) {
                fcNetowrkUri = dto.getUri();
                networkUris.add(fcNetowrkUri);
            }
        }
        return networkUris;
    }

    public String getPermittedInterconnectTypeUri(final RestParams params, final String permittedInterconnectType) {
        return interconnectTypeClient.getInterconnectTypeByName(params, permittedInterconnectType).getUri();
    }

    public String getPermittedInterconnectTypeUriForLigBasedOnBay(final RestParams params, final String ligName, final Integer bay) {
        logicalInterconnectGroupsDto = logicalInterconnectGroupClient.getLogicalInterconnectGroupByName(params, ligName);
        if (logicalInterconnectGroupsDto == null) {
            return null;
        }

        if (logicalInterconnectGroupsDto.getInterconnectMapTemplate() == null) {
            return null;
        }

        for (final InterconnectMapEntryTemplate mapTemplate : logicalInterconnectGroupsDto.getInterconnectMapTemplate()
                .getInterconnectMapEntryTemplates()) {
            for (final LocationEntry locationEntry : mapTemplate.getLogicalLocation().getLocationEntries()) {
                if (locationEntry.getType().equals(LocationEntry.Type.Bay) && locationEntry.getRelativeValue() == bay) {
                    return mapTemplate.getPermittedInterconnectTypeUri();
                }
            }
        }
        return null;
    }

    public String getServerHardwareUri(final RestParams params, final String serverHardwareName) {
        return serverHardwareClient.getServerHardwareByName(params, serverHardwareName).getUri();
    }

    public String getServerHardwareTypeUri(final RestParams params, final String serverHardwareName) {
        return serverHardwareClient.getServerHardwareByName(params, serverHardwareName).getServerHardwareTypeUri();
    }

    public String getEnclosureGroupUri(final RestParams params, final String enclosureGroupName) {
        return enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();
    }

    public Boolean isVolumeSharable(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getShareable();
    }

    public String getVolumeUri(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getUri();
    }

    public String getStoragePoolFromVolume(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getStoragePoolUri();
    }

    public String getStorageSystemFromVolume(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getStorageSystemUri();
    }
}
