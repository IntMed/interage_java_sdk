package br.com.intmed.interage.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Interacao extends APIResource {
    public enum Gravidade {
        @JsonProperty("Grave")
        GRAVE("Grave"),
        @JsonProperty("Nada esperado")
        NADA_ESPERADO("Nada esperado"),
        @JsonProperty("Leve")
        LEVE("Leve"),
        @JsonProperty("Moderada")
        MODERADA("Moderada"),
        @JsonProperty("Gravidade desconhecida")
        GRAVIDADE_DESCONHECIDA("Gravidade desconhecida");

        private final String value;

        Gravidade(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Evidencia {
        @JsonProperty("Teórica")
        TEORICA("Teórica"),
        @JsonProperty("Extensa")
        EXTENSA("Extensa"),
        @JsonProperty("Caso")
        CASO("Caso"),
        @JsonProperty("Estudo")
        ESTUDO("Estudo");

        private final String value;

        Evidencia(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Acao {
        @JsonProperty("Ajustar")
        AJUSTAR("Ajustar"),
        @JsonProperty("Monitorar")
        MONITORAR("Monitorar"),
        @JsonProperty("Informar")
        INFORMAR("Informar"),
        @JsonProperty("Nenhuma")
        NENHUMA("Nenhuma"),
        @JsonProperty("Evitar")
        EVITAR("Evitar");

        private final String value;

        Acao(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    private String nome;
    private Gravidade gravidade;
    private Evidencia evidencia;
    private Acao acao;
    private String explicacao;
    private List<PrincipioAtivo> principioAtivos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Gravidade getGravidade() {
        return gravidade;
    }

    public void setGravidade(Gravidade gravidade) {
        this.gravidade = gravidade;
    }

    public Evidencia getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    @JsonProperty("principios_ativos")
    public List<PrincipioAtivo> getPrincipioAtivos() {
        return principioAtivos;
    }

    public void setPrincipioAtivos(List<PrincipioAtivo> principioAtivos) {
        this.principioAtivos = principioAtivos;
    }
}
