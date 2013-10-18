package fr.paris.lutece.plugins.crmclient.business.demand;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* DemandItemUserGuid
*
*/
public class DemandItemUserGuid extends DemandItem{
	
		private static final long serialVersionUID = 1781761996546052287L;	
		private static final String PROPERTY_WS_CRM_DEMAND_USER_GUID = "crmclient.crm.rest.demand.userGuid";
	   

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public String getUrlForWS(  )
	    {
	    	
	    	String strIdDemand=this.getParameters().get(ID_DEMAND);
	    	StringBuffer strUrlForWS=new StringBuffer();
	    	strUrlForWS.append( getCRMWebAppBaseURL( ) );
	    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_REST_DEMAND_BASE_URL ) );
	    	strUrlForWS.append( strIdDemand);
	    	strUrlForWS.append("/");
	    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_DEMAND_USER_GUID ));
	    	
	    	return strUrlForWS.toString();
	    }
	
	

}
