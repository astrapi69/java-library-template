package io.github.astrapi69;

import static org.junit.jupiter.api.Assertions.*;

import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.io.StreamExtensions;
import io.github.astrapi69.create.FileFactory;
import io.github.astrapi69.delete.DeleteFileExtensions;
import io.github.astrapi69.modify.ModifyFileExtensions;
import io.github.astrapi69.search.PathFinder;
import io.github.astrapi69.write.WriteFileExtensions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

class InitialTemplateTest
{

	@Test
//	@Disabled
	public void renameToConcreteProject() throws IOException
	{
		String concreteProjectName;
		String templateProjectName;
		String projectDescription;
		File sourceProjectDir;
		File settingsGradle;
		File dotTravisYml;
		File dotGithubDir;
		File codeOfConduct;
		File initialTemplateClassFile;
		//
		sourceProjectDir = PathFinder.getProjectDirectory();
		templateProjectName = "java-library-template";
		concreteProjectName = "foo-bar";
		projectDescription = "projectDescription-foobar-projectDescription";
		// adapt settings.gradle file
		settingsGradle = new File(sourceProjectDir, DependenciesData.SETTINGS_GRADLE_FILENAME);
		ModifyFileExtensions.modifyFile(settingsGradle.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName,
				concreteProjectName)
				+ System.lineSeparator();
		});
		// adapt CODE_OF_CONDUCT.md file
		dotGithubDir = new File(sourceProjectDir, DependenciesData.DOT_GITHUB_DIRECTORY_NAME);
		codeOfConduct = new File(dotGithubDir, DependenciesData.CODE_OF_CONDUCT_FILENAME);
		ModifyFileExtensions.modifyFile(codeOfConduct.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName,
				concreteProjectName)
				+ System.lineSeparator();
		});
		// adapt .travis.yml file
		dotTravisYml = new File(sourceProjectDir, DependenciesData.DOT_TRAVIS_FILENAME);
		ModifyFileExtensions.modifyFile(dotTravisYml.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName,
				concreteProjectName)
				+ System.lineSeparator();
		});
		// delete template class
		initialTemplateClassFile = PathFinder.getRelativePath(PathFinder.getSrcMainJavaDir(), "io", "github", "astrapi69", "InitialTemplate.java");

		DeleteFileExtensions.delete(initialTemplateClassFile);
		// change projectDescription from gradle.properties
		File gradleProperties = new File(sourceProjectDir, DependenciesData.GRADLE_PROPERTIES_NAME);

		ModifyFileExtensions.modifyFile(gradleProperties.toPath(), (count, input) -> {
			return input.replaceAll("projectDescription=Template project for create java library projects",
				"projectDescription="+projectDescription)
				+ System.lineSeparator();
		});
		// TODO
		// delete template run configurations
		// create run configurations for current project

		String sourceProjectDirNamePrefix;
		String targetProjectDirNamePrefix;
		CopyGradleRunConfigurations copyGradleRunConfigurationsData;
		String sourceProjectName;
		String targetProjectName;
		sourceProjectName = templateProjectName;
		targetProjectName = concreteProjectName;
		sourceProjectDirNamePrefix = "/home/astrapi69/dev/github/astrapi69/";
		targetProjectDirNamePrefix = "/home/astrapi69/dev/github/astrapi69/";
		copyGradleRunConfigurationsData = GradleRunConfigurationsCopier
			.newCopyGradleRunConfigurations(sourceProjectName, targetProjectName,
				sourceProjectDirNamePrefix, targetProjectDirNamePrefix, true, true);
		GradleRunConfigurationsCopier.of(copyGradleRunConfigurationsData).copy();
	}


	private void setProjectDescription(File targetProjectDir, String projectDescription) throws IOException
	{
		File gradleProperties = new File(targetProjectDir, DependenciesData.GRADLE_PROPERTIES_NAME);

		Properties properties = PropertiesExtensions.loadProperties(gradleProperties);
		properties.setProperty("projectDescription", projectDescription);

		PropertiesExtensions.export(properties,
			StreamExtensions.getOutputStream(gradleProperties));
	}
}
