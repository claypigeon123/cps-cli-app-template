package com.cps.template.cliapptemplate;

import com.cps.template.cliapptemplate.provider.PicocliProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner, ExitCodeGenerator {

    @NonNull
    private final App app;

    @NonNull
    private final IFactory springFactory;

    @NonNull
    private final PicocliProvider picocliProvider;

    private int exitCode = 0;

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(AppRunner.class, args)));
    }

    @Override
    public void run(String... args) {
        CommandLine cmd = new CommandLine(app, springFactory);
        cmd.setCommandName(picocliProvider.getExecutableName());
        exitCode = cmd.execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
