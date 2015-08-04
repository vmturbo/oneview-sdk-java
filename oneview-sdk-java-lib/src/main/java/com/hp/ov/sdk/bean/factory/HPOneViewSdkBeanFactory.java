// OneViewLib.java - (insert one line description here)
// (C) Copyright 2015 Hewlett-Packard Development Company, L.P.

package com.hp.ov.sdk.bean.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hp.ov.sdk.messaging.msmb.services.MsmbConnectionManager;
import com.hp.ov.sdk.messaging.msmb.services.MsmbConnectionManagerImpl;
import com.hp.ov.sdk.messaging.scmb.services.ScmbAlertsHandler;
import com.hp.ov.sdk.messaging.scmb.services.ScmbConnectionManager;
import com.hp.ov.sdk.messaging.scmb.services.ScmbConnectionManagerImpl;
import com.hp.ov.sdk.rest.client.EnclosureClient;
import com.hp.ov.sdk.rest.client.EnclosureClientImpl;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClientImpl;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.FcNetworkClientImpl;
import com.hp.ov.sdk.rest.client.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.FirmwareDriverClientImpl;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClientImpl;
import com.hp.ov.sdk.rest.client.NetworkClient;
import com.hp.ov.sdk.rest.client.NetworkClientImpl;
import com.hp.ov.sdk.rest.client.NetworkSetClient;
import com.hp.ov.sdk.rest.client.NetworkSetClientImpl;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClientImpl;
import com.hp.ov.sdk.rest.client.ServerProfileClient;
import com.hp.ov.sdk.rest.client.ServerProfileClientImpl;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskMonitorManagerImpl;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.tasks.TaskServiceManagerImpl;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;

/**
 * 
 */
public class HPOneViewSdkBeanFactory
{
    private static AnnotationConfigApplicationContext context;

    public HPOneViewSdkBeanFactory()
    {

    }

    private static ApplicationContext getApplicationContext()
    {
        if (null == context)
        {
            context = new AnnotationConfigApplicationContext();
            context.scan("com.hp.ov.sdk");
            context.refresh();
        }
        return context;
    }

    public static NetworkClient getNetworkClient()
    {
        return getApplicationContext().getBean(NetworkClientImpl.class);
    }

    public static FcNetworkClient getFcNetworkClient()
    {
        return getApplicationContext().getBean(FcNetworkClientImpl.class);
    }

    public static NetworkSetClient getNetworkSetClient()
    {
        return getApplicationContext().getBean(NetworkSetClientImpl.class);
    }

    public static LogicalInterconnectGroupClient getLogicalInterconnectGroupClient()
    {
        return getApplicationContext().getBean(LogicalInterconnectGroupClientImpl.class);
    }

    public static EnclosureGroupClient getEnclosureGroupClient()
    {
        return getApplicationContext().getBean(EnclosureGroupClientImpl.class);
    }

    public static EnclosureClient getEnclosureClient()
    {
        return getApplicationContext().getBean(EnclosureClientImpl.class);
    }

    public static FirmwareDriverClient getFirmwareDriverClient()
    {
        return getApplicationContext().getBean(FirmwareDriverClientImpl.class);
    }

    public static ServerProfileClient getServerProfileClient()
    {
        return getApplicationContext().getBean(ServerProfileClientImpl.class);
    }

    public static ServerHardwareClient getServerHardwareClient()
    {
        return getApplicationContext().getBean(ServerHardwareClientImpl.class);
    }

    public static UrlUtils getUrlUtils()
    {
        return getApplicationContext().getBean(UrlUtils.class);
    }

    public static TaskMonitorManager getTaskMonitorManager()
    {
        return getApplicationContext().getBean(TaskMonitorManagerImpl.class);
    }

    public static TaskServiceManager getTaskServiceManager()
    {
        return getApplicationContext().getBean(TaskServiceManagerImpl.class);
    }

    public static SdkUtils getSdkUtils()
    {
        return getApplicationContext().getBean(SdkUtils.class);
    }

    public static ResourceDtoUtils getResourceDtoUtils()
    {
        return getApplicationContext().getBean(ResourceDtoUtils.class);
    }

    public static ScmbAlertsHandler getScmbAlertsHandler()
    {
        return getApplicationContext().getBean(ScmbAlertsHandler.class);
    }

    public static ScmbConnectionManager getScmbConnectionManager()
    {
        return getApplicationContext().getBean(ScmbConnectionManagerImpl.class);
    }
    public static MsmbConnectionManager getMsmbConnectionManager()
    {
        return getApplicationContext().getBean(MsmbConnectionManagerImpl.class);
    }
}