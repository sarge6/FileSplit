package pz;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileSplit1 {

    public static boolean writeContentToCreatedFile(File file, String content){
        boolean isCreated = false;
        try (BufferedWriter writer = Files.newBufferedWriter(
                file.toPath(),
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE))
        {
            writer.write(content);
            isCreated = true;
        } catch (Exception ex) {  }
        return isCreated;
    }
    public static void main(String[] args) {

        Optional<File> optChosenFile =
                FileDialogs.showOpenTxtFileChooserDLG("CHOOSE TEXTFILE");
        if(optChosenFile.isEmpty()) { System.exit(0); }

        File chosenFile = optChosenFile.get();
        List<String> lines = new ArrayList<>();
        int listSize = lines.size();
        String line = "";
        int maxFileLines = 50;
        int fileNr = 1;
        String fileName = "";
        int lastIndex = 0;
        try {
            lines = Files.readAllLines(chosenFile.toPath());
            listSize = lines.size();
            System.out.println(listSize);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < listSize; i++){
                line = lines.get(i);
                sb.append(line);
                sb.append("\n");
                if( (i % maxFileLines == 0) & (i > 0) ){
                    fileName = "File_" + fileNr + ".txt";
                    System.out.println("[" + i + "] " + fileName);
                    writeContentToCreatedFile(new File(chosenFile.getParent(), fileName), sb.toString());
                    if(sb.length() > 0) { sb.delete(0, sb.length()); }
                    fileNr+=1;
                    lastIndex = i;
                }
            }

            //sb.setLength(0);
            StringBuilder sb2 = new StringBuilder();
            fileName = "File_" + String.valueOf(fileNr) + ".txt";
            System.out.println(listSize-lastIndex);
            for(int i = lastIndex; i < listSize; i++){
                line = lines.get(i);
                sb2.append(line);
                sb2.append("\n");
            }
            writeContentToCreatedFile(
                    new File(chosenFile.getParent(), fileName), sb2.toString());

            System.out.println("Last Index: " + lastIndex);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }


        //String s = FileDialogs.showInputBoxDLG("test input", "xxx");
        //FileDialogs.showMessageBoxDLG("Input:  " + s, "TITLE MSGBOX");
        //boolean b = FileDialogs.showConfirmDLG("Confirm Trump?", "");
        //System.out.println("confirmed: " + b);
        //File[] chosenFiles = FileDialogs.showOpenTxtFilesChooserDLG("TITLE CHOOSE FILES");
        //if(chosenFiles == null){ System.exit(0); }






    }

}
