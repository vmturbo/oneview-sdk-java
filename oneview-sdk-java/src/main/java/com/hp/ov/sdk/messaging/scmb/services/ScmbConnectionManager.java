/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.services;

import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ScmbConnectionManager
{

    public void startScmb(final RestParams params);

    public void removeScmbConnection(final RestParams params);

    public void processConsumer(final RestParams params, final String routingKey, final ScmbMessageExecutionQueue messageQueue);

    public void stopScmb(final RestParams params);
}