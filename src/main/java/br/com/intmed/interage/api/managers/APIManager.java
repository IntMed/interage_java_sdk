package br.com.intmed.interage.api.managers;


import br.com.intmed.interage.api.APIConsts;
import br.com.intmed.interage.api.APIConsts.APIEndpoint;
import br.com.intmed.interage.api.InterageAPI;
import br.com.intmed.interage.handlers.HTTPResponseHandler;
import br.com.intmed.interage.resources.APIResource;
import br.com.intmed.interage.results.APIResults;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.apache.http.client.HttpResponseException;


import java.util.HashMap;
import java.util.Map;

public abstract class APIManager<TResource extends APIResource, TResult extends APIResults> {
    private InterageAPI client;

    public APIManager(InterageAPI client) {
        this.client = client;
    }

    public InterageAPI getClient() {
        return client;
    }

    public TResource get(int id) throws HttpResponseException {
        try {

            GetRequest request = this.client
                    .get(APIConsts.getResourceUrl(this.getResourceEndpoint()) + "/{id}/")
                    .routeParam("id", String.valueOf(id));
            HTTPResponseHandler.handleResponseError(request.asJson());
            return request.asObject(getResourceType()).getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TResult list() throws HttpResponseException {
        return list(new HashMap<String, String>());
    }

    public TResult list(Map<String, String> data) throws HttpResponseException {
        try {
            GetRequest request = this.getListRequest(data);
            HTTPResponseHandler.handleResponseError(request.asJson());
            return request.asObject(this.getResultType()).getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected GetRequest getListRequest(Map<String, String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append(APIConsts.getResourceUrl(this.getResourceEndpoint()));

        int count = 1;
        int size = data.size();

        if(size > 0) {
            builder.append("?");
        }

        for(String param : data.keySet()) {
            builder.append(String.format("%s={%s}", param, param));
            if(count < size ) {
                builder.append("&");
            }
            count++;
        }

        GetRequest request = this.client.get(builder.toString());

        for(Map.Entry<String, String> entry : data.entrySet()) {
            request = request.routeParam(entry.getKey(), String.valueOf(entry.getValue()));
        }

        return request;
    }

    public abstract APIEndpoint getResourceEndpoint();
    public abstract Class<TResource> getResourceType();
    public abstract Class<TResult> getResultType();
}
