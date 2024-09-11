/**
 * The MIT License
 *
 * Copyright (C) 2022 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapisixtynine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.collection.array.ArrayExtensions;
import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.modify.DeleteLinesByIndexInFile;
import io.github.astrapi69.file.modify.ModifyFileExtensions;
import io.github.astrapi69.file.search.FileSearchExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.gradle.migration.extension.DependenciesExtensions;
import io.github.astrapi69.gradle.migration.info.CopyGradleRunConfigurations;
import io.github.astrapi69.gradle.migration.info.DependenciesInfo;
import io.github.astrapi69.gradle.migration.runner.GradleRunConfigurationsCopier;
import io.github.astrapi69.io.file.filter.PrefixFileFilter;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

class InitialTemplateTest
{

	@Test
	@Disabled
	public void testRenameToConcreteProject() throws IOException
	{
		String projectDescription;
		// TODO change the following description with your project description
		// and then remove the annotation Disabled and run this unit test method
		projectDescription = "!!!Chage this description with your project description!!!";
		renameToConcreteProject(projectDescription);
	}

	private void renameToConcreteProject(final String projectDescription) throws IOException
	{
		String concreteProjectName;
		String concreteProjectWithDotsName;
		String templateProjectName;
		String templateProjectWithDotsName;
		File sourceProjectDir;
		File settingsGradle;
		File dotGithubDir;
		File codeOfConduct;
		File readme;
		File initialTemplateClassFile;
		//
		sourceProjectDir = PathFinder.getProjectDirectory();
		templateProjectName = DependenciesInfo.JAVA_LIBRARY_TEMPLATE_NAME;
		templateProjectWithDotsName = templateProjectName.replaceAll("-", ".");
		concreteProjectName = sourceProjectDir.getName();
		concreteProjectWithDotsName = concreteProjectName.replaceAll("-", ".");
		// adapt settings.gradle file
		settingsGradle = new File(sourceProjectDir, DependenciesInfo.SETTINGS_GRADLE_FILENAME);
		ModifyFileExtensions.modifyFile(settingsGradle.toPath(),
			(count, input) -> input.replaceAll(templateProjectName, concreteProjectName)
				+ System.lineSeparator());
		// adapt CODE_OF_CONDUCT.md file
		dotGithubDir = new File(sourceProjectDir, DependenciesInfo.DOT_GITHUB_DIRECTORY_NAME);
		codeOfConduct = new File(dotGithubDir, DependenciesInfo.CODE_OF_CONDUCT_FILENAME);
		ModifyFileExtensions.modifyFile(codeOfConduct.toPath(),
			(count, input) -> input.replaceAll(templateProjectName, concreteProjectName)
				+ System.lineSeparator());
		// delete template class
		initialTemplateClassFile = PathFinder.getRelativePath(PathFinder.getSrcMainJavaDir(), "io",
			"github", "astrapi69", "InitialTemplate.java");

		DeleteFileExtensions.delete(initialTemplateClassFile);
		// change projectDescription from gradle.properties
		File gradleProperties = FileFactory.newFile(sourceProjectDir,
			DependenciesInfo.GRADLE_PROPERTIES_FILENAME);

		ModifyFileExtensions.modifyFile(gradleProperties.toPath(),
			(count,
				input) -> input.replaceAll(
					"projectDescription=Template project for create java library projects",
					"projectDescription=" + projectDescription) + System.lineSeparator());

		// adapt README.md file
		readme = new File(sourceProjectDir, DependenciesInfo.README_FILENAME);
		ModifyFileExtensions.modifyFile(readme.toPath(),
			(count, input) -> input.replaceAll(templateProjectName, concreteProjectName)
				+ System.lineSeparator());

		ModifyFileExtensions.modifyFile(readme.toPath(),
			(count,
				input) -> input.replaceAll(templateProjectWithDotsName, concreteProjectWithDotsName)
					+ System.lineSeparator());

		ModifyFileExtensions.modifyFile(readme.toPath(),
			(count, input) -> input.replaceAll("Template project for create java library projects",
				projectDescription) + System.lineSeparator());

		ModifyFileExtensions.modifyFile(readme.toPath(),
			(count,
				input) -> input.replaceAll("javaLibraryTemplateVersion",
					DependenciesExtensions.getProjectVersionKeyName(concreteProjectName))
					+ System.lineSeparator());

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
		RuntimeExceptionDecorator.decorate(() -> DeleteFileExtensions.deleteFilesWithFileFilter(
			copyGradleRunConfigurationsData.getIdeaTargetDir(),
			new PrefixFileFilter("java_library_template", false)));

		// remove section 'Template from this project'
		removeTemplateSection(readme);

	}

	private static void removeTemplateSection(File readme) throws IOException
	{
		int startLineIndex = FileSearchExtensions.findLineIndex(readme,
			"# Template from this project");
		int endLineIndex = FileSearchExtensions.findLineIndex(readme,
			"this [medium blog](https://asterios-raptis.medium.com/new-github-template-repository-feature-ec09afe261b8)");
		endLineIndex = endLineIndex + 1;
		Integer[] deleteRangeArray = ArrayFactory.newRangeArray(startLineIndex, endLineIndex);
		List<Integer> lineIndexesToDelete = ArrayExtensions.toList(deleteRangeArray);

		DeleteLinesByIndexInFile deleter = new DeleteLinesByIndexInFile(lineIndexesToDelete);
		ModifyFileExtensions.modifyFile(readme.toPath(), deleter);
	}
}
