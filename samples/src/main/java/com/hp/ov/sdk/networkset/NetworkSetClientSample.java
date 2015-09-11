/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.networkset;

import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.NetworkSetClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * NetworkSetClientSample is a sample program to consume a consolidated set of ethernet network 
 * resource of HP OneView. It invokes APIs of NetworkSetClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations network set resource
 */
public class NetworkSetClientSample {

    private RestParams params;
    private static NetworkSetClient networkSetClient;
    private static UrlUtils urlUtils;
    private static ResourceDtoUtils resourceDtoUtils;
    private static TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================

    private static final String resourceId = "4a2dec96-6344-4f15-821c-ce51734faba7";
    private static final String resourceName = "NetworkSet_Prod";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402", "Prod_403", "Prod_404");

    // ================================

    private static void init() throws Exception {
        networkSetClient = HPOneViewSdkBeanFactory.getNetworkSetClient();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        resourceDtoUtils = HPOneViewSdkBeanFactory.getResourceDtoUtils();
    }

    private void getNetworkSetById() throws InstantiationException, IllegalAccessException {
        NetworkSets networkSetDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            networkSetDto = networkSetClient.getNetworkSets(params, resourceId);

            System.out.println("NetworkSetClientTest : getNetworkSetById : " + "network set object returned to client : "
                    + networkSetDto.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetById : " + "resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetById : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkSetClientTest : getNetworkSetById : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetById : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetById : " + "arguments are null ");
            return;
        }
    }

    private void getAllNetworkSet() throws InstantiationException, IllegalAccessException {
        NetworkSetCollection networkSetCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            networkSetCollectionDto = networkSetClient.getAllNetworkSets(params);

            System.out.println("NetworkSetClientTest : getAllNetworkSet : " + "network set object returned to client : "
                    + networkSetCollectionDto.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkSetClientTest : getAllNetworkSet : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkSetClientTest : getAllNetworkSet : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkSetClientTest : getAllNetworkSet : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkSetClientTest : getAllNetworkSet : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkSetClientTest : getAllNetworkSet : " + "arguments are null ");
            return;
        }

    }

    private void getNetworkSetByName() throws InstantiationException, IllegalAccessException {
        NetworkSets networkSetDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            networkSetDto = networkSetClient.getNetworkSetsByName(params, resourceName);

            System.out.println("NetworkSetClientTest : getNetworkSetByName : " + "network set object returned to client : "
                    + networkSetDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetByName : " + "resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetByName : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkSetClientTest : getNetworkSetByName : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetByName : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkSetClientTest : getNetworkSetByName : " + "arguments are null ");
            return;
        }

    }

    private void createNetworkSet() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network set request body
            final NetworkSets networkSetDto = resourceDtoUtils.buildNetworkSetDto(params, resourceName, networkNames);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkSetClient.createNetworkSet(params, networkSetDto, false, false);

            System.out.println("NetworkSetClientTest : createNetworkSet : " + "network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkSetClientTest : createNetworkSet :" + " resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("NetworkSetClientTest : createNetworkSet :"
                    + "may be duplicate resource name or invalid inputs. check inputs and try again : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkSetClientTest : createNetworkSet : " + "no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkSetClientTest : createNetworkSet : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkSetClientTest : createNetworkSet : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("NetworkSetClientTest : createNetworkSet : "
                    + "errors in task, please check task resource for more details ");
            return;
        }

    }

    private void updateNetworkSet() throws InstantiationException, IllegalAccessException {
        NetworkSets networkSetDto = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            networkSetDto = networkSetClient.getNetworkSetsByName(params, resourceName);

            if (null != networkSetDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(networkSetDto.getUri());
            }
            // Test name
            networkSetDto.setName(resourceName + "_updated");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkSetClient.updateNetworkSet(params, resourceId, networkSetDto, false, false);

            System.out.println("NetworkSetClientTest : updateNetworkSet : " + "network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkSetClientTest : updateNetworkSet :" + " resource you are looking is not found for update");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("NetworkSetClientTest : updateNetworkSet :"
                    + "may be duplicate resource name or invalid inputs. check inputs and try again : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkSetClientTest : updateNetworkSet :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkSetClientTest : updateNetworkSet :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkSetClientTest : updateNetworkSet :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkSetClientTest : updateNetworkSet : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("NetworkSetClientTest : updateNetworkSet : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteNetworkSet() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = networkSetClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = networkSetClient.deleteNetworkSet(params, resourceId, false);

            System.out.println("NetworkSetClientTest : deleteNetworkSet : " + "network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkSetClientTest : deleteNetworkSetSet :" + " resource not found for deleting ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkSetClientTest : deleteNetworkSetSet :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkSetClientTest : deleteNetworkSet :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkSetClientTest : deleteNetworkSet : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkSetClientTest : deleteNetworkSet :" + " arguments are null ");
            return;
        }

    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        NetworkSetClientSample client = new NetworkSetClientSample();
        client.getNetworkSetById();
        client.getAllNetworkSet();
        client.createNetworkSet();
        client.getNetworkSetByName();
        client.updateNetworkSet();
        client.createNetworkSet();
        client.deleteNetworkSet();
    }
}
