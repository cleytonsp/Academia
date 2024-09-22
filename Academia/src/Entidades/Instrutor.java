package Entidades;

public class Instrutor extends Pessoa {
    private String especialidade;

    public Instrutor(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
    }

    public Instrutor() {
        super();
        this.especialidade = "";
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Especialidade: " + especialidade;
    }
}