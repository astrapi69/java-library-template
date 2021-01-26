package io.github.astrapi69;

import de.alpharogroup.collections.properties.PropertiesExtensions;
import io.github.astrapi69.delete.DeleteFileExtensions;
import io.github.astrapi69.io.StreamExtensions;
import io.github.astrapi69.io.file.filter.PrefixFileFilter;
import io.github.astrapi69.modify.ModifyFileExtensions;
import io.github.astrapi69.search.PathFinder;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

class InitialTemplateTest
{
	private Git git;
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
		concreteProjectName = "foo-bar-test";
		projectDescription = "projectDescription-foobar-projectDescription";
		// adapt settings.gradle file
		settingsGradle = new File(sourceProjectDir, DependenciesData.SETTINGS_GRADLE_FILENAME);
		ModifyFileExtensions.modifyFile(settingsGradle.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName, concreteProjectName) + System
				.lineSeparator();
		});
		// adapt CODE_OF_CONDUCT.md file
		dotGithubDir = new File(sourceProjectDir, DependenciesData.DOT_GITHUB_DIRECTORY_NAME);
		codeOfConduct = new File(dotGithubDir, DependenciesData.CODE_OF_CONDUCT_FILENAME);
		ModifyFileExtensions.modifyFile(codeOfConduct.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName, concreteProjectName) + System
				.lineSeparator();
		});
		// adapt .travis.yml file
		dotTravisYml = new File(sourceProjectDir, DependenciesData.DOT_TRAVIS_FILENAME);
		ModifyFileExtensions.modifyFile(dotTravisYml.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName, concreteProjectName) + System
				.lineSeparator();
		});
		// delete template class
		initialTemplateClassFile = PathFinder
			.getRelativePath(PathFinder.getSrcMainJavaDir(), "io", "github", "astrapi69",
				"InitialTemplate.java");

		DeleteFileExtensions.delete(initialTemplateClassFile);
		// change projectDescription from gradle.properties
		File gradleProperties = new File(sourceProjectDir, DependenciesData.GRADLE_PROPERTIES_NAME);

		ModifyFileExtensions.modifyFile(gradleProperties.toPath(), (count, input) -> {
			return input
				.replaceAll("projectDescription=Template project for create java library projects",
					"projectDescription=" + projectDescription) + System.lineSeparator();
		});		// create run configurations for current project

		String sourceProjectDirNamePrefix;
		String targetProjectDirNamePrefix;
		CopyGradleRunConfigurations copyGradleRunConfigurationsData;
		String sourceProjectName;
		String targetProjectName;
		sourceProjectName = templateProjectName;
		targetProjectName = concreteProjectName;
		sourceProjectDirNamePrefix = sourceProjectDir.getParent() + "/";
		targetProjectDirNamePrefix = sourceProjectDirNamePrefix;
		copyGradleRunConfigurationsData = GradleRunConfigurationsCopier
			.newCopyGradleRunConfigurations(sourceProjectName, targetProjectName,
				sourceProjectDirNamePrefix, targetProjectDirNamePrefix, true, true);
		GradleRunConfigurationsCopier.of(copyGradleRunConfigurationsData).copy();

		// delete template run configurations
		RuntimeExceptionDecorator.decorate(() -> DeleteFileExtensions
			.deleteFilesWithFileFilter(
				copyGradleRunConfigurationsData.getIdeaTargetDir(),
				new PrefixFileFilter("java_library_template", false)));

		// add to the new run configurations to git
		RuntimeExceptionDecorator.decorate(() ->
			addRunConfigurationsToGit(copyGradleRunConfigurationsData));
		// TODO check
	}

	private void addRunConfigurationsToGit(CopyGradleRunConfigurations copyGradleRunConfigurationsData) throws IOException, GitAPIException
	{
		git = Git.open(copyGradleRunConfigurationsData.getTargetProjectDir());

		File targetRunConfigDir = copyGradleRunConfigurationsData.getTargetRunConfigDir();
		File[] files = targetRunConfigDir.listFiles();
		if (files != null && 0 < files.length)
		{
			Arrays.stream(files).forEach(this::addGitFile);
		}

		Status status = git.status().call();

		Set<String> added = status.getAdded();
		for (String add : added)
		{
			System.out.println("Added: " + add);
		}
	}

	private void addGitFile(File file)
	{
		RuntimeExceptionDecorator.decorate(() ->
			git.add().addFilepattern(file.getName()).call());
	}

	private void setProjectDescription(File targetProjectDir, String projectDescription)
		throws IOException
	{
		File gradleProperties = new File(targetProjectDir, DependenciesData.GRADLE_PROPERTIES_NAME);

		Properties properties = PropertiesExtensions.loadProperties(gradleProperties);
		properties.setProperty("projectDescription", projectDescription);

		PropertiesExtensions.export(properties, StreamExtensions.getOutputStream(gradleProperties));
	}
}
