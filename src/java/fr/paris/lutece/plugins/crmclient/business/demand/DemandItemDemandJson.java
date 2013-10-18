package fr.paris.lutece.plugins.crmclient.business.demand;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* DemandItemDemandJson
*
*/
public class DemandItemDemandJson extends DemandItem{
	
		private static final long serialVersionUID = 1781761996546052287L;
		private static final String PROPERTY_WS_CRM_DEMAND_JSON = "crmclient.crm.rest.demand.demandJson";
	   

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public String getUrlForWS(  )
	    {
	        return getCRMWebAppBaseURL(  ) + AppPropertiesService.getProperty( PROPERTY_WS_CRM_DEMAND_JSON );
	    }
	
	

}
