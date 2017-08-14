package br.com.intmed.interage.api.managers;

import br.com.intmed.interage.api.APIConsts.APIEndpoint;
import br.com.intmed.interage.api.InterageAPI;
import br.com.intmed.interage.resources.Medicamento;
import br.com.intmed.interage.results.MedicamentoResults;

public class MedicamentoManager extends APIManager<Medicamento, MedicamentoResults> {
    public MedicamentoManager(InterageAPI client) {
        super(client);
    }

    public APIEndpoint getResourceEndpoint() {
        return APIEndpoint.MEDICAMENTOS;
    }

    public Class<Medicamento> getResourceType() {
        return Medicamento.class;
    }

    public Class<MedicamentoResults> getResultType() {
        return MedicamentoResults.class;
    }
}
