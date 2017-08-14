package br.com.intmed.interage.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Medicamento extends APIResource {
    private String nome;
    private List<String> principiosAtivosAnvisa;
    private List<PrincipioAtivo> principioAtivos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("principios_ativos_anvisa")
    public List<String> getPrincipiosAtivosAnvisa() {
        return principiosAtivosAnvisa;
    }

    public void setPrincipiosAtivosAnvisa(List<String> principiosAtivosAnvisa) {
        this.principiosAtivosAnvisa = principiosAtivosAnvisa;
    }

    @JsonProperty("principios_ativos")
    public List<PrincipioAtivo> getPrincipioAtivos() {
        return principioAtivos;
    }

    public void setPrincipioAtivos(List<PrincipioAtivo> principioAtivos) {
        this.principioAtivos = principioAtivos;
    }
}
