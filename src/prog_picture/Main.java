/*
3.Сделать копию картинки(после запуска программы появляется копия картинки)
https://javadevblog.com/kak-skopirovat-fajl-v-java-4-sposoba-primery-i-kod.html

 */
package prog_picture;

import java.io.*;

public class Main {
    private static final String  LACAL_PATCH = "\\src\\prog_picture\\files\\";
    private static final String  FILENAME_ORIG = "android";
    private static final String EXTENTION = ".jpg";
    public static void main(String[] args) {

        String path = new java.io.File(".").getAbsolutePath();

        File fileOrig = new File(path + LACAL_PATCH + FILENAME_ORIG + EXTENTION);
        File fileCopy = new File(path + LACAL_PATCH + FILENAME_ORIG + "-copy" + EXTENTION);

        System.out.println("Копирование файла");
        System.out.println("-----------------");
        System.out.println("Путь: " + fileOrig.getParent());
        System.out.println("Оригинальный файл: " + fileOrig.getName());
        System.out.println("Копия: " + fileCopy.getName());
        System.out.println();

        copyFileUsingStream(fileOrig, fileCopy);
    }

    private static void copyFileUsingStream(File source, File dest) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
            System.out.println("Файл успешно скопирован.");
        }
        catch (FileNotFoundException ex) {
            System.err.println("Ошибка копирования. Не найден файл " + source.getAbsolutePath());
        }
        catch (IOException ex) {
            System.err.println("Ошибка копирования: " + ex.getMessage());
        }
    }

}
