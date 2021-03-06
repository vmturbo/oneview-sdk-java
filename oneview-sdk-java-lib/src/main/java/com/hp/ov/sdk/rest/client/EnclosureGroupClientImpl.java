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
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;


public class EnclosureGroupClientImpl implements EnclosureGroupClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnclosureGroupClientImpl.class);

    private final ResourceAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private EnclosureGroupClientImpl(HttpRestClient httpClient, ResourceAdaptor adaptor, TaskAdaptor taskAdaptor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
    }

    public static EnclosureGroupClient getClient() {
        return new EnclosureGroupClientImpl(HttpRestClient.getClient(), new ResourceAdaptor(), TaskAdaptor.getInstance());
    }

    @Override
    public EnclosureGroups getEnclosureGroup(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureGroupClientImpl : getEnclosureGroup : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureGroupClientImpl : getEnclosureGroup : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE_GROUP,
                    null);
        }

        final EnclosureGroups enclosureGroupDto = adaptor.buildResourceObject(returnObj, EnclosureGroups.class);

        LOGGER.debug("EnclosureGroupClientImpl : getEnclosureGroup : Name :" + enclosureGroupDto.getName());
        LOGGER.info("EnclosureGroupClientImpl : getEnclosureGroup : End");

        return enclosureGroupDto;
    }

    @Override
    public ResourceCollection<EnclosureGroups> getAllEnclosureGroups(final RestParams params) {
        LOGGER.info("EnclosureGroupClientImpl : getAllEnclosureGroups : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI));

        // call rest client
        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureGroupClientImpl : getAllEnclosureGroups : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE_GROUPS,
                    null);
        }

        ResourceCollection<EnclosureGroups> enclosureGroupCollectionDto
                = adaptor.buildResourceCollection(returnObj, EnclosureGroups.class);

        LOGGER.debug("EnclosureGroupClientImpl : getAllEnclosureGroups : members count :" + enclosureGroupCollectionDto.getCount());
        LOGGER.info("EnclosureGroupClientImpl : getAllEnclosureGroups : End");

        return enclosureGroupCollectionDto;
    }

    @Override
    public EnclosureGroups getEnclosureGroupByName(final RestParams params, final String name) {
        EnclosureGroups enclosureGroupDto = null;
        LOGGER.info("EnclosureGroupClientImpl : getEnclosureGroupByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureGroupClientImpl : getEnclosureGroupByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE_GROUPS,
                    null);
        }

        ResourceCollection<EnclosureGroups> enclosureGroupCollectionDto
                = adaptor.buildResourceCollection(returnObj, EnclosureGroups.class);

        if (!enclosureGroupCollectionDto.isEmpty()) {
            enclosureGroupDto = enclosureGroupCollectionDto.getMembers().get(0);
        } else {
            enclosureGroupDto = null;
        }

        if (enclosureGroupDto == null) {
            LOGGER.error("EnclosureGroupClientImpl : getEnclosureGroupByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.ENCLOSURE_GROUP,
                    null);
        }
        LOGGER.info("EnclosureGroupClientImpl : getEnclosureGroupByName : End");

        return enclosureGroupDto;
    }

    @Override
    public EnclosureGroups createEnclosureGroup(final RestParams params, final EnclosureGroups dto, final boolean useJsonRequest) {
        LOGGER.info("EnclosureGroupClientImpl : createEnclosureGroupV2 : Start");
        String returnObj = null;

        // validate params
        if (dto == null || params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.ENCLOSURE_GROUP,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto

        jsonObject = adaptor.buildJsonRequest(dto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);

        // convert returnObj to enclosureresourceV2
        final EnclosureGroups enclosureGroupV2 = (EnclosureGroups) taskAdaptor.buildClassDto(returnObj, EnclosureGroups.class);

        LOGGER.debug("EnclosureGroupClientImpl : createEnclosureGroupV2 : returnObj =" + returnObj);
        LOGGER.debug("EnclosureGroupClientImpl : createEnclosureGroupV2 : enclosureObject =" + enclosureGroupV2);

        LOGGER.info("EnclosureGroupClientImpl : createEnclosureGroupV2 : End");

        return enclosureGroupV2;
    }

    @Override
    public EnclosureGroups updateEnclosureGroup(final RestParams params, final String resourceId,
            final EnclosureGroups enclosureGroupDto, final boolean useJsonRequest) {
        LOGGER.info("EnclosureGroupClientImpl : updateEnclosureGroupV2 : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (enclosureGroupDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.ENCLOSURE_GROUP,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonRequest(enclosureGroupDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);

        // convert returnObj to taskResource
        final EnclosureGroups enclosureGroupV2 = (EnclosureGroups) taskAdaptor.buildClassDto(returnObj, EnclosureGroups.class);

        LOGGER.debug("EnclosureGroupClientImpl : updateEnclosureGroupV2 : returnObj =" + returnObj);
        LOGGER.debug("EnclosureGroupClientImpl : updateEnclosureGroupV2 : enclosureObject =" + enclosureGroupV2);

        LOGGER.info("EnclosureGroupClientImpl : updateEnclosureGroupV2 : End");

        return enclosureGroupV2;
    }

    @Override
    public String deleteEnclosureGroup(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureGroupClientImpl : deleteEnclosureGroup : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureGroupClientImpl : deleteEnclosureGroup : response from OV :" + returnObj);
        /************ Returns Response code 204 *********************************/

        LOGGER.debug("EnclosureGroupClientImpl : deleteEnclosureGroup : returnObj =" + returnObj);

        LOGGER.info("EnclosureGroupClientImpl : deleteEnclosureGroup : End");

        return "Deleted";
    }

    @Override
    public String getConfigurationScript(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureGroupClientImpl : getConfigurationScript : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId,
                SdkConstants.SCRIPT));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureGroupClientImpl : getConfigurationScript : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE_GROUP,
                    null);
        }

        LOGGER.info("EnclosureGroupClientImpl : getConfigurationScript : End");

        return returnObj;
    }

    @Override
    public String updateConfigurationScript(final RestParams params, final String resourceId, final String scriptData) {
        LOGGER.info("EnclosureGroupClientImpl : updateConfigurationScript : Start");

        // validate args
        if ((null == params) || (params.getHeaders() == null)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }

        String contentType = params.getHeaders().get("Content-Type");

        if (!"text/plain".equalsIgnoreCase(contentType)) {
            LOGGER.error("EnclosureGroupClientImpl : updateConfigurationScript : Content-Type must be \"text/plain\"");

            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.ENCLOSURE_GROUP, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId,
                SdkConstants.SCRIPT));

        final String returnObj = httpClient.sendRequest(params, scriptData);
        LOGGER.debug("EnclosureGroupClientImpl : updateConfigurationScript : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE_GROUP,
                    null);
        }

        LOGGER.info("EnclosureGroupClientImpl : updateConfigurationScript : End");

        return returnObj;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        EnclosureGroups enclosureGroups = getEnclosureGroupByName(params, name);

        if (null != enclosureGroups.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(enclosureGroups.getUri());
        }
        return resourceId;
    }
}
