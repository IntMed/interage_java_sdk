package br.com.intmed.interage.api;

import br.com.intmed.interage.api.managers.InteracaoManager;
import br.com.intmed.interage.api.managers.MedicamentoManager;
import br.com.intmed.interage.api.managers.PrincipioAtivoManager;
import br.com.intmed.interage.handlers.HTTPResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.apache.http.client.HttpResponseException;

import java.io.IOException;


public class InterageAPI {
    private String token;
    private PrincipioAtivoManager principioAtivoManager;
    private MedicamentoManager medicamentoManager;
    private InteracaoManager interacaoManager;

    public InterageAPI(String token) throws HttpResponseException {
        this.validateApiToken(token);
        this.token = token;
        this.initManagers();
    }

    public InterageAPI(String username, String password) throws HttpResponseException {
        this.token = getApiToken(username, password);
        this.initManagers();
    }

    public GetRequest get(String url) {
        return get(url, this.token);
    }

    public static void registerObjectMapper() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public PrincipioAtivoManager getPrincipioAtivoManager() {
        return principioAtivoManager;
    }

    public MedicamentoManager getMedicamentoManager() {
        return medicamentoManager;
    }

    public InteracaoManager getInteracaoManager() {
        return interacaoManager;
    }

    private GetRequest get(String url, String token) {
        return Unirest.get(url)
                .header("accept", "application/json")
                .header("Authorization", "Token " + token);
    }

    private void initManagers() {
        this.principioAtivoManager = new PrincipioAtivoManager(this);
        this.medicamentoManager = new MedicamentoManager(this);
        this.interacaoManager = new InteracaoManager(this);
    }

    private String getApiToken(String username, String password) throws HttpResponseException {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(APIConsts.getFullUrl("/obter-chave/"))
                    .header("accept", "application/json")
                    .field("username", username)
                    .field("password", password)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if(response != null) {
            if(response.getStatus() == 200) {
                return response.getBody().getObject().getString("token");
            }
            if(response.getStatus() == 400) {
                String errorMessage = response.getBody().getObject().getJSONArray("non_field_errors").getString(0);
                HTTPResponseHandler.handleResponseError(response, errorMessage);
            } else {
                HTTPResponseHandler.handleResponseError(response);
            }
        }

        return null;
    }

    private void validateApiToken(String token) throws HttpResponseException {
        try {
            HttpResponse<JsonNode> response = get(APIConsts.getFullUrl("/v1/"), token).asJson();
            HTTPResponseHandler.handleResponseError(response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
