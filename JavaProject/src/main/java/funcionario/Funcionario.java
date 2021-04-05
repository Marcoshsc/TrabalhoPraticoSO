package funcionario;

import filatarefas.FilaTarefas;
import tarefa.Tarefa;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class Funcionario implements Runnable {

    private final String fileName;
    private final BufferedReader file;
    private final String name;
    private final FilaTarefas filaTarefas;

    public Funcionario(String fileName, FilaTarefas filaTarefas) throws IOException, URISyntaxException {
        this.fileName = fileName;
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        this.file = new BufferedReader(new FileReader(new File(url.toURI())));
        this.name = file.readLine();
        this.filaTarefas = filaTarefas;
    }

    public Tarefa leTarefa() throws IOException {
        String tarefaString = file.readLine();
        if(tarefaString == null)
            throw new IllegalArgumentException("Terminou arquivo.");
        String[] fields = tarefaString.split(" ");
        return new Tarefa(fields[0], Integer.parseInt(fields[1]), name);
    }

    public void closeFile() throws IOException {
        file.close();
    }

    @Override
    public void run() {
        while(true) {
            try {
                filaTarefas.insert(leTarefa());
            } catch (IllegalArgumentException exc) {
                System.out.println("Terminou a lista " + name);
                filaTarefas.funcFinalized();
                break;
            } catch (IOException | InterruptedException exc) {
                System.out.println("Erro na leitura");
                break;
            }
        }
    }
}
