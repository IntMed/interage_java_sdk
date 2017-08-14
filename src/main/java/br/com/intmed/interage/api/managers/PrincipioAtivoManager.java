package br.com.intmed.interage.api.managers;

import br.com.intmed.interage.api.APIConsts.APIEndpoint;
import br.com.intmed.interage.api.InterageAPI;
import br.com.intmed.interage.resources.PrincipioAtivo;
import br.com.intmed.interage.results.PrincipioAtivoResults;

public class PrincipioAtivoManager extends APIManager<PrincipioAtivo, PrincipioAtivoResults>{

    public PrincipioAtivoManager(InterageAPI client) {
        super(client);
    }

    public APIEndpoint getResourceEndpoint() {
        return APIEndpoint.PRINCIPIOS_ATIVOS;
    }

    public Class<PrincipioAtivo> getResourceType() {
        return PrincipioAtivo.class;
    }

    public Class<PrincipioAtivoResults> getResultType() {
        return PrincipioAtivoResults.class;
    }
}
