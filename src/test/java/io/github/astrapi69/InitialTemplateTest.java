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
	public static final String BUILD_GRADLE_FILENAME = "build.gradle";
	public static final String DOT_GITHUB_DIRECTORY_NAME = ".github";
	public static final String CODE_OF_CONDUCT_FILENAME = "CODE_OF_CONDUCT.md";

	@Test
//	@Disabled
	public void renameToConcreteProject() throws IOException
	{
		String concreteProjectName;
		File sourceProjectDir;
		File settingsGradle;
		File dotGithubDir;
		File codeOfConduct;
		//
		concreteProjectName = "foo-bar";
		sourceProjectDir = PathFinder.getProjectDirectory();
		settingsGradle = new File(sourceProjectDir, DependenciesData.SETTINGS_GRADLE_FILENAME);
		ModifyFileExtensions.modifyFile(settingsGradle.toPath(), (count, input) -> {
			return input.replaceAll(sourceProjectDir.getName(),
				concreteProjectName)
				+ System.lineSeparator();
		});

		dotGithubDir = new File(sourceProjectDir, DependenciesData.DOT_GITHUB_DIRECTORY_NAME);
		codeOfConduct = new File(dotGithubDir, DependenciesData.CODE_OF_CONDUCT_FILENAME);
		ModifyFileExtensions.modifyFile(codeOfConduct.toPath(), (count, input) -> {
			return input.replaceAll(sourceProjectDir.getName(),
				concreteProjectName)
				+ System.lineSeparator();
		});
	}

}
