import filatarefas.FilaTarefas;
import funcionario.Funcionario;
import maquinas.Maquina;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, URISyntaxException {

        int numeroMaquinas, capacidadeDaFila, numeroFuncionario;

        System.out.print("Digite o número de máquinas: ");
        numeroMaquinas = scanner.nextInt();

        System.out.print("Digite a capacidade da fila: ");
        capacidadeDaFila = scanner.nextInt();

        System.out.print("Digite o número de funcionários: ");
        numeroFuncionario = scanner.nextInt();

        FilaTarefas filaTarefas = new FilaTarefas(capacidadeDaFila, numeroFuncionario);

        List<Maquina> maquinas = new ArrayList<>();
        for(int i = 0 ; i < numeroMaquinas; i++){
            maquinas.add(new Maquina(i + 1, filaTarefas));
        }

        List<Funcionario> funcionarios = new ArrayList<>();

        for(int i = 0; i < numeroFuncionario; i++){
            System.out.print("Digite o nome do arquivo: ");
            String name = scanner.next();
            funcionarios.add(new Funcionario(name, filaTarefas));
        }

        for(Maquina maquina: maquinas){
            Thread threadMaquina = new Thread(maquina);
            threadMaquina.start();
        }

        for(Funcionario funcionario: funcionarios){
            Thread threadFuncionario = new Thread(funcionario);
            threadFuncionario.start();
        }
    }
}
