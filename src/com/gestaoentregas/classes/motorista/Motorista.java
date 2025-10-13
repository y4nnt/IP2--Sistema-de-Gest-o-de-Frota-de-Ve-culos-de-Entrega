package com.gestaoentregas.classes.motorista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Motorista {
    private String nomeMotorista;
    private String telefoneMotorista;
    private String cpfMotorista;
    private String cnhMotorista;
    private DisponibilidadeMotorista disponibilidadeMotorista;
    // private List<Rota> rotasMotorista; // Tirar o comentário quando Rota existir
    // private List<Entrega> historicoEntregaMotorista; // Tirar o comentário quando Entrega existir
    private List<PeriodoIndisponivel> feriasMotorista;

    public Motorista(String nomeMotorista, String telefoneMotorista, String cpfMotorista, String cnhMotorista) {
        if(nomeMotorista == null || telefoneMotorista == null || cpfMotorista == null || cnhMotorista == null){
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos!");
        }

        this.nomeMotorista = nomeMotorista;
        this.telefoneMotorista = telefoneMotorista;
        this.cpfMotorista = cpfMotorista;
        this.cnhMotorista = cnhMotorista;
        this.disponibilidadeMotorista = DisponibilidadeMotorista.INDISPONIVEL;
        //this.rotasMotorista = new ArrayList<>();
        //this.historicoEntregaMotorista = new ArrayList<>();
        this.feriasMotorista = new ArrayList<>();
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getTelefoneMotorista() {
        return telefoneMotorista;
    }

    public void setTelefoneMotorista(String telefoneMotorista) {
        this.telefoneMotorista = telefoneMotorista;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getCnhMotorista() {
        return cnhMotorista;
    }

    public void setCnhMotorista(String cnhMotorista) {
        this.cnhMotorista = cnhMotorista;
    }

    public DisponibilidadeMotorista getDisponibilidadeMotorista() {
        return disponibilidadeMotorista;
    }

    public List<PeriodoIndisponivel> getFeriasMotorista() {
        return feriasMotorista;
    }

    /*public void addRotaMotorista(Rota rota){
        this.rotasMotorista.add(rota);
    }*/

    /*public void addEntregaMotorista (Entrega entrega){
        this.historicoEntregaMotorista.add(entrega);
    }*/

    public void addFerias(LocalDate inicio, LocalDate fim){
        PeriodoIndisponivel ferias = new PeriodoIndisponivel(inicio, fim, "Férias");
        this.feriasMotorista.add(ferias);
    }

    // Usa o metodo contem do PeriodoIndisponivel para saber
    // se em alguma das ferias do motorista, existe a data
    // procurada no metodo estaDisponivel
    public boolean estaDisponivel(LocalDate data){
        for(PeriodoIndisponivel ferias : this.feriasMotorista){
            if(ferias.contem(data)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        // Informações principais
        sb.append(String.format("| %-15s | %-30s %n", "Nome", this.nomeMotorista));
        sb.append(String.format("| %-15s | %-30s %n", "CPF", this.cpfMotorista));
        sb.append(String.format("| %-15s | %-30s %n", "CNH", this.cnhMotorista));
        sb.append(String.format("| %-15s | %-30s %n", "Telefone", this.telefoneMotorista));

        // Informações de lista

        for(PeriodoIndisponivel ferias : this.feriasMotorista){
            sb.append(String.format("| %-15s | %d/%d/%d até %d/%d/%d", "Férias",
                    ferias.getDataInicio().getDayOfMonth(),
                    ferias.getDataInicio().getMonthValue(),
                    ferias.getDataInicio().getYear(),
                    ferias.getDataFim().getDayOfMonth(),
                    ferias.getDataFim().getMonthValue(),
                    ferias.getDataFim().getYear()));
        }

        return sb.toString();
    }

}
