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
package com.hp.ov.sdk.messaging.core;

import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultSaslConfig;

import javax.net.ssl.SSLContext;


public class RabbitMqClientConnectionFactory {

    public static ConnectionFactory getConnectionFactory(final SSLContext sslContext, final RestParams params) {

        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(params.getHostname());
        factory.setPort(params.getAmqpPort());

        // Set Auth mechanism to "EXTERNAL" so that commonName of the client
        // certificate is mapped to AMQP user name. Hence, No need to set
        // userId/Password here.
        factory.setSaslConfig(DefaultSaslConfig.EXTERNAL);
        factory.useSslProtocol(sslContext);
        factory.setAutomaticRecoveryEnabled(true);

        return factory;
    }

}
