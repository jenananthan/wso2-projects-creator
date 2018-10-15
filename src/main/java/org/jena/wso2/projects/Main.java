package org.jena.wso2.projects;

import com.diogonunes.jcdp.color.api.Ansi;
import org.jena.wso2.projects.dto.ProjectMetadata;
import org.jena.wso2.projects.project.ProjectGenerator;
import org.jena.wso2.projects.util.CommandLineUtil;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandLineUtil commandLineUtil = CommandLineUtil.getInstance();

        commandLineUtil.printline(Ansi.FColor.GREEN);
        commandLineUtil.println(Ansi.FColor.GREEN, " W S O 2  P R O J E C T  C R E A T O R");
        commandLineUtil.printline(Ansi.FColor.GREEN);

        //project metadata questionnaire
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


        commandLineUtil.printline(Ansi.FColor.GREEN);
        commandLineUtil.println(Ansi.FColor.GREEN, "GENERATING WSO2 PROJECT(S)");
        commandLineUtil.printline(Ansi.FColor.GREEN);

        //Generate the project
        ProjectMetadata projectMetadata = new ProjectMetadata();
        projectMetadata.setProjectName(projectName);
        projectMetadata.setGroupId(groupId);
        projectMetadata.setIncludeDssProject(createDSSProject.equalsIgnoreCase("Y"));
        projectMetadata.setIncludeEsbProject(createESBProject.equalsIgnoreCase("Y"));
        projectMetadata.setIncludeRegistryProject(createRegProject.equalsIgnoreCase("Y"));
        projectMetadata.setEnvironments(environments);
        projectMetadata.setRegistryCustomDirs(customDirs);

        new ProjectGenerator(projectMetadata).generate();
    }
}
