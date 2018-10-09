<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <modelVersion>4.0.0</modelVersion>
    <groupId>${pomObject.groupId}</groupId>
    <artifactId>${pomObject.artifactId}</artifactId>
    <version>${pomObject.version}</version>
    <packaging>pom</packaging>
    <name>${pomObject.projectName}</name>
    <modules>
    <#list modules as module>
        <module>${module}</module>
    </#list>
    </modules>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>2.9</version>
                <configuration>
                    <buildcommands/>
                    <projectnatures>
                        <projectnature>org.wso2.developerstudio.eclipse.mavenmultimodule.project.nature</projectnature>
                    </projectnatures>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>