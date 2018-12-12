package io.zip;

import io.basics.BasicIOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.imageio.IIOException;
import monitor.utils.ProgressMonitor;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class ZipManager extends BasicIOUtils {

    /**
     * This method compress any file or directory on Zip format
     *
     * @param inputDirectory directory or file that will be compressed
     * @param outputDirectory directory where the compressed file will be saved
     * @throws IOException
     */
    public static void zipFile(String inputDirectory, String outputDirectory) throws IOException {

        String internalDir = "";
        File file = new File(inputDirectory);
        if (!file.exists()) {
            throw new IIOException("file not found");
        }

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(outputDirectory)));

        if (file.isFile()) {
            zip(file, outputDirectory, zipOutputStream);
        } else {
            internalDir = file.getName();
            File[] files = file.listFiles();
            for (File f : files) {
                zip(f, internalDir, zipOutputStream);
            }
        }
        zipOutputStream.close();

    }

    /**
     * This method compress any file or directory on Zip format
     *
     * @param inputDirectory directory or file that will be compressed
     * @param outputDirectory directory where the compressed file will be saved
     * @param attributes list of attributes to add on final archive
     * @throws IOException
     */
    public static void zipFile(String inputDirectory, String outputDirectory, HashMap<String, String> attributes) throws IOException {

        String internalDir = "";
        File file = new File(inputDirectory);
        if (!file.exists()) {
            throw new IIOException("file not found");
        }

        File outputFile = new File(outputDirectory);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));

        if (file.isFile()) {
            zip(file, outputDirectory, zipOutputStream);
        } else {
            internalDir = file.getName();
            File[] files = file.listFiles();
            for (File f : files) {
                zip(f, internalDir, zipOutputStream);
            }
        }
        zipOutputStream.close();
        AttributeManager.defineAtributes(outputFile, attributes);

    }

    private static void zip(File file, String outputDirectory, ZipOutputStream zipOutputStream) throws IOException {

        byte data[] = new byte[BUFFER_SIZE];

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                zip(f, outputDirectory + File.separator + file.getName(), zipOutputStream);
            }
        } else {

            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            ZipEntry entry = new ZipEntry(outputDirectory + File.separator + file.getName());
            zipOutputStream.putNextEntry(entry);

            int count;
            while ((count = fileInputStream.read(data)) > 0) {
                zipOutputStream.write(data, 0, count);
            }

            zipOutputStream.closeEntry();
            fileInputStream.close();

        }

    }
    
        /**
     * This method compress any file or directory on Zip format
     *
     * @param inputDirectory directory or file that will be compressed
     * @param outputDirectory directory where the compressed file will be saved
     * @param attributes list of attributes to add on final archive
     * @param monitor Object of ProcessMonitor for process monitoring
     * @throws IOException
     */
    public static void zipFile(String inputDirectory,
            String outputDirectory,
            HashMap<String, String> attributes,
            ProgressMonitor monitor) throws IOException {

        String internalDir = "";
        File file = new File(inputDirectory);
        if (!file.exists()) {
            throw new IIOException("file not found");
        }

        File outputFile = new File(outputDirectory);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));

        monitor.setInitValue(0);
        monitor.setMaxValue(calcTotalFiles(file));

        if (file.isFile()) {
            zip(file, outputDirectory, zipOutputStream, monitor);
        } else {
            internalDir = file.getName();
            File[] files = file.listFiles();
            for (File f : files) {
                zip(f, internalDir, zipOutputStream, monitor);
            }
        }
        zipOutputStream.close();
        AttributeManager.defineAtributes(outputFile, attributes);
        
    }

    /**
     * This method compress any file or directory on Zip format
     *
     * @param inputDirectory directory or file that will be compressed
     * @param outputDirectory directory where the compressed file will be saved
     * @param monitor Object of ProcessMonitor for process monitoring
     * @throws IOException
     */
    public static void zipFile(String inputDirectory, String outputDirectory, ProgressMonitor monitor) throws IOException {

        String internalDir = "";
        File file = new File(inputDirectory);
        if (!file.exists()) {
            throw new IIOException("file not found");
        }

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(outputDirectory)));

        monitor.setInitValue(0);
        monitor.setMaxValue(calcTotalFiles(file));
        
        if (file.isFile()) {
            zip(file, outputDirectory, zipOutputStream, monitor);
        } else {
            internalDir = file.getName();
            File[] files = file.listFiles();
            for (File f : files) {
                zip(f, internalDir, zipOutputStream, monitor);
            }
        }
        zipOutputStream.close();

    }

    private static void zip(File file,
            String outputDirectory,
            ZipOutputStream zipOutputStream,
            ProgressMonitor monitor) throws IOException {

        byte data[] = new byte[BUFFER_SIZE];

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                zip(f, outputDirectory + File.separator + file.getName(), zipOutputStream, monitor);
            }
        } else {

            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            ZipEntry entry = new ZipEntry(outputDirectory + File.separator + file.getName());
            zipOutputStream.putNextEntry(entry);

            int count;
            while ((count = fileInputStream.read(data)) > 0) {
                zipOutputStream.write(data, 0, count);
            }

            zipOutputStream.closeEntry();
            fileInputStream.close();
            
            monitor.next();

        }

    }

    /**
     * This method unzip a file to an directory
     *
     * @param inputFile file to unzip
     * @param outputDirectory outputdirectory
     * @throws IOException
     */
    public static void unzipFile(File inputFile, File outputDirectory) throws IOException {

        if (inputFile.exists()) {
            if (inputFile.isDirectory()) {
                throw new IllegalArgumentException("\"" + inputFile.getAbsolutePath() + "\" its is not a file!");
            }
        } else {
            throw new IllegalArgumentException("\"" + inputFile.getAbsolutePath() + "\" does not exist!");
        }
        if (outputDirectory.exists()) {
            if (outputDirectory.isFile()) {
                throw new IllegalArgumentException("\"" + outputDirectory.getAbsolutePath() + "\" its is not a direcotory!");
            }
        }

        ZipInputStream zip = new ZipInputStream(new FileInputStream(inputFile));
        unzip(zip, outputDirectory);
        zip.close();

    }

    /**
     * This method unzip a file to an directory
     *
     * @param inputFile file to unzip
     * @param outputDirectory outputdirectory
     * @param deleteAfterConclude if true delete the input file after conclude
     * @throws IOException
     */
    public static void unzipFile(File inputFile, File outputDirectory, boolean deleteAfterConclude) throws IOException {
        unzipFile(inputFile, outputDirectory);
        if (deleteAfterConclude) {
            inputFile.deleteOnExit();
        }
    }

    /**
     * This method unzip a file to an directory
     *
     * @param inputDirectory file to unzip
     * @param outputDirectory outputdirectory
     * @throws IOException
     */
    public static void unzipFile(String inputDirectory, String outputDirectory) throws IOException {
        unzipFile(new File(inputDirectory), new File(outputDirectory));
    }

    /**
     * This method unzip a file to an directory
     *
     * @param inputDirectory file to unzip
     * @param outputDirectory output directory
     * @param deleteAfterConclude if true delete the input file after conclude
     * @throws IOException
     */
    public static void unzipFile(String inputDirectory, String outputDirectory, boolean deleteAfterConclude) throws IOException {
        unzipFile(new File(inputDirectory), new File(outputDirectory), deleteAfterConclude);
    }

    private static void unzip(ZipInputStream zip, File directory) throws IOException {

        ZipEntry element = null;

        while ((element = zip.getNextEntry()) != null) {

            String name = element.getName();
            name = name.replace('/', File.separatorChar);
            name = name.replace('\\', File.separatorChar);
            File file = new File(directory, name);

            if (element.isDirectory()) {
                file.mkdirs();
            } else {
                if (!file.exists()) {
                    File parent = file.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }
                    file.createNewFile();
                } else {
                    if (file.isHidden()) {
                        Files.setAttribute(file.toPath(), "dos:hidden", false);
                    }
                    if (!file.canWrite()) {
                        file.setWritable(true);
                    }
                }
            }

            OutputStream out = new FileOutputStream(file);
            copy(zip, out);
            out.close();

            if (file.exists()) {
                if (file.isHidden()) {
                    Files.setAttribute(file.toPath(), "dos:hidden", true);
                }
                if (!file.canWrite()) {
                    file.setWritable(false);
                }
            }

            file.setLastModified(element.getTime());

        }

    }

}
