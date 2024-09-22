package Entidades;

import java.util.LinkedList;
import java.util.Queue;

public class Membro extends Pessoa {
    private Plano plano;
    private static Queue<Membro> filaMembros = new LinkedList<>(); // Fila para gerenciar membros
    private static int contadorId = 1; // Contador para IDs

    public Membro(String nome, String cpf, Plano plano) {
        super(nome, cpf);
        this.plano = plano;
        super.setId(contadorId++); // Atribui ID automático
        filaMembros.add(this); // Adiciona na fila automaticamente
    }

    public Membro() {
        super();
        this.plano = null;
    }

    public static void adicionarMembroNaFila(Membro membro) {
        filaMembros.add(membro);
        System.out.println("Membro adicionado à fila: " + membro.getNome());
    }

    public static Membro removerMembroDaFila() {
        Membro membroRemovido = filaMembros.poll();
        if (membroRemovido != null) {
            System.out.println("Membro removido da fila: " + membroRemovido.getNome());
        } else {
            System.out.println("A fila está vazia.");
        }
        return membroRemovido;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}