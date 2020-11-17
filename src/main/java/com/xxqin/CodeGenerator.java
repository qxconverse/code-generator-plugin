package com.xxqin;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Goal which touches a timestamp file.
 *
 * @author qinxue
 * @goal touch
 * @phase process-sources
 */
@Mojo(name = "CodeGenerator")
public class CodeGenerator extends AbstractMojo {
    /**
     * 模块路径
     */
    @Parameter(property = "CodeGenerator.configurationFile", defaultValue = "")
    private String configurationFile;

    private final Gson gson = new GsonBuilder().create();

    @Override
    public void execute() throws MojoExecutionException {
        try {
            MavenProject mvnProject = (MavenProject) getPluginContext().get("project");
            String absolutePath = mvnProject.getBasedir().getAbsolutePath();
            InputStream configurationFileInputStream = new FileInputStream(absolutePath + File.separator + configurationFile);
            String configStr = CharStreams.toString(new InputStreamReader(configurationFileInputStream));
            Type type = new TypeToken<List<DataBaseConfig>>() {
            }.getType();
            List<DataBaseConfig> dbConfigList = gson.fromJson(configStr, type);
            dbConfigList.forEach(config -> DataBaseCodeGenerator.execute(config, absolutePath));
            getLog().debug("configStr is " + configStr);
        } catch (Exception e) {
            getLog().error(e);
        }
    }

}
