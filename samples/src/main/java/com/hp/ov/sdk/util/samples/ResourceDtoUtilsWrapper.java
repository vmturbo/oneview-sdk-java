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
package com.hp.ov.sdk.util.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.generated.SanStorage;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.dto.generated.VolumeAttachment;
import com.hp.ov.sdk.dto.samples.NetworkForServerProfile;
import com.hp.ov.sdk.dto.samples.ServerProfileValue;
import com.hp.ov.sdk.dto.samples.UplinkSetValue;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;

public class ResourceDtoUtilsWrapper {

    private final ResourceDtoUtils resourceDtoUtils;

    public ResourceDtoUtilsWrapper(ResourceDtoUtils resourceDtoUtils) {
        this.resourceDtoUtils = resourceDtoUtils;
    }

    public List<UplinkSet> buildUplinkSetGroupDto(final RestParams params, final List<UplinkSetValue> uplinkSetValues) {
        final List<UplinkSet> uplinkSetGroupDto = new ArrayList<UplinkSet>();

        for (UplinkSetValue uplinkSetValue : uplinkSetValues) {
            uplinkSetGroupDto.add(resourceDtoUtils.buildUplinkSetDto(params, uplinkSetValue.getLigName(),
                    uplinkSetValue.getUplinkSetName(), uplinkSetValue.getUplinkSetType(), uplinkSetValue.getBayPortMap(),
                    uplinkSetValue.getNetworkNames()));
        }

        return uplinkSetGroupDto;
    }

    public ServerProfile buildServerProfile(final RestParams params, final ServerProfileValue serverProfileValue) {
        Integer j = 1;
        final HashMap<String, Integer> fcId = new HashMap<String, Integer>();
        List<ProfileConnectionV3> connections = new ArrayList<ProfileConnectionV3>();
        SanStorage sanStorage;

        if (serverProfileValue.getNetworkForServerProfile().size() > 0) {
            for (final NetworkForServerProfile networkForServerProfile : serverProfileValue.getNetworkForServerProfile()) {
                connections.add(resourceDtoUtils.buildProfileConnection(params, j, networkForServerProfile.getNetworkName(),
                        networkForServerProfile.getRequestedMbps(), networkForServerProfile.getAllocatedMbps(),
                        networkForServerProfile.getMaximumMbps(), networkForServerProfile.getNetworkType(),
                        networkForServerProfile.getBoot()));
                if ((networkForServerProfile.getNetworkType().toString()).equalsIgnoreCase("FibreChannel")) {
                    fcId.put(networkForServerProfile.getNetworkName(), j);
                }
                j++;
            }
        } else {
            connections = null;
        }
        if (serverProfileValue.getStorageVolumeForServerProfile().getStorageVolume().size() > 0) {
            final List<VolumeAttachment> volumeAttachments = new ArrayList<VolumeAttachment>();
            for (final StorageVolume storageVolume : serverProfileValue.getStorageVolumeForServerProfile().getStorageVolume()) {
                volumeAttachments.add(resourceDtoUtils.buildVolumeAttachment(params, storageVolume.getVolumeName(),
                        serverProfileValue.getUseBayNameForServerHardwareUri(), j, storageVolume.getIsEnabled(),
                        storageVolume.getStorageTargets(), storageVolume.getStorageTargetType(), storageVolume.getLunType(), fcId));
                j++;
            }
            sanStorage = resourceDtoUtils.buildSanStorage(params, serverProfileValue.getStorageVolumeForServerProfile()
                    .getHostOSType(), volumeAttachments);
        } else {
            sanStorage = null;
        }

        return resourceDtoUtils.buildServerProfile(params, serverProfileValue.getTemplateName(), serverProfileValue.getBayName(),
                serverProfileValue.getUseBayNameForServerHardwareUri(), serverProfileValue.getEnclosureGroupName(),
                serverProfileValue.getAffinity(), serverProfileValue.getWwnType(), serverProfileValue.getMacType(),
                serverProfileValue.getSerialNumberType(), sanStorage, connections, serverProfileValue.getLocalStorage(),
                serverProfileValue.getBoot(), serverProfileValue.getBios(), serverProfileValue.getFirmware());
    }
}
