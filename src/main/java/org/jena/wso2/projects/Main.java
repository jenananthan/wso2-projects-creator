package org.jena.wso2.projects;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.diogonunes.jcdp.bw.Printer;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.jena.wso2.projects.constants.Constants;

import freemarker.template.TemplateException;
import org.jena.wso2.projects.util.CommandLineUtil;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        CommandLineUtil commandLineUtil = CommandLineUtil.getInstance();

        commandLineUtil.printline(Ansi.FColor.GREEN);
        commandLineUtil.println(Ansi.FColor.GREEN, " W S O 2  P R O J E C T  C R E A T O R");
        commandLineUtil.printline(Ansi.FColor.GREEN);

        String projectName = commandLineUtil
                .newTerminalScan()
                .withMessage("Enter project name (e.g invoice-sync): ")
                .withPattern("[a-z-]*")
                .withErrorMessage("ERROR: Project names should be in hyphen case (e.g invoice-sync)")
                .scan();

        String[] environments = commandLineUtil
                .newTerminalScan()
                .withMessage("Enter environments with comma separated values (e.g dev,aat,sit): ")
                .withPattern("[a-z,]*")
                .withErrorMessage("ERROR: Environment names should be in comma-separated lowercase")
                .scan()
                .split(",");

        String groupId = commandLineUtil
                .newTerminalScan()
                .withMessage("Enter group id (e.g org.test.sales): ")
                .withPattern("[a-z.]*")
                .withErrorMessage("ERROR: Project group id should be in lowercase (e.g org.test.sales)")
                .scan();

        String createESBProject = commandLineUtil
                .newTerminalScan()
                .withMessage("Do you want to create ESB project (Y/n): ")
                .withPattern("[ynYN]")
                .withErrorMessage("ERROR: Invalid answer, please answer Y-yes or N-no")
                .withDefaultValue("Y")
                .scan();

        String createRegProject = commandLineUtil
                .newTerminalScan()
                .withMessage("Do you want to create Registry project (Y/n): ")
                .withPattern("[ynYN]")
                .withErrorMessage("ERROR: Invalid answer, please answer Y-yes or N-no")
                .withDefaultValue("Y")
                .scan();

        String[] customDirs = null;
        if ("Y".equalsIgnoreCase(createRegProject)) {
            String createRegCustomDir = commandLineUtil
                    .newTerminalScan()
                    .withMessage("Do you want to create custom directories in the Registry projects (Y/n): ")
                    .withPattern("[ynYN]")
                    .withErrorMessage("ERROR: Invalid answer, please answer Y-yes or N-no")
                    .withDefaultValue("Y")
                    .scan();

            if ("Y".equalsIgnoreCase(createRegCustomDir)) {
                customDirs = commandLineUtil
                        .newTerminalScan()
                        .withMessage("Enter custom directory paths (e.g xslt, xsd, endpoints/dev, endpoints/sit): ")
                        .withPattern("[a-z,\\/]*")
                        .withErrorMessage("ERROR: Custom directory paths should be in comma-separated lowercase")
                        .scan()
                        .split(",");
            }
        }

        String createDSSProject = commandLineUtil
                .newTerminalScan()
                .withMessage("Do you want to create DSS project (Y/n): ")
                .withPattern("[ynYN]")
                .withErrorMessage("ERROR: Invalid answer, please answer Y-yes or N-no")
                .withDefaultValue("Y")
                .scan();


        String targetProjectName = "";
        List<String> modules = new ArrayList<>();

        commandLineUtil.printline(Ansi.FColor.GREEN);
        commandLineUtil.println(Ansi.FColor.GREEN, "GENERATING WSO2 PROJECT(S)");
        commandLineUtil.printline(Ansi.FColor.GREEN);

        //Create REG project
        if ("y".equalsIgnoreCase(createRegProject)) {
            targetProjectName = projectName + Constants.SUFFIX_REG;
            modules.add(targetProjectName);

            commandLineUtil.println(Ansi.FColor.GREEN, "Generating project :" + targetProjectName);

            RegistryProject registryProject = new RegistryProject(targetProjectName, groupId);
            registryProject.create(customDirs);
        }

        //Create ESB Project
        if ("y".equalsIgnoreCase(createESBProject)) {
            targetProjectName = projectName + Constants.SUFFIX_ESB;
            modules.add(targetProjectName);

            commandLineUtil.println(Ansi.FColor.GREEN, "Generating project :" + targetProjectName);

            ESBProject esbProject = new ESBProject(targetProjectName, groupId);
            esbProject.create();
        }

        //Create DSS project
        if ("y".equalsIgnoreCase(createDSSProject)) {
            targetProjectName = projectName + Constants.SUFFIX_DSS;
            modules.add(targetProjectName);

            commandLineUtil.println(Ansi.FColor.GREEN, "Generating project :" + targetProjectName);

            DSSProject dssProject = new DSSProject(targetProjectName, groupId);
            dssProject.create();
        }

        //Create common CAPP project
        targetProjectName = projectName + Constants.SUFFIX_CAPP;
        modules.add(targetProjectName);

        commandLineUtil.println(Ansi.FColor.GREEN, "Generating project :" + targetProjectName);

        CAPPProject cappProject = new CAPPProject(targetProjectName, groupId);
        cappProject.create();

        //Create CAAP Projects for envs
        for (String env : environments) {
            targetProjectName = projectName + Constants.SUFFIX_CONFIG + env.toLowerCase();
            modules.add(targetProjectName);

            commandLineUtil.println(Ansi.FColor.GREEN, "Generating project :" + targetProjectName);

            CAPPProject cappEnvProject = new CAPPProject(targetProjectName, groupId);
            cappEnvProject.create();
        }
        //Create Parent project
        RootProject rootProject = new RootProject(projectName, groupId, modules);
        rootProject.create();

    }
}
