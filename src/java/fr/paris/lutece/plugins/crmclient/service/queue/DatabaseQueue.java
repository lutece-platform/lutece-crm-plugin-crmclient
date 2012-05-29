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
package fr.paris.lutece.plugins.crmclient.service.queue;

import fr.paris.lutece.plugins.crmclient.business.CRMItemQueue;
import fr.paris.lutece.plugins.crmclient.business.ICRMItem;
import fr.paris.lutece.plugins.crmclient.business.ICRMItemQueueDAO;
import fr.paris.lutece.plugins.crmclient.service.CRMClientPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import javax.inject.Inject;


/**
 *
 * DatabaseQueue
 *
 */
public class DatabaseQueue implements ICRMClientQueue
{
    public static final String BEAN_SERVICE = "crmclient.databaseQueue";
    @Inject
    private ICRMItemQueueDAO _crmItemQueueDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void send( ICRMItem crmItem )
    {
        CRMItemQueue crmQueue = new CRMItemQueue(  );
        crmQueue.setCRMItem( crmItem );
        _crmItemQueueDAO.insert( crmQueue, getPlugin(  ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized ICRMItem consume(  )
    {
        CRMItemQueue crmItemQueue = this.getNextCRMItemQueue(  );

        if ( crmItemQueue != null )
        {
            _crmItemQueueDAO.delete( crmItemQueue.getIdCRMItemQueue(  ), getPlugin(  ) );

            return crmItemQueue.getCRMItem(  );
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size(  )
    {
        return _crmItemQueueDAO.getCountCRMItem( getPlugin(  ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CRMItemQueue getNextCRMItemQueue(  )
    {
        // Get the id of the next mail item queue
        int nIdCRMItemQueue = _crmItemQueueDAO.nextCRMItemQueueId( getPlugin(  ) );

        if ( nIdCRMItemQueue != -1 )
        {
            // Lock the mail item queue before getting notificationItemQueue Object
            _crmItemQueueDAO.lockCRMItemQueue( nIdCRMItemQueue, getPlugin(  ) );

            return _crmItemQueueDAO.load( nIdCRMItemQueue, getPlugin(  ) );
        }

        return null;
    }

    /**
     * Get the plugin
     * @return the plugin
     */
    private Plugin getPlugin(  )
    {
        return PluginService.getPlugin( CRMClientPlugin.PLUGIN_NAME );
    }
}
