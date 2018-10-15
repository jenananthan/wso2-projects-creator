package org.jena.wso2.projects.project;

import com.diogonunes.jcdp.color.api.Ansi;
import freemarker.template.TemplateException;
import org.jena.wso2.projects.util.TemplateGenerator;
import org.jena.wso2.projects.constants.Constants;
import org.jena.wso2.projects.util.CommandLineUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Project {
    protected String baseProjectName;
    protected String projectName;
    protected String groupId;
    protected String version;

    protected String[] subdirectories;

    protected String suffix;
    protected String pomFileTemplate;
    protected String eclipseProjectFileTemplate;

    protected final CommandLineUtil commandLineUtil = CommandLineUtil.getInstance();

    public Project(ProjectBuilder builder) {
        this.baseProjectName = builder.baseProjectName;
        if (builder.suffix == null) {
            this.projectName = builder.baseProjectName;
        } else {
            this.projectName = builder.baseProjectName + builder.suffix;
        }
        this.groupId = builder.groupId;
        this.version = builder.version;

        this.subdirectories = builder.subdirectories;

        this.suffix = builder.suffix;
        this.pomFileTemplate = builder.pomFileTemplate;
        this.eclipseProjectFileTemplate = builder.pomFileTemplate;
    }


    public abstract void generate() throws IOException;

    protected void generateTemplate(Map<String, Object> params, String templateFile, String outputFile) throws IOException, TemplateException {
        String fileDisplayName = outputFile;
        commandLineUtil.println(Ansi.FColor.GREEN, "\tGenerating project file: " + fileDisplayName);

        TemplateGenerator.generate(params, templateFile, outputFile);
    }

    public static final class ProjectBuilder {
        private String baseProjectName;
        private String groupId;
        private String version;

        private String[] subdirectories;

        private String suffix;
        private String pomFileTemplate;
        private String eclipseProjectFileTemplate;

        public ProjectBuilder withBaseProjectName(String baseProjectName) {
            this.baseProjectName = baseProjectName;
            return this;
        }

        public ProjectBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public ProjectBuilder withVersion(String version) {
            this.version = version;
            return this;
        }

        public ProjectBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        public ProjectBuilder withSubdirectories(String[] subdirectories) {
            this.subdirectories = subdirectories;
            return this;
        }

        public Project build(String type) {
            switch (type) {
                case Constants.PROJECT_TYPE_ROOT:
                    return buildRootProject();
                case Constants.PROJECT_TYPE_CAPP:
                    return buildCappProject();
                case Constants.PROJECT_TYPE_ESB:
                    return buildEsbProject();
                case Constants.PROJECT_TYPE_REG:
                    return buildRegistryProject();
                case Constants.PROJECT_TYPE_DSS:
                    return buildDssProject();
                default:
                    throw new AssertionError("No such project type");
            }
        }

        private Project buildCappProject() {
            this.suffix = this.suffix == null ? null : Constants.SUFFIX_CONFIG + this.suffix;
            this.pomFileTemplate = Constants.CAPP_POM_FTL;
            this.eclipseProjectFileTemplate = Constants.CAPP_ECLIPSE_FILE_FTL;

            return new ChildProject(this);
        }

        private Project buildEsbProject() {
            this.suffix = Constants.SUFFIX_ESB;
            this.pomFileTemplate = Constants.ESB_POM_FTL;
            this.eclipseProjectFileTemplate = Constants.ESB_ECLIPSE_FILE_FTL;
            this.subdirectories = Constants.ESB_DIRS;

            return new ChildProject(this);
        }

        private Project buildRegistryProject() {
            this.suffix = Constants.SUFFIX_REG;
            this.pomFileTemplate = Constants.REG_POM_FTL;
            this.eclipseProjectFileTemplate = Constants.REG_ECLIPSE_FILE_FTL;

            return new ChildProject(this);
        }

        private Project buildDssProject() {
            this.suffix = Constants.SUFFIX_DSS;
            this.pomFileTemplate = Constants.DSS_POM_FTL;
            this.eclipseProjectFileTemplate = Constants.DSS_ECLIPSE_FILE_FTL;
            this.subdirectories = Constants.DSS_DIRS;

            return new ChildProject(this);
        }

        private Project buildRootProject() {
            this.suffix = Constants.SUFFIX_ROOT;
            this.pomFileTemplate = Constants.ROOT_POM_FTL;
            this.eclipseProjectFileTemplate = Constants.ROOT_ECLIPSE_FILE_FTL;
            this.subdirectories = null;

            return new RootProject(this);
        }
    }

    public static final class Auditor {
        private static final Auditor INSTANCE = new Auditor();

        private final List<String> projectAudits;

        private Auditor() {
            this.projectAudits = new ArrayList<>();
        }

        public static Auditor getInstance() {
            return INSTANCE;
        }

        public void registerProject(String project) {
            this.projectAudits.add(project);
        }

        public List<String> getAudits() {
            return projectAudits;
        }
    }

}
