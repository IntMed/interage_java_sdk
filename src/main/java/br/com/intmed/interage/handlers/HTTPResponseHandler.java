package br.com.intmed.interage.handlers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.client.HttpResponseException;
import org.json.JSONException;

public class HTTPResponseHandler {
    public static void handleResponseError(HttpResponse<JsonNode> response) throws HttpResponseException {
        String errorMessage = null;
        try {
            errorMessage = response.getBody().getObject().getString("detail");
        } catch (JSONException e) {
            errorMessage = "";
        } finally {
            handleResponseError(response, errorMessage);
        }
    }

    public static void handleResponseError(HttpResponse response, String errorMessage) throws HttpResponseException {
        if (hasError(response))
            throw new HttpResponseException(response.getStatus(), errorMessage);
    }

    public static boolean hasError(HttpResponse response) {
        return !String.valueOf(response.getStatus()).startsWith("2");
    }
}
