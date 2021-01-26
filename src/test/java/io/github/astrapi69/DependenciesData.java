/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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
package io.github.astrapi69;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.util.List;
import java.util.Properties;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class DependenciesData
{
	public static final String JAVA_LIBRARY_TEMPLATE_NAME = "java-library-template";
	public static final String SETTINGS_GRADLE_FILENAME = "settings.gradle";
	public static final String BUILD_GRADLE_NAME = "build.gradle";
	public static final String GRADLE_PROPERTIES_NAME = "gradle.properties";
	public static final String DOT_GITHUB_DIRECTORY_NAME = ".github";
	public static final String DOT_TRAVIS_FILENAME = ".travis.yml";
	public static final String CODE_OF_CONDUCT_FILENAME = "CODE_OF_CONDUCT.md";
	public static final String README_FILENAME = "README.md";

	File buildGradle;
	File settingsGradle;
	Properties properties;
	List<String> versionStrings;

}
