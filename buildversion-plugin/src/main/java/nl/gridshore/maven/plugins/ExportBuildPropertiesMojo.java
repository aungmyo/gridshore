package nl.gridshore.maven.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Goal that creates a version.properties file that contains version related properties.
 *
 * @goal write
 * 
 * @phase generate-sources
 * 
 * @description creates a version.properties file in the classes folder
 */
public class ExportBuildPropertiesMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     * @parameter expression="${project.build.outputDirectory}"
     * @required
     */
    private String outputDirectory;

    
    /**
     * @parameter expression="${project.version}"
     * @required
     * @readonly
     */
    private String version;
    
    public void execute()
        throws MojoExecutionException
    {
        Properties projectProperties = new Properties();
        projectProperties.put("version", version);

        try
        {
            File targetDirectory = new File(outputDirectory);
            if (!targetDirectory.exists())
            {
                targetDirectory.mkdirs();
            }

            File propertiesFile = new File(outputDirectory + "/version.properties");
            propertiesFile.createNewFile();

            OutputStream propertiesStream = new FileOutputStream(propertiesFile);
            projectProperties.store(propertiesStream, "Properties from Maven POM");
            propertiesStream.close();
        }
        catch (IOException e)
        {
            getLog().error(e);
            throw new MojoExecutionException("Unable to open properties file for write");
        }
    }
}
