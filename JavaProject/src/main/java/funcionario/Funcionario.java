package funcionario;

import filatarefas.FilaTarefas;
import tarefa.Tarefa;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Classe que modela o funcionário possuindo assim o nome do arquivo, nome do funcionário, a fila
 * de tarefas e buffer do arquivo
 * como atributos e métodos para a execução da thread e leitura das tarefas do arquivo
 */
public class Funcionario implements Runnable {

    /*
     * Nome do arquivo
     */
    private final String fileName;
    /*
     * BufferedReader do arquivo associado ao funcionário
     */
    private final BufferedReader file;
    /*
     * Nome do funcionário
     */
    private final String name;
    /*
     * Fila de tarefas para o funcionário inserir tarefas para serem executadas
     */
    private final FilaTarefas filaTarefas;

    /**
     * Construtor do funcionário
     * @param fileName Nome do arquivo que o funcionário deve ler as tarefas
     * @param filaTarefas Fila das tarefas, onde o funcionário deve inserir suas respectivas tarefas
     * @throws IOException
     * @throws URISyntaxException
     */
    public Funcionario(String fileName, FilaTarefas filaTarefas) throws IOException, URISyntaxException {
        this.fileName = fileName;
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        /*
         * Coloca o bufferReader do respectivo arquivo no atributo file
         */
        this.file = new BufferedReader(new FileReader(new File(url.toURI())));
        /*
         * Lê a primeira linha do arquivo, se no caso será o nome do funcionário
         */
        this.name = file.readLine();
        this.filaTarefas = filaTarefas;
    }

    /**
     * Método que lê as tarefas do arquivo e retorna um objeto da tarefa
     * @return tarefa lida do arquivo
     * @throws IOException
     */
    public Tarefa leTarefa() throws IOException {
        /*
         * Lê a próxima linha do arquivo, no qual possui o identificador da tarefa e seu tempo de conclusão,
         * será null se não houver mais tarefas no arquivo
         */
        String tarefaString = file.readLine();
        /*
         * Verifica se a tarefa é null se sim o arquivo terminou e uma exceção do tipo IllegalArgumentException é lançada
         */
        if(tarefaString == null)
            throw new IllegalArgumentException("Terminou arquivo.");
        /*
         * Separa por meio do espaço o identificador da tarefa e seu tempo de conclusão
         */
        String[] fields = tarefaString.split(" ");
        /*
         * Se constroe uma nova tarefa com base das informações lidas do arquivo
         */
        return new Tarefa(fields[0], Integer.parseInt(fields[1]), name);
    }

    /*
     * Método para fechar o arquivo lógico
     */
    public void closeFile() throws IOException {
        file.close();
    }

    /**
     * Método sobrescrito que a thread executa quando criada.
     */
    @Override
    public void run() {
        /*
         * Aqui temos um while infinito, no qual o funcionário atua como um producer da fila de tarefas tentando a todo
         * momento inserir uma tarefa para ser executada posteriormente pela máquina
         */
        while(true) {
            try {
                /*
                 * Aqui o funcionário tenta inserir uma tarefa lida no arquivo e construída pelo método leTarefa na fila de tarefas,
                 * lembrando que o método insert é uma seção crítica,
                 * logo para preservar a exclusão mútua se o funcionário não obtiver sucesso em obter o recurso,
                 * a thread do mesmo terá sua execução interrompida até ser notificada novamente e voltar oara fila de
                 * prontos
                 */
                filaTarefas.insert(leTarefa());
                /*
                 * A exceção IllegalArgumentException será lançada pelo método leTarefa quando não houverem mais tarefas para serem
                 * lidas, logo o arquivo chegou ao fim, assim saíndo do while e finalizado o código da thread do funcionário
                 */
            } catch (IllegalArgumentException exc) {
                filaTarefas.funcFinalized();
                break;
                /*
                 * A exceção InterruptedException será lançada se houver algum problema na interrupção das threads,
                 *  assim saíndo do while e finalizado o código da thread do funcionário
                 */
            } catch (IOException | InterruptedException exc) {
                System.out.println("Erro na leitura");
                break;
            }
        }
    }
}


