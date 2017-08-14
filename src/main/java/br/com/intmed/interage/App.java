package br.com.intmed.interage;

import br.com.intmed.interage.api.InterageAPI;
import br.com.intmed.interage.resources.Interacao;
import br.com.intmed.interage.resources.Medicamento;
import br.com.intmed.interage.resources.PrincipioAtivo;
import br.com.intmed.interage.results.MedicamentoResults;
import br.com.intmed.interage.results.PrincipioAtivoResults;

import org.apache.http.client.HttpResponseException;

import java.util.HashMap;
import java.util.Map;


public class App
{
    public static void main( String[] args )
    {
        // Registrando ObjectMapper para serialização dos recursos (necessário apenas uma vez)
        InterageAPI.registerObjectMapper();

        try {
            String username = "your-username";
            String password = "your-password";
            InterageAPI client = new InterageAPI(username, password);

            // Recuperando recursos pelos seus identificadores
            PrincipioAtivo principioAtivo = client.getPrincipioAtivoManager().get(1);
            Medicamento medicamento = client.getMedicamentoManager().get(34);
            Interacao interacao = client.getInteracaoManager().get(1025);

            // Recuperando lista de recursos
            PrincipioAtivoResults principioAtivoResult = client.getPrincipioAtivoManager().list();
            MedicamentoResults medicamentoResult = client.getMedicamentoManager().list();

            // Filtrando medicamentos
            Map<String, String> params = new HashMap<String, String>();
            params.put("search", "paracetamol");
            MedicamentoResults filtrado = client.getMedicamentoManager().list(params);
        } catch (HttpResponseException e) {
            e.printStackTrace();
        }
    }
}
