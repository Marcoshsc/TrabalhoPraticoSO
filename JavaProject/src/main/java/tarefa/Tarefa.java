package tarefa;

public class Tarefa {

    private final String name;
    private final int time;

    public Tarefa(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
