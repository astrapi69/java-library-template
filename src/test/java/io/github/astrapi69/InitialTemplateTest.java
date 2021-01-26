package io.github.astrapi69;

import de.alpharogroup.string.StringExtensions;
import io.github.astrapi69.delete.DeleteFileExtensions;
import io.github.astrapi69.io.file.filter.PrefixFileFilter;
import io.github.astrapi69.modify.ModifyFileExtensions;
import io.github.astrapi69.search.PathFinder;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitialTemplateTest
{

	@Test
	//	@Disabled
	public void testRenameToConcreteProject() throws IOException
	{
		String projectDescription;

		projectDescription = "projectDescription-foobar-projectDescription";
		renameToConcreteProject(projectDescription);

	}

	@Test
	//	@Disabled
	public void testCamelCase() throws IOException
	{
		String actual;
		String expected;
		String input;
		String templateProjectName;
		templateProjectName = DependenciesData.JAVA_LIBRARY_TEMPLATE_NAME;

		input = "LORD_OF_THE_RINGS";
		expected = "LordOfTheRings";
		actual = WordUtils
			.capitalizeFully(input, new char[]{'_'}).replaceAll("_", "");
		assertEquals(expected, actual);

		input = templateProjectName;
		expected = "JavaLibraryTemplate";
		assertEquals(expected, WordUtils
			.capitalizeFully(input, new char[]{'-'}).replaceAll("-", ""));

		actual = getProjectVersionKeyName(input);
		expected = "javaLibraryTemplateVersion";
		assertEquals(expected, actual);
	}

	private String getProjectVersionKeyName(String projectName) {
		String camelCased = WordUtils
			.capitalizeFully(projectName, new char[]{'-'}).replaceAll("-", "");
		String projectVersionKeyName = StringExtensions.firstCharacterToLowerCase(camelCased);
		return projectVersionKeyName + "Version";
	}

	private void renameToConcreteProject(final String projectDescription) throws IOException
	{
		String concreteProjectName;
		String templateProjectName;
		File sourceProjectDir;
		File settingsGradle;
		File dotTravisYml;
		File dotGithubDir;
		File codeOfConduct;
		File readme;
		File initialTemplateClassFile;
		//
		sourceProjectDir = PathFinder.getProjectDirectory();
		templateProjectName = DependenciesData.JAVA_LIBRARY_TEMPLATE_NAME;
		concreteProjectName = sourceProjectDir.getName();
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
		});

		// adapt README.md file
		readme = new File(sourceProjectDir, DependenciesData.README_FILENAME);
		ModifyFileExtensions.modifyFile(readme.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName, concreteProjectName) + System
				.lineSeparator();
		});

		ModifyFileExtensions.modifyFile(readme.toPath(), (count, input) -> {
			return input
				.replaceAll("Template project for create java library projects",
					projectDescription) + System.lineSeparator();
		});

		ModifyFileExtensions.modifyFile(readme.toPath(), (count, input) -> {
			return input
				.replaceAll("javaLibraryTemplateVersion",
					getProjectVersionKeyName(concreteProjectName)) + System.lineSeparator();
		});

		// create run configurations for current project
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
			.deleteFilesWithFileFilter(copyGradleRunConfigurationsData.getIdeaTargetDir(),
				new PrefixFileFilter("java_library_template", false)));
	}

}
