package fr.paris.lutece.plugins.crmclient.business.demand;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* DemandItemUpdate
*
*/
public class DemandItemUpdate extends DemandItem{
	
		private static final long serialVersionUID = 1781761996546052287L;
		private static final String PROPERTY_WS_CRM_DEMAND_UPDATE_URL = "crmclient.crm.rest.demand.update.url";
	   

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public String getUrlForWS(  )
	    {
	    	StringBuffer strUrlForWS=new StringBuffer();
	    	strUrlForWS.append( getCRMWebAppBaseURL( ) );
	    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_REST_DEMAND_BASE_URL ) );
	    	strUrlForWS.append( AppPropertiesService.getProperty( PROPERTY_WS_CRM_DEMAND_UPDATE_URL ));
	    	
	    	return strUrlForWS.toString();
	    }
	
	

}
