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

import fr.paris.lutece.portal.service.plugin.Plugin;


/**
 *
 * This class provides Data Access methods for CRMItemQueue objects
 *
 */
public interface ICRMItemQueueDAO
{
    /**
     * Insert a new crm item in the table.
     * @param crmItemQueue the crm item queue
     * @param plugin {@link Plugin}
     */
    void insert( CRMItemQueue crmItemQueue, Plugin plugin );

    /**
     * return the first crm item  in the table
     * @param nIdCRMItemQueue the id of the crm item
     * @param plugin {@link Plugin}
     * @return the first crm item in the table
     */
    CRMItemQueue load( int nIdCRMItemQueue, Plugin plugin );

    /**
     * Delete the crm item record in the table
     * @param nIdCRMItemQueue The indentifier of the crm item to remove
     * @param plugin {@link Plugin}
     */
    void delete( int nIdCRMItemQueue, Plugin plugin );

    /**
     * Get the number of crm items
     * @param plugin {@link Plugin}
     * @return the number of crm item present in the database
     */
    int getCountCRMItem( Plugin plugin );

    /**
     * Return the next crm item queue id
     * @param plugin {@link Plugin}
     * @return the next mail item queue id
     */
    int nextCRMItemQueueId( Plugin plugin );

    /**
     * Lock the mail item
     * @param nIdCRMItemQueue the id of the crm item to lock
     * @param plugin {@link Plugin}
     */
    void lockCRMItemQueue( int nIdCRMItemQueue, Plugin plugin );
}
