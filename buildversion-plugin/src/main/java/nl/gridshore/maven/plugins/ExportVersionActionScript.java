package nl.gridshore.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 *
 * Goal that creates a Version.as file with an ActionScript object Version in there. This class has one public
 * property of type String that contains the maven version of the current project.
 *
 * @goal writeActionScript
 * @phase generate-sources
 * @description creates a Version.as file in the generates-sources folder in the package components
 */
public class ExportVersionActionScript extends AbstractMojo {
    /**
     * Location of the file.
     *
     * @parameter expression="${project.build.directory}/generated-resources"
     * @required
     * @readonly
     */
    private String outputDirectory;

    /**
     * @parameter expression="${project.version}"
     * @required
     * @readonly
     */
    private String version;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            File targetDirectory = new File(outputDirectory);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            targetDirectory = new File(outputDirectory+"/components");
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }

            FileWriter fstream = new FileWriter(outputDirectory + "/components/Version.as");
            BufferedWriter out = new BufferedWriter(fstream);

            out.write("package components {\n");
            out.write("    public class Version {\n");
            out.write("        public var version:String = \"" + version + "\";\n");
            out.write("        public function Version() {\n");
            out.write("\n");
            out.write("        }\n");
            out.write("    }\n");
            out.write("}\n");

            //Close the output stream
            out.close();
        }
        catch (IOException e) {
            getLog().error(e);
            throw new MojoExecutionException("Unable to open file for write");
        }
    }
}
