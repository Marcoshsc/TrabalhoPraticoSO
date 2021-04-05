package maquinas;

import filatarefas.FilaTarefas;
import tarefa.Tarefa;

import java.io.IOException;

public class Maquina  implements Runnable{

    private final FilaTarefas filaTarefas;
    private final int id;

    public Maquina(int id, FilaTarefas filaTarefas) {

        this.filaTarefas = filaTarefas;
        this.id = id;
    }


    @Override
    public void run() {
        while(true) {
            try {
                Tarefa tarefa = filaTarefas.pop();
                Thread.sleep(tarefa.getTime());
                System.out.printf("Maquina %d, %s, %s, %d\n", id, tarefa.getNameFuncionario(), tarefa.getName(), tarefa.getTime());
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
                break;
            } catch (InterruptedException exc) {
                System.out.println(exc.getMessage());
                break;
            }
        }
    }

}
