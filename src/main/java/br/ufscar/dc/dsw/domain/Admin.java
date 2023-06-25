package br.ufscar.dc.dsw.domain;

public class Admin implements Usuario {
    private Long id;
    private String nome;
    private String login;
    private String senha;

    public Admin(String nome, String login, String senha) {
        super();
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Admin(Long id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Admin(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getPapel() {
        return "ADMIN";
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public Long getId() {
        return this.id;
    }


}
