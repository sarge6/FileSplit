package pz;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class FileDialogs {
    public static final File USER_DIR = new File(System.getProperty("user.home"));
    public static final File USER_DOCSDIR = new File(USER_DIR, "Documents");
    public static Optional<File> showOpenTxtFileChooserDLG(String title){
        File chosenFile = null;
        Optional<File> chosenFileOpt = Optional.ofNullable(chosenFile);
        final JFileChooser fc = new JFileChooser();
        if(!title.isBlank()){ fc.setDialogTitle(title.toUpperCase()); }
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("Txt Files", "txt", "TXT");
        fc.addChoosableFileFilter(filter);
        int choice = fc.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION) {
            chosenFileOpt = Optional.ofNullable(fc.getSelectedFile());
        }
        return chosenFileOpt;
    }
    public static File[] showOpenTxtFilesChooserDLG(String title){
        File[] chosenFiles = null;
        final JFileChooser fc = new JFileChooser();
        if(!title.isBlank()){ fc.setDialogTitle(title.toUpperCase()); }
        fc.setMultiSelectionEnabled(true);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("Txt Files", "txt", "TXT");
        fc.addChoosableFileFilter(filter);
        int choice = fc.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION) {
            chosenFiles = fc.getSelectedFiles();
        }
        return chosenFiles;
    }
    public static String showInputBoxDLG(String message, String defaultValue){
        String input = JOptionPane.showInputDialog(message, defaultValue);
        if( (input == null)||(input.isBlank()) ) { return ""; }
        return input;
    }
    public static void showMessageBoxDLG(String message, String title){
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean showConfirmDLG(String message, String title){
        if( (title == null)||(title.isBlank()) ) title = "CONFIRMATION";
        boolean confirmed = false;
        int choice = JOptionPane.showConfirmDialog(null, message, title,
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) { confirmed = true; }
        return confirmed;
    }
}
