import filatarefas.FilaTarefas;
import funcionario.Funcionario;
import maquinas.Maquina;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que inicia o projeto, lê as informações do usuário como número de maquinas,
 * capaciade da fila e número de funcionarios, além de iniciar as thread para as máquinas e funcionários
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, URISyntaxException {

        int numeroMaquinas, capacidadeDaFila, numeroFuncionario;

        /*
         * Lê o número de máquinas
         */
        System.out.print("Digite o número de máquinas: ");
        numeroMaquinas = scanner.nextInt();

        /*
         * Lê a capacidade da fila
         */
        System.out.print("Digite a capacidade da fila: ");
        capacidadeDaFila = scanner.nextInt();

        /*
         * Lê o número de funcionários
         */
        System.out.print("Digite o número de funcionários: ");
        numeroFuncionario = scanner.nextInt();

        /*
         * Inicia a fila de tareafas com as informações lidas
         */
        FilaTarefas filaTarefas = new FilaTarefas(capacidadeDaFila, numeroFuncionario);

        /*
         * Cria o número de máquinas requisitado pelo usuário
         */
        List<Maquina> maquinas = new ArrayList<>();
        for(int i = 0 ; i < numeroMaquinas; i++){
            maquinas.add(new Maquina(i + 1, filaTarefas));
        }

        /*
         * Cria o número de funcionarios requisitado pelo usuário
         */
        List<Funcionario> funcionarios = new ArrayList<>();
        for(int i = 0; i < numeroFuncionario; i++){
            System.out.print("Digite o nome do arquivo: ");
            String name = scanner.next();
            funcionarios.add(new Funcionario(name, filaTarefas));
        }

        /*
         * Cria uma thread para cada máquina e a inicia com o método start
         */
        for(Maquina maquina: maquinas){
            Thread threadMaquina = new Thread(maquina);
            threadMaquina.start();
        }

        /*
         * Cria uma thread para cada funcionario e a inicia com o método start
         */
        for(Funcionario funcionario: funcionarios){
            Thread threadFuncionario = new Thread(funcionario);
            threadFuncionario.start();
        }
    }
}
