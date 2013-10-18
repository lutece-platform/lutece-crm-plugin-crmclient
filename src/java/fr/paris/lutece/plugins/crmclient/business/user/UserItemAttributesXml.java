package fr.paris.lutece.plugins.crmclient.business.user;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* UserItemAttributesXml
*
*/
public class UserItemAttributesXml  extends UserItem
{
	   
	private static final long serialVersionUID = 1781761996546052287L;
    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlForWS(  )
    {
    	String strUserGuid=this.getParameters().get(USER_GUID);
      	StringBuffer strUrlForWS=new StringBuffer();
    	strUrlForWS.append( getCRMWebAppBaseURL( ) );
    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_REST_USER_BASE_URL ) );
    	strUrlForWS.append( strUserGuid);
    	return strUrlForWS.toString();
    }

    
    
    


}
