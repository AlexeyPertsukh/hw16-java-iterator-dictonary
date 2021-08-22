package prog_dictionary;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class Prog {
    private final Scanner sc;
    private int typeDictionary;     //1-2 анло_русский/русско_английский
    private String cmd;
    private boolean endProg;

    private HashMap<String, String> dictionary;

    public Prog() {
        sc = new Scanner(System.in);
    }

    //========= ОСНОВНОЙ МЕТОД ================================================
    public void go() {
        setTypeDictionary();
        if(loadToMap()) {
            printDictionaryInfo();
        }
        else {
            endProg = true;
        }

        while(!endProg) {
            printCommands();
            inputCommand();
            processCommand();
        }

//        printOnEnd();
    }
    //=========================================================================

    private boolean loadToMap() {
            dictionary = loadFromCsv();
            return (dictionary != null);
    }

    private HashMap<String, String> loadFromCsv() {
        String path = new java.io.File(".").getAbsolutePath();
        String filename = path + Const.FILENAME;
        return Csv.getMap(filename, typeDictionary);
    }

    private void printDictionaryInfo() {
        String strTypeDictionary = (typeDictionary == Const.ENG_RUS) ? "англо-русский" : "русско-английский";
        System.out.println();
        System.out.println("Загружен " + strTypeDictionary + " словарь");
        System.out.println("Количество слов: " + dictionary.size());
        System.out.println();
    }

    private void setTypeDictionary() {
        String message = String.format("Направление перевода (%c англо-русский, %c русско-английский): ", Const.ENG_RUS, Const.RUS_ENG);
        char chType = My.nextCharLowerCase(sc, message, Const.ENG_RUS, Const.RUS_ENG);
        String str = Character.toString(chType);
        typeDictionary = Integer.parseInt(str);
    }


    private void printDictionary() {

        dictionary.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEach(e -> {
                                String strWords = String.format("%-20s %-20s", e.getKey(),e.getValue());
                                System.out.println(strWords);
                               });
        System.out.println();
    }

    private void printCommands() {
        System.out.println("-----------------------------------------------------------------");
        String info = String.format("%s %s, %s %s, для перевода введите слово ", Command.PRINT_DICTIONARY.getKey(), Command.PRINT_DICTIONARY.getNameRus(),
                                                   Command.EXIT.getKey(), Command.EXIT.getNameRus());
        System.out.println(info);
        System.out.println("-----------------------------------------------------------------");
    }

    private void inputCommand() {
        System.out.print("введите команду или слово для перевода: ");
        cmd = sc.next();
    }

    private void processCommand() {
        String key;

        //
        key = Command.EXIT.getKey();
        if(My.cmpStr(cmd, key)) {
            endProg = true;
            return;
        }

        //
        key = Command.PRINT_DICTIONARY.getKey();
        if(My.cmpStr(cmd, key)) {
            printDictionary();
            return;
        }

        //иначе- считаем, что ввели слово для перевода
        String wordAfterTranslation = translate(cmd);
        printTranslate(wordAfterTranslation);

    }

    private void printOnEnd() {
        System.out.println();
        System.out.println(Const.COPYRIGHT);
        System.out.println(Const.AUTHOR);
    }

    //распечатать перевод слова
    private void printTranslate(String wordAfterTranslation) {
        if(wordAfterTranslation == null) {
            System.out.println("слово отсутствует в словаре");
        }
        else {
            System.out.println("перевод: " + wordAfterTranslation);
        }
        System.out.println();
    }

    private String translate(String wordForTranslate) {
        return dictionary.get(wordForTranslate.toLowerCase());
    }

}
