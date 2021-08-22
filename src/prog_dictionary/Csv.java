package prog_dictionary;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Csv {

    private Csv() {
    }

    public static HashMap<String, String> getMap(String filename, int direction) {
        HashMap<String, String> map = new HashMap<>();
        String str;
        String key;
        String word;

        try
        {
            FileReader fr= new FileReader(filename);
            Scanner scan = new Scanner(fr);

            while (scan.hasNext()) {
                str = scan.next();
                String[] arr = str.split(",");
                if(arr.length == 2) {

                    if(direction == Character.getNumericValue(Const.ENG_RUS)) {
                        key = arr[0].replaceAll(" ", "");
                        word = arr[1].replaceAll(" ", "");
                    }
                    else {
                        key = arr[1].replaceAll(" ", "");
                        word = arr[0].replaceAll(" ", "");
                    }
                    map.put(key,word);
                }
            }
            fr.close();
        }
        catch(IOException ex){
            System.err.println("Файл не найден: " + filename);
            return null;
        }

        return map;
    }

}
