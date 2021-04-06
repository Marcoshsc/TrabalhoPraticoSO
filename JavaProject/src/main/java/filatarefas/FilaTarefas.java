package filatarefas;

import tarefa.Tarefa;

public class FilaTarefas {

    private int in = 0;
    private int out = 0;
    private int tam;
    private int size = 0;
    private final Tarefa[] tarefas;

    private final int nFuncionarios;
    private int counter = 0;

    public FilaTarefas(int tam, int nFuncionarios) {
        this.tam = tam;
        this.tarefas = new Tarefa[tam];
        this.nFuncionarios =  nFuncionarios;
    }

    public synchronized void insert(Tarefa tarefa) throws InterruptedException {
        while(size == tam)
            wait();
        tarefas[in] = tarefa;
        in = (in + 1) % tam;
        size++;
        notifyAll();
    }

    public synchronized void funcFinalized() {
        counter++;
        notifyAll();
    }

    public synchronized Tarefa pop() throws InterruptedException {
        while(size == 0) {
            if(counter == nFuncionarios)
                throw new IllegalArgumentException("Terminou os funcionarios");
            wait();
        }
        Tarefa tarefa = tarefas[out];
        out = (out + 1) % tam;
        size--;
        notifyAll();
        return tarefa;
    }
}
