/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.sanmanager;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FcSansManagedSanClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * ManagedSanClientSample is a sample program capture/consume the physical or logical fibre channel SAN or a Flat SAN  
 * managed by HP OneView. It invokes APIs of ManagedSanClientSample which is in sdk library to perform GET/PUT
 * operations on san resource
 */
public class FcSansManagedSanClientSample {
    private RestParams params;
    private static FcSansManagedSanClient fcSansManagedSanClient;

    // test values - user input
    // ================================
    private static final String resourceId = "ec7ec761-c70a-48c6-9d70-496a2edd7212";
    private static final String resourceName = "SANA";

    // ================================

    private static void init() {
        fcSansManagedSanClient = HPOneViewSdkBeanFactory.getManagedSanClient();
    }

    private void getManagedSan() throws InstantiationException, IllegalAccessException {
        SanResponse sanResponseDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            sanResponseDto = fcSansManagedSanClient.getManagedSan(params, resourceId);

            System.out.println("ManagedSanClientTest : getManagedSan :" + " device manager object returned to client : "
                    + sanResponseDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " arguments are null ");
            return;
        }

    }

    private void getAllManagedSan() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        SanResponseCollection sanResponseCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            sanResponseCollectionDto = fcSansManagedSanClient.getAllManagedSan(params);

            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " device manager object returned to client : "
                    + sanResponseCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " arguments are null ");
            return;
        }

    }

    private void updateManagedSan() throws InstantiationException, IllegalAccessException {
        SanResponse sanResponseReturnDto = null;
        String resourceId = null;
        SanResponse sanResponseDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = fcSansManagedSanClient.getId(params, resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            sanResponseDto = new SanResponse();
            sanResponseDto.setRefreshState(RefreshState.RefreshPending);
            sanResponseReturnDto = fcSansManagedSanClient.updateManagedSan(params, resourceId, sanResponseDto, false, false);

            System.out.println("ManagedSanClientTest : updateManagedSan : " + " device manager object returned to client : "
                    + sanResponseReturnDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ManagedSanClientTest : updateManagedSan : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        FcSansManagedSanClientSample client = new FcSansManagedSanClientSample();
        client.getAllManagedSan();
        client.getManagedSan();
        client.updateManagedSan();
    }
}
