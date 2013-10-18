package fr.paris.lutece.plugins.crmclient.service.authenticator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.util.signrequest.AbstractAuthenticator;
import fr.paris.lutece.util.signrequest.RequestAuthenticator;

public class AuthenticatorService implements IAuthenticatorService {

	private static final String DEFAULT_AUTHENTICATOR_CODE ="default";
	private  Map<String, RequestAuthenticator> _mapRequestAuthenticatorForWs; 
	private  Map<String, AbstractAuthenticator> _mapRequestAuthenticatorForUrl;
	
	/**
     * {@inheritDoc}
     */
    @Override
	public RequestAuthenticator getRequestAuthenticatorForWs(String strCrmWebbAppCode )
	{
		
		if(StringUtils.isEmpty(strCrmWebbAppCode))
		{
			return _mapRequestAuthenticatorForWs.get(DEFAULT_AUTHENTICATOR_CODE);
		}
		
		return _mapRequestAuthenticatorForWs.containsKey(strCrmWebbAppCode)?_mapRequestAuthenticatorForWs.get(strCrmWebbAppCode):_mapRequestAuthenticatorForWs.get(DEFAULT_AUTHENTICATOR_CODE);
	
			
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractAuthenticator getRequestAuthenticatorForUrl(String strCrmWebbAppCode  )
    {
    	if(StringUtils.isEmpty(strCrmWebbAppCode))
		{
			return _mapRequestAuthenticatorForUrl.get(DEFAULT_AUTHENTICATOR_CODE);
		}
		
    	return _mapRequestAuthenticatorForUrl.containsKey(strCrmWebbAppCode)?_mapRequestAuthenticatorForUrl.get(strCrmWebbAppCode):_mapRequestAuthenticatorForUrl.get(DEFAULT_AUTHENTICATOR_CODE);
    	
    }


	/**
	 * setMapRequestAuthenticatorWs
	 * @param mapRequestAuthenticator mapRequestAuthenticator
	 */
    public void setMapRequestAuthenticatorForWs(
			Map<String, RequestAuthenticator> mapRequestAuthenticator) {
		this._mapRequestAuthenticatorForWs = mapRequestAuthenticator;
	}
	
    /**
	 * setMapRequestAuthenticatorUrl
	 * @param mapRequestAuthenticatorForUrl mapRequestAuthenticatorForUrl
	 */
    public void setMapRequestAuthenticatorForUrl(
			Map<String, AbstractAuthenticator> mapRequestAuthenticatorForUrl) {
		this._mapRequestAuthenticatorForUrl = mapRequestAuthenticatorForUrl;
	}
	
}
