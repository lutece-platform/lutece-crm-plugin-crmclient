package fr.paris.lutece.plugins.crmclient.business.user;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* UserItemGuidByIdCRMUser
*
*/
public class UserItemGuidByIdCRMUser  extends UserItem
{
	private static final long serialVersionUID = 1781761996546052287L;
	private static final String PROPERTY_WS_USER_GUID = "crmclient.crm.rest.user.userGuidByIdCRMUser";
	   

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlForWS(  )
    {
    	String strIdCrmUser=this.getParameters().get(ID_CRM_USER);
      	StringBuffer strUrlForWS=new StringBuffer();
    	strUrlForWS.append( getCRMWebAppBaseURL( ) );
    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_REST_USER_BASE_URL ) );
    	strUrlForWS.append( strIdCrmUser);
    	strUrlForWS.append( "/");
    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_USER_GUID ) );
    	
    	
    	return strUrlForWS.toString();
    }

    
    
    


}
