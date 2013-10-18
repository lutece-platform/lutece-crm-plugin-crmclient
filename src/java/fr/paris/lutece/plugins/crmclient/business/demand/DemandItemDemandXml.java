package fr.paris.lutece.plugins.crmclient.business.demand;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
*
* DemandItemDemandXml
*
*/
public class DemandItemDemandXml extends DemandItem{
	
		private static final long serialVersionUID = 1781761996546052287L;
		private static final String PROPERTY_WS_CRM_DEMAND_XML = "crmclient.crm.rest.demand.demandXml";
	   

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public String getUrlForWS(  )
	    {
	        return getCRMWebAppBaseURL(  ) + AppPropertiesService.getProperty( PROPERTY_WS_CRM_DEMAND_XML );
	    }
	
	

}
