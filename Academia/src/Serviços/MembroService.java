package Serviços;

import Repositorio.MembroRepository;
import Entidades.Membro;
import java.util.List;

public class MembroService {
    private MembroRepository membroRepository;

    public MembroService(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    public void cadastrarMembro(Membro membro) {
        membroRepository.adicionarMembro(membro);
        System.out.println("Membro cadastrado e adicionado à fila!");
    }

    public Membro buscarMembro(int id) {
        return membroRepository.buscarMembro(id);
    }

    public void atualizarMembro(Membro membro) {
        membroRepository.atualizarMembro(membro);
    }

    public void removerMembro(int id) {
        membroRepository.removerMembro(id);
    }

    public List<Membro> listarMembros() {
        return membroRepository.listarTodos();
    }
}