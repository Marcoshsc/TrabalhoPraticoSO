package maquinas;

import filatarefas.FilaTarefas;
import tarefa.Tarefa;

import java.io.IOException;

/**
 * Classe que modela a máquina possuindo assim identificador da máquina, e
 * a fila de tarefas como atributos e métodos para a execução da thread
 */
public class Maquina  implements Runnable{

    /*
     * Fila de tarefas para a máquina pegar tarefas para serem executadas
     */
    private final FilaTarefas filaTarefas;
    /*
     * Identificador da máquina
     */
    private final int id;

    /**
     * Constroi uma máquina
     * @param id Identificador da máquina
     * @param filaTarefas Fila de tarefas para a máquina pegar tarefas para serem executadas
     */
    public Maquina(int id, FilaTarefas filaTarefas) {

        this.filaTarefas = filaTarefas;
        this.id = id;
    }


    /**
     * Método sobrescrito que a thread executa quando criada.
     */
    @Override
    public void run() {
        /*
         * Aqui temos um while infinito, no qual a máquina atua como um consumer da fila de tarefas tentando a todo
         * momento pegar uma tarefa para ser executada
         */
        while(true) {
            try {
                /*
                 * Aqui a máquina tenta pegar uma tarefa da fila de tarefas para executar a mesma,
                 * lembrando que o método pop é uma seção crítica,
                 * logo para preservar a exclusão mútua se a máquina não obtiver sucesso em obter o recurso,
                 * a thread da mesma terá sua execução interrompida até ser notificada novamente e voltar oara fila de
                 * prontos
                 */
                Tarefa tarefa = filaTarefas.pop();
                /*
                 * Assim que a máquina pega alguma tarefa utilizamos o método sleep para pausar a thread por um tempo
                 * pré-determinado definido pelo tempo da tarefa.
                 */
                Thread.sleep(tarefa.getTime());
                /*
                 * Imprimimos no console a informação conforme pedido no roteiro do trabalho após a tarefa ser concluída.
                 */
                System.out.printf("Maquina %d, %s, %s, %d\n", id, tarefa.getNameFuncionario(), tarefa.getName(), tarefa.getTime());
                /*
                 * A exceção IllegalArgumentException será lançada pelo método pop quando não houverem mais tarefas para serem
                 * executadas na fila de tarefas e todos os funcionários ja finalizaram suas respectivas
                 * inserções, significando que a thread da máquina pode ser finalizada, assim saíndo do while e finalizado o código da
                 * thread da máquina
                 */
            } catch (IllegalArgumentException exc) {
                break;
                /*
                 * A exceção InterruptedException será lançada se houver algum problema na interrupção das threads,
                 *  assim saíndo do while e finalizado o código da thread do funcionário
                 */
            } catch (InterruptedException exc) {
                System.out.println(exc.getMessage());
                break;
            }
        }
    }

}
