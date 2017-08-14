package br.com.intmed.interage.api;

import java.net.MalformedURLException;
import java.net.URL;

public class APIConsts {
    public static String VERSION = "v1";
    public static String PROTOCOL = "https";
    public static String HOST = "api.interage.intmed.com.br";

    public enum APIEndpoint {
        MEDICAMENTOS("medicamentos"),
        PRINCIPIOS_ATIVOS("principios-ativos"),
        INTERACOES("interacoes");

        private final String text;

        APIEndpoint(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }

    public static String getResourceUrl(APIEndpoint endpoint) {
        return getFullUrl(String.format("/%s/%s", VERSION, endpoint.toString()));
    }

    public static String getFullUrl(String path) {
        try {
            return new URL(PROTOCOL, HOST, path).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
