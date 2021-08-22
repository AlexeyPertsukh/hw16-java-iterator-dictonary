/*
*Добавить в Storage iterator.
Попробовать рабоать с Storage в цикле for each.
 */
package storage_for_each;

public class Main {
    public static void main(String[] args) {
        Storage<Avto> storage = new Storage<>();
        storage.add(new Avto("ЗАЗ-965", 1960));
        storage.add(new Avto("ЗАЗ-968", 1972));
        storage.add(new Avto("ЗАЗ-968М", 1979));
        storage.add(new Avto("Таврия", 1987));
        storage.add(new Avto("ZAZ Sens", 2000));
        storage.add(new Avto("ZAZ Lanos", 2004));


        System.out.println("storage:");
        //проходим по сторажу циклом фор ейч
        for (Avto avto : storage) {
            System.out.println(avto);
        }

        System.out.println();
        System.out.println("объектов в storage: " + storage.size());
        System.out.println("текущая ёмкость storage: " + storage.getCapacity());
    }
}
