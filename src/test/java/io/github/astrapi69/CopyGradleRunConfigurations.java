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

import java.io.File;

public class CopyGradleRunConfigurations
{

	public static final String IDEA_DIR_NAME = ".idea";
	public static final String RUN_CONFIGURATIONS_DIR_NAME = "runConfigurations";
	File ideaSourceDir;
	File ideaTargetDir;
	String sourceFilenamePrefix;
	File sourceProjectDir;
	String sourceProjectName;
	File sourceRunConfigDir;
	String targetFilenamePrefix;
	File targetProjectDir;
	boolean onlyRunConfigurations;
	String targetProjectName;
	File targetRunConfigDir;

	CopyGradleRunConfigurations(File ideaSourceDir, File ideaTargetDir, String sourceFilenamePrefix,
		File sourceProjectDir, String sourceProjectName, File sourceRunConfigDir,
		String targetFilenamePrefix, File targetProjectDir, String targetProjectName,
		File targetRunConfigDir, boolean onlyRunConfigurations)
	{
		this.ideaSourceDir = ideaSourceDir;
		this.ideaTargetDir = ideaTargetDir;
		this.sourceFilenamePrefix = sourceFilenamePrefix;
		this.sourceProjectDir = sourceProjectDir;
		this.sourceProjectName = sourceProjectName;
		this.sourceRunConfigDir = sourceRunConfigDir;
		this.targetFilenamePrefix = targetFilenamePrefix;
		this.targetProjectDir = targetProjectDir;
		this.targetProjectName = targetProjectName;
		this.targetRunConfigDir = targetRunConfigDir;
		this.onlyRunConfigurations = onlyRunConfigurations;
	}

	public static CopyGradleRunConfigurationsBuilder builder()
	{
		return new CopyGradleRunConfigurationsBuilder();
	}

	public boolean isOnlyRunConfigurations()
	{
		return onlyRunConfigurations;
	}

	public void setOnlyRunConfigurations(boolean onlyRunConfigurations)
	{
		this.onlyRunConfigurations = onlyRunConfigurations;
	}

