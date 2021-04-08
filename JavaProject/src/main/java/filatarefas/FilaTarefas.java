package filatarefas;

import tarefa.Tarefa;

/**
 * Classe que implementa o funcionamento da fila de tarefas. Ela controla a inserção e remoção de novos elementos na fila,
 * além de controlar quantos funcionários já esgotaram sua lista de tarefas.
 */
public class FilaTarefas {

    /**
     * Posição de inserção na fila de tarefas.
     */
    private int in = 0;

    /**
     * Posição de remoção na fila de tarefas.
     */
    private int out = 0;

    /**
     * Tamanho máximo da fila de tarefas.
     */
    private int tam;

    /**
     * Tamanho atual da fila de tarefas.
     */
    private int size = 0;

    /**
     * Lista que armazena as tarefas.
     */
    private final Tarefa[] tarefas;

    /**
     * Número de funcionários que interagem com a fila de tarefas.
     */
    private final int nFuncionarios;

    /**
     * Número de funcionários que já esgotaram sua lista de tarefas.
     */
    private int counter = 0;

    /**
     * Constrói uma nova fila de tarefas.
     * @param tam tamanho máximo da fila de tarefas.
     * @param nFuncionarios número de funcionários que interagem com a fila de tarefas.
     */
    public FilaTarefas(int tam, int nFuncionarios) {
        this.tam = tam;
        this.tarefas = new Tarefa[tam];
        this.nFuncionarios =  nFuncionarios;
    }

    /**
     * Insere uma tarefa na fila de tarefas e acorda todas as threads adormecidas. A thread entra em espera caso a
     * fila esteja cheia.
     * @param tarefa tarefa a ser inserida
     * @throws InterruptedException caso algum erro ocorra ao executar o wait().
     */
    public synchronized void insert(Tarefa tarefa) throws InterruptedException {
        while(size == tam)
            wait();
        tarefas[in] = tarefa;
        in = (in + 1) % tam;
        size++;
        notifyAll();
    }

    /**
     * Função que o funcionário deve chamar quando esgotar sua lista de tarefas. Quando todos os funcionários terminam as
     * suas tarefas, todas as máquinas são acordadas.
     */
    public synchronized void funcFinalized() {
        counter++;
        if(counter == nFuncionarios)
            notifyAll();
    }

    /**
     * Remove e retorna uma tarefa da fila de tarefas e acorda todas as threads adormecidas. A thread entra em espera caso a
     * fila esteja vazia, ou seja, caso não existam elementos para serem removidos.
     * @return a tarefa removida
     * @throws InterruptedException caso algum erro ocorra ao executar o wait().
     */
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
