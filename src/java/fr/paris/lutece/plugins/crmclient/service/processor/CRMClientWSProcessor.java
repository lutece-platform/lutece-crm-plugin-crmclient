/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.crmclient.service.processor;

import fr.paris.lutece.plugins.crmclient.business.ICRMItem;
import fr.paris.lutece.plugins.crmclient.service.authenticator.AuthenticatorService;
import fr.paris.lutece.plugins.crmclient.util.CRMException;
import fr.paris.lutece.plugins.crmclient.util.http.HttpMethodEnum;
import fr.paris.lutece.plugins.crmclient.util.http.IWebServiceCaller;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * CRMClientWSProcessor
 *
 */
public class CRMClientWSProcessor implements ICRMClientProcessor, InitializingBean
{
    private List<String> _listSignatureElements;
    @Inject
    @Named( "crmclient.webServiceCaller" )
    private IWebServiceCaller _webServiceCaller;
    @Inject
    @Named( "crmclient.requestAuthenticatorService" )
    private AuthenticatorService _authenticatorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String doProcess( ICRMItem crmItem ) throws CRMException
    {
        // List elements to include to the signature
        List<String> listElements = buildListElements( crmItem );

        return _webServiceCaller.callWebService( crmItem.getUrlForWS(  ), crmItem.getParameters(  ),
            _authenticatorService.getRequestAuthenticatorForWs( crmItem.getCRMWebAppCode(  ) ), listElements,
            HttpMethodEnum.POST );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProcess( ICRMItem crmItem ) throws CRMException
    {
        // List elements to include to the signature
        List<String> listElements = buildListElements( crmItem );

        return _webServiceCaller.callWebService( crmItem.getUrlForWS(  ), crmItem.getParameters(  ),
            _authenticatorService.getRequestAuthenticatorForWs( crmItem.getCRMWebAppCode(  ) ), listElements,
            HttpMethodEnum.GET );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet(  ) throws Exception
    {
        Assert.notNull( _webServiceCaller, "The WebService Caller must be set." );
        Assert.notNull( _listSignatureElements, "The list of signature elements must be set." );
    }

    /**
     * Set the signature elements
     * @param listSignatureElements the signature elements
     */
    public void setSignatureElements( List<String> listSignatureElements )
    {
        _listSignatureElements = listSignatureElements;
    }

    /**
     * Set the web service caller
     * @param webServiceCaller the web service caller
     */
    public void setWebServiceCaller( IWebServiceCaller webServiceCaller )
    {
        _webServiceCaller = webServiceCaller;
    }

    /**
     * Build the list of elements to put on the request authenticator
     * @param crmItem the crm item
     * @return the list of elements
     */
    private List<String> buildListElements( ICRMItem crmItem )
    {
        List<String> listElements = new ArrayList<String>(  );

        for ( Entry<String, String> parameter : crmItem.getParameters(  ).entrySet(  ) )
        {
            if ( _listSignatureElements.contains( parameter.getKey(  ) ) )
            {
                listElements.add( parameter.getValue(  ) );
            }
        }

        return listElements;
    }
}
