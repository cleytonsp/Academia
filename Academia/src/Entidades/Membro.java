package Entidades;

import Repositorio.MembroRepository;

public class Membro extends Pessoa {
    private Plano plano;
    private static int contadorId = 1;

    public Membro(String nome, String cpf, Plano plano) {
        super(nome, cpf);
        this.plano = plano;
        super.setId(contadorId++); 
        MembroRepository.adicionarMembroNaFila(this); 
    }

    public Membro() {
        super();
        this.plano = null;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}