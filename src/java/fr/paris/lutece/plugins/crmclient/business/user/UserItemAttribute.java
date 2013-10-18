package fr.paris.lutece.plugins.crmclient.business.user;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* UserItemAttribute
*
*/
public class UserItemAttribute  extends UserItem
{
	private static final long serialVersionUID = 1781761996546052287L;   

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlForWS(  )
    {
      	String strUserGuid=this.getParameters().get(USER_GUID);
      	String strAttribute=this.getParameters().get(USER_ATTRIBUTE);
    	StringBuffer strUrlForWS=new StringBuffer();
    	strUrlForWS.append( getCRMWebAppBaseURL( ) );
    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_REST_USER_BASE_URL ) );
    	strUrlForWS.append( strUserGuid);
    	strUrlForWS.append("/");
    	strUrlForWS.append( strAttribute);
    	return strUrlForWS.toString();
    }

    
    
    


}
