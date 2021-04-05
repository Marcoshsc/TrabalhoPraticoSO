import filatarefas.FilaTarefas;
import funcionario.Funcionario;
import maquinas.Maquina;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Teste {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, URISyntaxException {
        FilaTarefas filaTarefas = new FilaTarefas(3, 3);

        Maquina m1 = new Maquina(1, filaTarefas);
        Maquina m2 = new Maquina(2, filaTarefas);
        Maquina m3 = new Maquina(3, filaTarefas);
        Funcionario f1 = new Funcionario("func1.txt", filaTarefas);
        Funcionario f2 = new Funcionario("func2.txt", filaTarefas);
        Funcionario f3 = new Funcionario("func3.txt", filaTarefas);
        Thread tm1 = new Thread(m1);
        tm1.start();
        Thread tm2 = new Thread(m2);
        tm2.start();
        Thread tm3 = new Thread(m3);
        tm3.start();
        Thread func1 = new Thread(f1);
        func1.start();
        Thread func2 = new Thread(f2);
        func2.start();
        Thread func3 = new Thread(f3);
        func3.start();
    }
}
