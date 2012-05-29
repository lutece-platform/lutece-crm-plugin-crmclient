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

import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;


/**
 *
 * AbstractCRMClientService
 *
 */
public abstract class AbstractCRMClientService implements ICRMClientService
{
    @Inject
    private ICRMClientQueue _crmClientQueue;

    /**
         * {@inheritDoc}
         */
    @Override
    public ICRMClientQueue getQueue(  )
    {
        return _crmClientQueue;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void notify( String strIdDemand, String strObject, String strMessage, String strSender )
    {
        notify( strIdDemand, strObject, strMessage, strSender, StringUtils.EMPTY );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notify( String strIdDemand, String strObject, String strMessage, String strSender,
        String strCRMWebAppBaseURL )
    {
        ICRMItem crmItem = new NotificationItem(  );

        if ( StringUtils.isNotBlank( strCRMWebAppBaseURL ) )
        {
            crmItem.setCRMWebAppBaseURL( strCRMWebAppBaseURL );
        }

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
     * {@inheritDoc}
     */
    @Override
    public void sendUpdateDemand( String strIdDemand, String strStatusText )
    {
        sendUpdateDemand( strIdDemand, strStatusText, StringUtils.EMPTY );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUpdateDemand( String strIdDemand, String strStatusText, String strCRMWebAppBaseURL )
    {
        ICRMItem crmItem = new DemandItem(  );

        if ( StringUtils.isNotBlank( strCRMWebAppBaseURL ) )
        {
            crmItem.setCRMWebAppBaseURL( strCRMWebAppBaseURL );
        }

        crmItem.putParameter( AbstractCRMItem.ID_DEMAND,
            StringUtils.isNotBlank( strIdDemand ) ? strIdDemand : StringUtils.EMPTY );
        crmItem.putParameter( AbstractCRMItem.STATUS_TEXT,
            StringUtils.isNotBlank( strStatusText ) ? strStatusText : StringUtils.EMPTY );

        _crmClientQueue.send( crmItem );
    }
}
