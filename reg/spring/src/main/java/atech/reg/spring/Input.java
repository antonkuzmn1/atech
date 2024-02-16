package atech.reg.spring;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Input {

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("login", true, "database's login");
        options.addOption("password", true, "database's password");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String login = cmd.getOptionValue("login");
            String password = cmd.getOptionValue("password");

            if (login == null || password == null) {
                formatter.printHelp("java -jar /path/to/spring/app.jar", options);
                System.exit(1);
            }

            System.setProperty("spring.datasource.username", login);
            System.setProperty("spring.datasource.password", password);

        } catch (ParseException e) {
            System.out.println("etwa kurwa happened: " + e.getMessage());
            formatter.printHelp("java -jar /path/to/spring/app.jar", options);
            System.exit(1);
        }
    }
}
