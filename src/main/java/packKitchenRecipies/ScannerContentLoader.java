package packKitchenRecipies;

import packKitchenRecipies.packResponse.ResponseStore;
import packKitchenRecipies.packURL.URLQuestionParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ScannerContentLoader {
    private Scanner scanner = new Scanner(System.in);

    public int getIntFromRange(String text, int min, int max) {
        int result;
        boolean flag = false;
        do {
            System.out.print(text);
            System.out.println(" z zakresu od: " + min + " do: " + max + ":");
            while (!scanner.hasNextInt()) {
                System.out.println("Podaj liczbę całkowitą!");
                scanner.next();
            }
            result = scanner.nextInt();
            if (result < min || result > max) {
                System.out.println("Liczba spoza zakresu!");
            } else {
                flag = true;
            }
        } while (!flag);
        return result;
    }

    public int getIntfromSet(ResponseStore responseStore) {
        int result;
        boolean flag = false;
        do {
            System.out.println();
            System.out.print("Potwierdź nr strony (dostępne strony:");
            responseStore.getStore().keySet().stream()
                    .forEach(integer -> System.out.print(" " + integer + ","));
            System.out.print(").");
            while (!scanner.hasNextInt()) {
                System.out.println("Podaj liczbę całkowitą!");
                scanner.next();
            }
            result = scanner.nextInt();
            if (responseStore.getStore().containsKey(result)) {
                flag = true;
            } else {
                System.out.println("Błędny nr strony!");
            }
        } while (!flag);
        return result;
    }

    public String getString(String textToDisplay) {
        System.out.println("Podaj " + textToDisplay + ":");
        String result = scanner.next();
        return result;
    }

    public void setListOfIngredients(URLQuestionParameters parameters) {
        List<String> ingredients = new ArrayList<>();
        System.out.println("Podaj nazwy składników potrawy(po angielsku), (litera 'k' - kończy podawanie składników).");
        boolean flag = false;
        do {
            String ingredient = getString("nazwę składnika, ('k' - kończy podawanie składników)");
            if (!ingredient.equalsIgnoreCase("k")) {
                ingredients.add(ingredient);
//                ingredients.add(",");
            } else {
                flag = true;
            }
        } while (!flag);
        parameters.setIngredients(ingredients);
    }

    public void setPageNumber(URLQuestionParameters parameters) {
        int number = getIntFromRange("Podaj żądany nr strony z przepisami",1, 100);
        parameters.setPageNumber(number);
    }

    public char decisionIfMoreRecipies() {
        System.out.println("Czy wybrałas/eś przepis - naciśnij 't', czy chcesz zobaczyć kolejną stronę - naciśnij 's':");
        char znak = 'a';
        boolean flag = false;
        do {
            znak = scanner.next().charAt(0);
            if (znak != 't' && znak != 's') {
                getString("'t' lub 's'");
            } else {
                flag = true;
            }
        } while (!flag);
        return znak;
    }

    public char choice() {
        System.out.println();
        System.out.println("Dokonaj wyboru:" +
                "\n jeśli wybierasz przepis z tej listy - naciśnij 't'," +
                "\n jeśli chcesz wczytać nową stronę z przepisami - naciśnij 'n'," +
                "\n jeśli chcesz przejrzeć ponownie dostępne strony - naciśnij 's'.");
        boolean flag = false;
        char znak = 'a';
        do {
//            System.out.println();
            znak = scanner.next().charAt(0);
            if (znak == 't' || znak == 'n' || znak == 's') {
                flag = true;
            } else {
                System.out.println("Dokonaj poprawnego wyboru!");
            }
        } while (!flag);
//        System.out.println(znak);
        return znak;
    }

    public int choisePageNumberFromMap(ResponseStore responseStore) {
        int result;
        boolean flag = false;
        do {
            System.out.println("Wybierz żądany nr strony z listy");
            while (!scanner.hasNextInt()) {
                System.out.println("Podaj liczbę całkowitą!");
                scanner.next();
            }
            result = scanner.nextInt();
            if(responseStore.getStore().containsKey(result)) {
                flag = true;
            } else {
                System.out.println("Wybierz nr strony z podanej listy!");
            }
        } while (!flag);
        return result;
    }
}
