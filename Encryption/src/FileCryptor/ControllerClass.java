package FileCryptor;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllerClass {
    private SecretKey key;
    private Path path;
    private StringBuffer txtToEnc = new StringBuffer();
    private String encTxt;
    private String decTxt;

    public ControllerClass()
    {
        key = KEY();
        path = selectFile();

        if(path != null) {
            readFile(path);
        }

        if(txtToEnc != null) {
            encTxt = ENC(txtToEnc, key);
            decTxt = DEC(encTxt, key);

            writeFile(encTxt, path);
        }


        System.out.println("ENC: " + encTxt);
        System.out.println("DEC: " + decTxt);

    }

    // File selection +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
    public Path selectFile()
    {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        return fc.getSelectedFile().toPath();
    }

    // Read the selected File +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
    public void readFile(Path file)
    {
        String line;

        try{
            BufferedReader in = Files.newBufferedReader(Paths.get(file.toUri()));
            try{
                while((line = in.readLine()) != null) {
                    txtToEnc.append(line);
                }
            }catch(IOError er) {
                System.out.println(er.getMessage());
            }finally {
                in.close();
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    // Writes the file +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

    public void writeFile(String encTxt, Path p)
    {
        try {
            BufferedWriter out = Files.newBufferedWriter(p);

            try {
                out.write(encTxt);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            finally {
                out.close();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    // GENERATE KEY -----------------------------------------------------------------------
    public SecretKey KEY()
    {
        try {

            key = Symmetric.generateAESKey() ;

        } catch (Exception e) {
            System.out.println("Error: Unable to generate key!\n" + e.getMessage());
        }
        return key;
    }

    // ENCRYPTION -------------------------------------------------------------------------
    public String ENC(StringBuffer data, SecretKey key)
    {
        try {

            return Symmetric.encrypt(String.valueOf(data), key);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // DECRYPTION -------------------------------------------------------------------------
    public String DEC(String data, SecretKey key)
    {
        try {

            return Symmetric.decrypt(data, key) ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
