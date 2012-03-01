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
package fr.paris.lutece.plugins.crmclient.service;

import fr.paris.lutece.plugins.crmclient.business.AbstractCRMItem;
import fr.paris.lutece.plugins.crmclient.business.ICRMItem;
import fr.paris.lutece.plugins.crmclient.business.demand.DemandItem;
import fr.paris.lutece.plugins.crmclient.business.notification.NotificationItem;
import fr.paris.lutece.plugins.crmclient.service.queue.ICRMClientQueue;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

import org.apache.commons.lang.StringUtils;


/**
 *
 * CRMClientService
 *
 */
public final class CRMClientService
{
    private static final String BEAN_CRM_CLIENT_SERVICE = "crmclient.crmClientService";
    private ICRMClientQueue _crmClientQueue;
    private CRMClientWebService _crmClientWebService;

    /**
     * Private constructor
     */
    private CRMClientService(  )
    {
    }

    /**
     * Get the instance of the service
     * @return the instance of the service
     */
    public static CRMClientService getService(  )
    {
        return (CRMClientService) SpringContextService.getPluginBean( CRMClientPlugin.PLUGIN_NAME,
            BEAN_CRM_CLIENT_SERVICE );
    }

    /**
     * Set the crm client queue
     * @param crmClientQueue the crm client queue
     */
    public void setCRMClientQueue( ICRMClientQueue crmClientQueue )
    {
        _crmClientQueue = crmClientQueue;
    }

    /**
     * Set the crm client webservice
     * @param crmClientWebService the crm client webservice
     */
    public void setCRMClientWebService( CRMClientWebService crmClientWebService )
    {
        _crmClientWebService = crmClientWebService;
    }

    /**
     * Get the queue
     * @return the queue
     */
    public ICRMClientQueue getQueue(  )
    {
        return _crmClientQueue;
    }

    /**
     * Notify a demand
     * @param strIdDemand the id demand
     * @param strObject the object
     * @param strMessage the message
     * @param strSender the sender
     */
    public void notify( String strIdDemand, String strObject, String strMessage, String strSender )
    {
        AbstractCRMItem crmItem = new NotificationItem(  );
        crmItem.putParameter( AbstractCRMItem.ID_DEMAND,
            StringUtils.isNotBlank( strIdDemand ) ? strIdDemand : StringUtils.EMPTY );
        crmItem.putParameter( AbstractCRMItem.NOTIFICATION_OBJECT,
            StringUtils.isNotBlank( strObject ) ? strObject : StringUtils.EMPTY );
        crmItem.putParameter( AbstractCRMItem.NOTIFICATION_MESSAGE,
            StringUtils.isNotBlank( strMessage ) ? strMessage : StringUtils.EMPTY );
        crmItem.putParameter( AbstractCRMItem.NOTIFICATION_SENDER,
            StringUtils.isNotBlank( strSender ) ? strSender : StringUtils.EMPTY );

        _crmClientQueue.send( crmItem );
    }

    /**
     * Update a demand
     * @param strIdDemand the id demand
     * @param strStatusText the status text
     */
    public void sendUpdateDemand( String strIdDemand, String strStatusText )
    {
        ICRMItem crmItem = new DemandItem(  );
        crmItem.putParameter( AbstractCRMItem.ID_DEMAND,
            StringUtils.isNotBlank( strIdDemand ) ? strIdDemand : StringUtils.EMPTY );
        crmItem.putParameter( AbstractCRMItem.STATUS_TEXT,
            StringUtils.isNotBlank( strStatusText ) ? strStatusText : StringUtils.EMPTY );

        _crmClientQueue.send( crmItem );
    }

    /**
     * Calls WS to notify/update a demand
     * @param crmItem the crm item
     * @throws HttpAccessException exception if connexion problem
     * @throws CRMClientException exception if application not well configured
     */
    public void doProcessWS( ICRMItem crmItem ) throws HttpAccessException, CRMClientException
    {
        _crmClientWebService.doProcess( crmItem );
    }
}
