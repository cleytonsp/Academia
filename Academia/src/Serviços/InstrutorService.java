package Serviços;

import java.util.List;

import Entidades.Instrutor;
import InterfaceRepositorio.IInstrutorRepository;
import InterfacesdeServiço.IInstrutorService;

public class InstrutorService implements IInstrutorService {
    private IInstrutorRepository instrutorRepository;

    public InstrutorService(IInstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    @Override
    public void cadastrarInstrutor(Instrutor instrutor) {
        instrutorRepository.adicionarInstrutor(instrutor); // Chama o método do repositório
    }

    @Override
    public Instrutor buscarInstrutor(String cpf) {
        return instrutorRepository.buscarInstrutor(cpf);
    }

    @Override
    public void atualizarInstrutor(Instrutor instrutor) {
        instrutorRepository.atualizarInstrutor(instrutor); // Atualiza via repositório
    }

    @Override
    public void removerInstrutor(String cpf) {
        instrutorRepository.removerInstrutor(cpf); // Remove via repositório
    }

    @Override
    public List<Instrutor> listarInstrutores() {
        return instrutorRepository.listarTodos(); // Retorna lista de instrutores
    }
}