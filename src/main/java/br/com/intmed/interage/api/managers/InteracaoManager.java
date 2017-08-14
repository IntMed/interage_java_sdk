package br.com.intmed.interage.api.managers;

import br.com.intmed.interage.api.APIConsts.APIEndpoint;
import br.com.intmed.interage.api.InterageAPI;
import br.com.intmed.interage.resources.Interacao;
import br.com.intmed.interage.results.InteracaoResults;

public class InteracaoManager extends APIManager<Interacao, InteracaoResults> {
    public InteracaoManager(InterageAPI client) {
        super(client);
    }

    public APIEndpoint getResourceEndpoint() {
        return APIEndpoint.INTERACOES;
    }

    public Class<Interacao> getResourceType() {
        return Interacao.class;
    }

    public Class<InteracaoResults> getResultType() {
        return InteracaoResults.class;
    }
}
