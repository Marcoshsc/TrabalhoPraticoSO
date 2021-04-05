package filatarefas;

import tarefa.Tarefa;

public class FilaTarefas {

    private int in = 0;
    private int out = 0;
    private int tam;
    private final Tarefa[] tarefas;

    public FilaTarefas(int tam) {
        this.tam = tam;
        this.tarefas = new Tarefa[tam];
    }

    public synchronized void insert(Tarefa tarefa) throws InterruptedException {
        if(tarefas.length == tam)
            wait();
        tarefas[in] = tarefa;
        in = (in + 1) % tam;
        tam++;
        notifyAll();
    }


    public synchronized Tarefa pop() throws InterruptedException {
        if(this.tarefas.length == 0)
            wait();
        Tarefa tarefa = tarefas[out];
        out = (out + 1) % tam;

        notifyAll();

        return tarefa;
    }
}
