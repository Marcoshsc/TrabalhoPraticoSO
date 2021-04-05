package tarefa;

public class Tarefa {

    private final String name;
    private final int time;
    private final String nameFuncionario;

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
