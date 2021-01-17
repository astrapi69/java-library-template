package io.github.astrapi69;

import static org.junit.jupiter.api.Assertions.*;

import io.github.astrapi69.modify.ModifyFileExtensions;
import io.github.astrapi69.search.PathFinder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class InitialTemplateTest
{

	@Test
//	@Disabled
	public void renameToConcreteProject() throws IOException
	{
		String concreteProjectName;
		String templateProjectName;
		File sourceProjectDir;
		File settingsGradle;
		File dotTravisYml;
		File dotGithubDir;
		File codeOfConduct;
		//
		sourceProjectDir = PathFinder.getProjectDirectory();
		templateProjectName = "java-library-template";
		concreteProjectName = "foo-bar";
		settingsGradle = new File(sourceProjectDir, DependenciesData.SETTINGS_GRADLE_FILENAME);
		ModifyFileExtensions.modifyFile(settingsGradle.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName,
				concreteProjectName)
				+ System.lineSeparator();
		});

		dotGithubDir = new File(sourceProjectDir, DependenciesData.DOT_GITHUB_DIRECTORY_NAME);
		codeOfConduct = new File(dotGithubDir, DependenciesData.CODE_OF_CONDUCT_FILENAME);
		ModifyFileExtensions.modifyFile(codeOfConduct.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName,
				concreteProjectName)
				+ System.lineSeparator();
		});

		dotTravisYml = new File(sourceProjectDir, DependenciesData.DOT_TRAVIS_FILENAME);
		ModifyFileExtensions.modifyFile(dotTravisYml.toPath(), (count, input) -> {
			return input.replaceAll(templateProjectName,
				concreteProjectName)
				+ System.lineSeparator();
		});

		// TODO
		// change projectDescription from gradle.properties
		// delete template class
		// rename run configurations
	}

}
