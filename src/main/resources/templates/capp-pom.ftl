<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <modelVersion>4.0.0</modelVersion>
    <groupId>${pomObject.groupId}</groupId>
    <artifactId>${pomObject.artifactId}</artifactId>
    <version>${pomObject.version}</version>
    <packaging>carbon/application</packaging>
    <name>${pomObject.projectName}</name>
    <description></description>
    <properties>
        <artifact.types>
            synapse/priority-executor=xml,jaggery/app=zip,synapse/inbound-endpoint=xml,service/rule=aar,synapse/message-store=xml,service/meta=xml,event/stream=json,datasource/datasource=xml,synapse/proxy-service=xml,synapse/sequence=xml,bpel/workflow=zip,synapse/endpointTemplate=xml,carbon/application=car,synapse/api=xml,wso2/gadget=dar,synapse/event-source=xml,synapse/message-processors=xml,event/receiver=xml,lib/dataservice/validator=jar,synapse/template=xml,synapse/endpoint=xml,lib/carbon/ui=jar,lib/synapse/mediator=jar,synapse/local-entry=xml,synapse/task=xml,event/publisher=xml,synapse/configuration=xml,webapp/jaxws=war,registry/resource=zip,service/axis2=aar,synapse/lib=zip,synapse/sequenceTemplate=xml,event/execution-plan=siddhiql,service/dataservice=dbs,web/application=war,lib/library/bundle=jar
        </artifact.types>
    </properties>
    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>daily</updatePolicy>
                <checksumPolicy>ignore</checksumPolicy>
            </releases>
            <id>wso2-nexus</id>
            <url>http://maven.wso2.org/nexus/content/groups/wso2-public/</url>
        </repository>
        <repository>
            <id>wso2-maven2-repository-1</id>
            <url>http://dist.wso2.org/maven2</url>
        </repository>
        <repository>
            <id>wso2-nexus-repository-1</id>
            <url>http://maven.wso2.org/nexus/content/groups/wso2-public/</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>daily</updatePolicy>
                <checksumPolicy>ignore</checksumPolicy>
            </releases>
            <id>wso2-nexus</id>
            <url>http://maven.wso2.org/nexus/content/groups/wso2-public/</url>
        </pluginRepository>
        <pluginRepository>
            <id>wso2-maven2-repository-1</id>
            <url>http://dist.wso2.org/maven2</url>
        </pluginRepository>
        <pluginRepository>
            <id>wso2-nexus-repository-1</id>
            <url>http://maven.wso2.org/nexus/content/groups/wso2-public/</url>
        </pluginRepository>
    </pluginRepositories>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>2.9</version>
                <configuration>
                    <buildcommands/>
                    <projectnatures>
                        <projectnature>org.wso2.developerstudio.eclipse.distribution.project.nature</projectnature>
                    </projectnatures>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.wso2.maven</groupId>
                <artifactId>maven-car-plugin</artifactId>
                <version>2.1.1</version>
                <extensions>true</extensions>
                <executions>
                    <execution>
                        <id>car</id>
                        <phase>package</phase>
                        <goals>
                            <goal>car</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration/>
            </plugin>
            <plugin>
                <groupId>org.wso2.maven</groupId>
                <artifactId>maven-car-deploy-plugin</artifactId>
                <version>1.1.1</version>
                <extensions>true</extensions>
                <configuration>
                    <carbonServers>
                        <CarbonServer>
                            <trustStorePath>${r"${basedir}"}/src/main/resources/security/wso2carbon.jks</trustStorePath>
                            <trustStorePassword>wso2carbon</trustStorePassword>
                            <trustStoreType>JKS</trustStoreType>
                            <serverUrl>https://localhost:9443</serverUrl>
                            <userName>admin</userName>
                            <password>admin</password>
                            <operation>deploy</operation>
                        </CarbonServer>
                    </carbonServers>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
