package x5.mantis.manager;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class JamesCliHelper extends HelperBase {
    private final String workingDir;

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
        this.workingDir = System.getProperty("james.workingDir", "c:/Education/james-server-jpa-guice");
    }


    public void addUser(String email, String password) {
        CommandLine cmd = new CommandLine("java");
        cmd.addArgument("-cp");
        cmd.addArgument("james-server-jpa-app.lib/*");
        cmd.addArgument("org.apache.james.cli.ServerCmd");
        cmd.addArgument("AddUser");
        cmd.addArgument(email);
        cmd.addArgument(password);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(workingDir));
        executor.setStreamHandler(streamHandler);

        try {
            executor.execute(cmd);
            System.out.println("Command output: " + outputStream.toString());
        } catch (ExecuteException e) {
            System.err.println("Error executing command: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }

    }
}
