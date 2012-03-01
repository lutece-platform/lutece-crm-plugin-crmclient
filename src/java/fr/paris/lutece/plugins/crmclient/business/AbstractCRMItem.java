/*
 * Copyright (c) 2002-2012, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.crmclient.business;

import fr.paris.lutece.plugins.crmclient.service.CRMClientException;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * AbstractCRMItem
 *
 */
public abstract class AbstractCRMItem implements Serializable, ICRMItem
{
    // Keys
    public static final String NOTIFICATION_OBJECT = "notification_object";
    public static final String NOTIFICATION_MESSAGE = "notification_message";
    public static final String NOTIFICATION_SENDER = "notification_sender";
    public static final String ID_DEMAND = "id_demand";
    public static final String STATUS_TEXT = "status_text";
    private static final long serialVersionUID = -6044853153732668036L;

    // PROPERTY
    private static final String PROPERTY_WS_CRM_REST_WEBAPP_URL = "crmclient.crm.rest.webapp.url";

    // Private parameters
    private Map<String, String> _mapParameters = new LinkedHashMap<String, String>(  );

    /**
     * {@inheritDoc}
     */
    public void setParameters( Map<String, String> mapParameters )
    {
        _mapParameters = mapParameters;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, String> getParameters(  )
    {
        return _mapParameters;
    }

    /**
     * {@inheritDoc}
     */
    public void putParameter( String strKey, String strValue )
    {
        _mapParameters.put( strKey, strValue );
    }

    /**
     * Get the CRM rest webapp url
     * @return the crm rest webapp url
     * @throws CRMClientException exception if the property file is not well configured
     */
    protected String getCRMRestWebappUrl(  ) throws CRMClientException
    {
        String strCRMRestWebappUrl = AppPropertiesService.getProperty( PROPERTY_WS_CRM_REST_WEBAPP_URL );

        if ( StringUtils.isBlank( strCRMRestWebappUrl ) )
        {
            throw new CRMClientException( 
                "CRMClient - Could not retrieve the CRM rest webapp URL : The property file 'crmclient.properties' is not well configured." );
        }

        return strCRMRestWebappUrl;
    }
}