	protected boolean canEqual(final Object other)
	{
		return other instanceof CopyGradleRunConfigurations;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof CopyGradleRunConfigurations))
			return false;
		final CopyGradleRunConfigurations other = (CopyGradleRunConfigurations)o;
		if (!other.canEqual(this))
			return false;
		final Object this$ideaSourceDir = this.getIdeaSourceDir();
		final Object other$ideaSourceDir = other.getIdeaSourceDir();
		if (this$ideaSourceDir == null
			? other$ideaSourceDir != null
			: !this$ideaSourceDir.equals(other$ideaSourceDir))
			return false;
		final Object this$ideaTargetDir = this.getIdeaTargetDir();
		final Object other$ideaTargetDir = other.getIdeaTargetDir();
		if (this$ideaTargetDir == null
			? other$ideaTargetDir != null
			: !this$ideaTargetDir.equals(other$ideaTargetDir))
			return false;
		final Object this$sourceFilenamePrefix = this.getSourceFilenamePrefix();
		final Object other$sourceFilenamePrefix = other.getSourceFilenamePrefix();
		if (this$sourceFilenamePrefix == null
			? other$sourceFilenamePrefix != null
			: !this$sourceFilenamePrefix.equals(other$sourceFilenamePrefix))
			return false;
		final Object this$sourceProjectDir = this.getSourceProjectDir();
		final Object other$sourceProjectDir = other.getSourceProjectDir();
		if (this$sourceProjectDir == null
			? other$sourceProjectDir != null
			: !this$sourceProjectDir.equals(other$sourceProjectDir))
			return false;
		final Object this$sourceProjectName = this.getSourceProjectName();
		final Object other$sourceProjectName = other.getSourceProjectName();
		if (this$sourceProjectName == null
			? other$sourceProjectName != null
			: !this$sourceProjectName.equals(other$sourceProjectName))
			return false;
		final Object this$sourceRunConfigDir = this.getSourceRunConfigDir();
		final Object other$sourceRunConfigDir = other.getSourceRunConfigDir();
		if (this$sourceRunConfigDir == null
			? other$sourceRunConfigDir != null
			: !this$sourceRunConfigDir.equals(other$sourceRunConfigDir))
			return false;
		final Object this$targetFilenamePrefix = this.getTargetFilenamePrefix();
		final Object other$targetFilenamePrefix = other.getTargetFilenamePrefix();
		if (this$targetFilenamePrefix == null
			? other$targetFilenamePrefix != null
			: !this$targetFilenamePrefix.equals(other$targetFilenamePrefix))
			return false;
		final Object this$targetProjectDir = this.getTargetProjectDir();
		final Object other$targetProjectDir = other.getTargetProjectDir();
		if (this$targetProjectDir == null
			? other$targetProjectDir != null
			: !this$targetProjectDir.equals(other$targetProjectDir))
			return false;
		final Object this$targetProjectName = this.getTargetProjectName();
		final Object other$targetProjectName = other.getTargetProjectName();
		if (this$targetProjectName == null
			? other$targetProjectName != null
			: !this$targetProjectName.equals(other$targetProjectName))
			return false;
		final Object this$targetRunConfigDir = this.getTargetRunConfigDir();
		final Object other$targetRunConfigDir = other.getTargetRunConfigDir();
		return (this$targetRunConfigDir == null
			? other$targetRunConfigDir != null
			: !this$targetRunConfigDir.equals(other$targetRunConfigDir));
	}

	public File getIdeaSourceDir()
	{
		return this.ideaSourceDir;
	}

	public void setIdeaSourceDir(File ideaSourceDir)
	{
		this.ideaSourceDir = ideaSourceDir;
	}

	public File getIdeaTargetDir()
	{
		return this.ideaTargetDir;
	}

	public void setIdeaTargetDir(File ideaTargetDir)
	{
		this.ideaTargetDir = ideaTargetDir;
	}

	public String getSourceFilenamePrefix()
	{
		return this.sourceFilenamePrefix;
	}

	public void setSourceFilenamePrefix(String sourceFilenamePrefix)
	{
		this.sourceFilenamePrefix = sourceFilenamePrefix;
	}

	public File getSourceProjectDir()
	{
		return this.sourceProjectDir;
	}

	public void setSourceProjectDir(File sourceProjectDir)
	{
		this.sourceProjectDir = sourceProjectDir;
	}

	public String getSourceProjectName()
	{
		return this.sourceProjectName;
	}

	public void setSourceProjectName(String sourceProjectName)
	{
		this.sourceProjectName = sourceProjectName;
	}

	public File getSourceRunConfigDir()
	{
		return this.sourceRunConfigDir;
	}

	public void setSourceRunConfigDir(File sourceRunConfigDir)
	{
		this.sourceRunConfigDir = sourceRunConfigDir;
	}

	public String getTargetFilenamePrefix()
	{
		return this.targetFilenamePrefix;
	}

	public void setTargetFilenamePrefix(String targetFilenamePrefix)
	{
		this.targetFilenamePrefix = targetFilenamePrefix;
	}

	public File getTargetProjectDir()
	{
		return this.targetProjectDir;
	}

	public void setTargetProjectDir(File targetProjectDir)
	{
		this.targetProjectDir = targetProjectDir;
	}

	public String getTargetProjectName()
	{
		return this.targetProjectName;
	}

	public void setTargetProjectName(String targetProjectName)
	{
		this.targetProjectName = targetProjectName;
	}

	public File getTargetRunConfigDir()
	{
		return this.targetRunConfigDir;
	}

	public void setTargetRunConfigDir(File targetRunConfigDir)
	{
		this.targetRunConfigDir = targetRunConfigDir;
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $ideaSourceDir = this.getIdeaSourceDir();
		result = result * PRIME + ($ideaSourceDir == null ? 43 : $ideaSourceDir.hashCode());
		final Object $ideaTargetDir = this.getIdeaTargetDir();
		result = result * PRIME + ($ideaTargetDir == null ? 43 : $ideaTargetDir.hashCode());
		final Object $sourceFilenamePrefix = this.getSourceFilenamePrefix();
		result = result * PRIME
			+ ($sourceFilenamePrefix == null ? 43 : $sourceFilenamePrefix.hashCode());
		final Object $sourceProjectDir = this.getSourceProjectDir();
		result = result * PRIME + ($sourceProjectDir == null ? 43 : $sourceProjectDir.hashCode());
		final Object $sourceProjectName = this.getSourceProjectName();
		result = result * PRIME + ($sourceProjectName == null ? 43 : $sourceProjectName.hashCode());
		final Object $sourceRunConfigDir = this.getSourceRunConfigDir();
		result = result * PRIME
			+ ($sourceRunConfigDir == null ? 43 : $sourceRunConfigDir.hashCode());
		final Object $targetFilenamePrefix = this.getTargetFilenamePrefix();
		result = result * PRIME
			+ ($targetFilenamePrefix == null ? 43 : $targetFilenamePrefix.hashCode());
		final Object $targetProjectDir = this.getTargetProjectDir();
		result = result * PRIME + ($targetProjectDir == null ? 43 : $targetProjectDir.hashCode());
		final Object $targetProjectName = this.getTargetProjectName();
		result = result * PRIME + ($targetProjectName == null ? 43 : $targetProjectName.hashCode());
		final Object $targetRunConfigDir = this.getTargetRunConfigDir();
		result = result * PRIME
			+ ($targetRunConfigDir == null ? 43 : $targetRunConfigDir.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return "CopyGradleRunConfigurations(ideaSourceDir=" + this.getIdeaSourceDir()
			+ ", ideaTargetDir=" + this.getIdeaTargetDir() + ", sourceFilenamePrefix="
			+ this.getSourceFilenamePrefix() + ", sourceProjectDir=" + this.getSourceProjectDir()
			+ ", sourceProjectName=" + this.getSourceProjectName() + ", sourceRunConfigDir="
			+ this.getSourceRunConfigDir() + ", targetFilenamePrefix="
			+ this.getTargetFilenamePrefix() + ", targetProjectDir=" + this.getTargetProjectDir()
			+ ", targetProjectName=" + this.getTargetProjectName() + ", targetRunConfigDir="
			+ this.getTargetRunConfigDir() + ")";
	}

	public static class CopyGradleRunConfigurationsBuilder
	{
		boolean onlyRunConfigurations;
		private File ideaSourceDir;
		private File ideaTargetDir;
		private String sourceFilenamePrefix;
		private File sourceProjectDir;
		private String sourceProjectName;
		private File sourceRunConfigDir;
		private String targetFilenamePrefix;
		private File targetProjectDir;
		private String targetProjectName;
		private File targetRunConfigDir;

		CopyGradleRunConfigurationsBuilder()
		{
		}

		public CopyGradleRunConfigurations build()
		{
			return new CopyGradleRunConfigurations(ideaSourceDir, ideaTargetDir,
				sourceFilenamePrefix, sourceProjectDir, sourceProjectName, sourceRunConfigDir,
				targetFilenamePrefix, targetProjectDir, targetProjectName, targetRunConfigDir,
				onlyRunConfigurations);
		}


		public CopyGradleRunConfigurationsBuilder onlyRunConfigurations(
			boolean onlyRunConfigurations)
		{
			this.onlyRunConfigurations = onlyRunConfigurations;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder ideaSourceDir(
			File ideaSourceDir)
		{
			this.ideaSourceDir = ideaSourceDir;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder ideaTargetDir(
			File ideaTargetDir)
		{
			this.ideaTargetDir = ideaTargetDir;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder sourceFilenamePrefix(
			String sourceFilenamePrefix)
		{
			this.sourceFilenamePrefix = sourceFilenamePrefix;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder sourceProjectDir(
			File sourceProjectDir)
		{
			this.sourceProjectDir = sourceProjectDir;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder sourceProjectName(
			String sourceProjectName)
		{
			this.sourceProjectName = sourceProjectName;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder sourceRunConfigDir(
			File sourceRunConfigDir)
		{
			this.sourceRunConfigDir = sourceRunConfigDir;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder targetFilenamePrefix(
			String targetFilenamePrefix)
		{
			this.targetFilenamePrefix = targetFilenamePrefix;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder targetProjectDir(
			File targetProjectDir)
		{
			this.targetProjectDir = targetProjectDir;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder targetProjectName(
			String targetProjectName)
		{
			this.targetProjectName = targetProjectName;
			return this;
		}

		public CopyGradleRunConfigurationsBuilder targetRunConfigDir(
			File targetRunConfigDir)
		{
			this.targetRunConfigDir = targetRunConfigDir;
			return this;
		}

		@Override
		public String toString()
		{
			return "CopyGradleRunConfigurations.CopyGradleRunConfigurationsBuilder(ideaSourceDir="
				+ this.ideaSourceDir + ", ideaTargetDir=" + this.ideaTargetDir
				+ ", sourceFilenamePrefix=" + this.sourceFilenamePrefix + ", sourceProjectDir="
				+ this.sourceProjectDir + ", sourceProjectName=" + this.sourceProjectName
				+ ", sourceRunConfigDir=" + this.sourceRunConfigDir + ", targetFilenamePrefix="
				+ this.targetFilenamePrefix + ", targetProjectDir=" + this.targetProjectDir
				+ ", targetProjectName=" + this.targetProjectName + ", targetRunConfigDir="
				+ this.targetRunConfigDir + ")";
		}
	}
}
