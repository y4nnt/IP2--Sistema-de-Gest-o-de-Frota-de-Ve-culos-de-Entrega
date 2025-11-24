package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.repositorios.IRepositorioMotorista;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.repositorios.RepositorioMotorista;
import com.gestaoentregas.excecoes.DIException;
import com.gestaoentregas.excecoes.IIException;
import com.gestaoentregas.excecoes.MCException;
import com.gestaoentregas.excecoes.MIException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicoMotorista {
    private final RepositorioMotorista repositorioMotorista;

    public ServicoMotorista(RepositorioMotorista repositorioMotorista) {
        this.repositorioMotorista = repositorioMotorista;
    }

    public void cadastrarMotorista(Motorista novoMotorista) throws MCException, IIException {

        // 1. Tenta buscar no banco pra ver se JÁ existe (para evitar duplicidade)
        Motorista motoristaNoBanco = repositorioMotorista.buscarMotorista(novoMotorista.getIdMotorista());

        // 2. Se o retorno for DIFERENTE de null, é porque JÁ EXISTE. Aí você lança erro.
        if (motoristaNoBanco != null) {
            throw new MCException("Motorista já cadastrado!");
        }

        // 3. AQUI ESTAVA O ERRO:
        // Você deve validar a idade do "novoMotorista" (o parâmetro), e não do "motoristaNoBanco" (que é null)
        if (novoMotorista.getIdadeMotorista() < 18) {
            throw new IIException("Motorista menor de idade não permitido.");
        }

        // 4. Se passou por tudo, salva
        repositorioMotorista.cadastrarMotorista(novoMotorista);
    }


    public void atualizarMotorista(Motorista motorista) throws MIException {
        if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()) == null) {
            throw new MIException();
        }
        repositorioMotorista.atualizarMotorista(motorista);
    }

    public void removerMotorista(int id) throws MIException {
        if (repositorioMotorista.buscarMotorista(id) == null) {
            throw new MIException();
        }
        repositorioMotorista.removerMotorista(id);
    }

    public Motorista buscarMotorista(int id) throws MIException {
        Motorista motorista = repositorioMotorista.buscarMotorista(id);
        if (motorista == null) {
            throw new MIException();
        }
        return motorista;
    }

    public ArrayList<Motorista> listarMotoristas() {
        return repositorioMotorista.listarMotoristas();
    }
}
