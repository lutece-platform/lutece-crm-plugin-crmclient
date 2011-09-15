/*
 * Copyright (c) 2002-2011, Mairie de Paris
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

import fr.paris.lutece.plugins.crmclient.service.CRMClientPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;


/**
 *
 * This class provides Data Access methods for CRMItemQueue objects
 *
 */
public final class CRMItemQueueHome
{
    private static final String BEAN_CRM_ITEM_QUEUE_DAO = "crmclient.crmItemQueueDAO";
    private static Plugin _plugin = PluginService.getPlugin( CRMClientPlugin.PLUGIN_NAME );
    private static ICRMItemQueueDAO _dao = (ICRMItemQueueDAO) SpringContextService.getPluginBean( CRMClientPlugin.PLUGIN_NAME,
            BEAN_CRM_ITEM_QUEUE_DAO );

    /**
     * Private constructor
     */
    private CRMItemQueueHome(  )
    {
    }

    /**
     * Insert a new crm item in the database queue.
     * @param crmItemQueue the mail item to insert
     */
    public static void create( CRMItemQueue crmItemQueue )
    {
        _dao.insert( crmItemQueue, _plugin );
    }

    /**
     * Delete the crm item record in the table
     * @param nIdCRMItemQueue The indentifier of the mail item to remove
     */
    public static void delete( int nIdCRMItemQueue )
    {
        _dao.delete( nIdCRMItemQueue, _plugin );
    }

    /**
     * Return the first crm item in the queue
     * @return the first crm item in the queue
     */
    public static CRMItemQueue getNextCRMItemQueue(  )
    {
        //get the id of the next mail item queue
        int nIdCRMItemQueue = _dao.nextCRMItemQueueId( _plugin );

        if ( nIdCRMItemQueue != -1 )
        {
            //lock the mail item queue before getting notificationItemQueue Object
            _dao.lockCRMItemQueue( nIdCRMItemQueue, _plugin );

            return _dao.load( nIdCRMItemQueue, _plugin );
        }

        return null;
    }

    /**
     * Get the number of crm itemss
     * @return the number of crm items present in the queue
     */
    public static int getCRMItemNumber(  )
    {
        return _dao.getCountCRMItem( _plugin );
    }
}
