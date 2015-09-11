/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.interconnects;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.InterconnectTypeCollectionV2;
import com.hp.ov.sdk.dto.generated.InterconnectTypes;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.InterconnectTypeClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * InterconnectTypeClientSample is a sample program to consume the characteristics model of an interconnect in 
 * HP OneView.It invokes APIs of InterconnectTypeClient which is in sdk library to perform GET
 * operations on interconnect type resource
 */
public class InterconnectTypeClientSample {
    private RestParams params;
    private static InterconnectTypeClient interconnectTypeClient;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "HP VC FlexFabric-20/40 F8 Module";
    private static final String resourceId = "2edca2bb-e8d1-44b9-839e-3e4a95dff9f2";

    // ================================

    private static void init() {
        interconnectTypeClient = HPOneViewSdkBeanFactory.getInterconnectTypeClient();
    }

    private void getInterconnectTypeById() throws InstantiationException, IllegalAccessException {
        InterconnectTypes interconnectTypeDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectTypeDto = interconnectTypeClient.getInterconnectType(params, resourceId);

            System.out.println("InterconnectTypeClientTest : getInterconnectTypeById :"
                    + " interconnect type object returned to client : " + interconnectTypeDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("InterconnectTypeClientTest : getInterconnectTypeById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeById :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeById :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeById :" + " arguments are null ");
            return;
        }

    }

    private void getAllInterconnectType() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        InterconnectTypeCollectionV2 interconnectTypeCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectTypeCollectionDto = interconnectTypeClient.getAllInterconnectType(params);

            System.out.println("InterconnectTypeClientTest : getAllInterconnectType :"
                    + " interconnect type object returned to client : " + interconnectTypeCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectTypeClientTest : getAllInterconnectType " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectTypeClientTest : getAllInterconnectType :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectTypeClientTest : getAllInterconnectType :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectTypeClientTest : getAllInterconnectType :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectTypeClientTest : getAllInterconnectType :" + " arguments are null ");
            return;
        }
    }

    private void getInterconnectTypeByName() throws InstantiationException, IllegalAccessException {
        InterconnectTypes interconnectTypeDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectTypeDto = interconnectTypeClient.getInterconnectTypeByName(params, resourceName);

            System.out.println("InterconnectTypeClientTest : getInterconnectTypeByName :"
                    + " interconnect type object returned to client : " + interconnectTypeDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeByName :" + " resource not found : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectTypeClientTest : getInterconnectTypeByName :" + " arguments are null ");
            return;
        }

    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        InterconnectTypeClientSample client = new InterconnectTypeClientSample();
        client.getInterconnectTypeById();
        client.getAllInterconnectType();
        client.getInterconnectTypeByName();
    }

}