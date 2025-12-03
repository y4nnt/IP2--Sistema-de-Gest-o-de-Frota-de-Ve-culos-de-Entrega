package com.gestaoentregas.dados.beans.motorista;

import com.gestaoentregas.dados.beans.veiculo.Veiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Motorista {
    private String nomeMotorista;
    private String telefoneMotorista;
    private String cpfMotorista;
    private String cnhMotorista;
    private int idadeMotorista;
    private DisponibilidadeMotorista disponibilidadeMotorista;
    private List<PeriodoIndisponivelMotorista> feriasMotorista;
    private int idMotorista;
    private Veiculo veiculoMotorista;

    public Motorista(String nomeMotorista, String telefoneMotorista, String cpfMotorista, String cnhMotorista, int idadeMotorista) {
        this.nomeMotorista = nomeMotorista;
        this.telefoneMotorista = telefoneMotorista;
        this.cpfMotorista = cpfMotorista;
        this.cnhMotorista = cnhMotorista;
        this.idadeMotorista = idadeMotorista;
        this.disponibilidadeMotorista = DisponibilidadeMotorista.INDISPONIVEL;
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

    private void setCpfMotorista(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getCnhMotorista() {
        return cnhMotorista;
    }

    public void setCnhMotorista(String cnhMotorista) {
        this.cnhMotorista = cnhMotorista;
    }

    public int getIdadeMotorista() {
        return idadeMotorista;
    }

    public void setIdadeMotorista(int idadeMotorista) {
        this.idadeMotorista = idadeMotorista;
    }

    public DisponibilidadeMotorista getDisponibilidadeMotorista() {
        return disponibilidadeMotorista;
    }

    public List<PeriodoIndisponivelMotorista> getFeriasMotorista() {
        return feriasMotorista;
    }

    public int getIdMotorista() {return idMotorista;}

    public void setIdMotorista(int idMotorista) {this.idMotorista = idMotorista;}

    public Veiculo getVeiculoMotorista() {
        return veiculoMotorista;
    }

    public void setVeiculoMotorista(Veiculo veiculoMotorista) {
        this.veiculoMotorista = veiculoMotorista;
    }

    public void addFerias(LocalDate inicio, LocalDate fim){
        PeriodoIndisponivelMotorista ferias = new PeriodoIndisponivelMotorista(inicio, fim, "Férias");
        this.feriasMotorista.add(ferias);
    }

    // Usa o metodo contem do PeriodoIndisponivel para saber
    // se em alguma das ferias do motorista, existe a data
    // procurada no metodo estaDisponivel
    public boolean estaDisponivel(LocalDate data){
        for(PeriodoIndisponivelMotorista ferias : this.feriasMotorista){
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

        for(PeriodoIndisponivelMotorista ferias : this.feriasMotorista){
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
