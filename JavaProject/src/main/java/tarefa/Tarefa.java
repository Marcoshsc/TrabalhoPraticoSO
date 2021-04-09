package tarefa;

/**
 * Classe que representa a tarefa, aqui temos informações sobre o tempo e nome da tarefa, além
 * do nome do funcionário que a inseriu
 */
public class Tarefa {

    /*
     * Nome da tarefa
     */
    private final String name;
    /*
     * Tempo que a tarefa executa
     */
    private final int time;
    /*
     * Nome do funcionário que inseriu a tarefa na fila
     */
    private final String nameFuncionario;

    /**
     * Constroi uma tarefa
     * @param name Nome da tarefa
     * @param time Tempo que a tarefa executa
     * @param nameFuncionario Nome do funcionário que inseriu a tarefa na fila
     */
    public Tarefa(String name, int time, String nameFuncionario) {
        this.name = name;
        this.time = time;
        this.nameFuncionario = nameFuncionario;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getNameFuncionario() {
        return nameFuncionario;
    }
}
