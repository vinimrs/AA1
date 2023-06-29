package br.ufscar.dc.dsw.domain;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Locacao {
    private Long id;

    private LocalTime hora;
    private LocalDate data;
    private Cliente cliente;
    private Locadora locadora;

    public Locacao(Long id, LocalTime hora, LocalDate data, Cliente cliente, Locadora locadora) {
        this.id = id;
        this.hora = hora;
        this.data = data;
        this.cliente = cliente;
        this.locadora = locadora;
    }

    public Locacao(LocalTime hora, LocalDate data, Cliente cliente, Locadora locadora) {
        super();
        this.hora = hora;
        this.data = data;
        this.cliente = cliente;
        this.locadora = locadora;
    }

    public Locacao(Long id, LocalTime hora, LocalDate data, Locadora locadora) {
        this.id = id;
        this.hora = hora;
        this.data = data;
        this.locadora = locadora;
    }

    public Locacao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }
}

