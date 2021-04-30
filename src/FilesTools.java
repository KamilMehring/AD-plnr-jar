

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilesTools {

    private String fileName;
    private String content;

    FilesTools(String nazwaPliku){
        this.fileName = nazwaPliku;
    }
    public Boolean store(String content){
        //zapisywanie
        return null;
    }

    public String read(){
        String content = "";
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine())
            { //odczytuj plik tak długo aż  osiągniesz koniec pliku

                content+= scan.nextLine(); //odczyt kazdej lini
                content+= "\n";

            }
            scan.close();
        } catch (FileNotFoundException ex) {
            //plik nie iestnieje
        }

        return this.content;
    }
}
