package packKitchenRecipies.packResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import packKitchenRecipies.packURL.URLQuestionParameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ResponseStore {
    private Map<Integer, List<Results>> store;
//    private List<Integer> listPageNumbers;


    public ResponseStore() {
        store = new HashMap<>();
    }

    public void vievSetOfAvailableRecipiesPages() {
        System.out.println();
        System.out.println("Dostępne strony z przepisami (nr): ");
        store.keySet()
                .stream()
                .forEach(integer -> System.out.print(integer + ", "));
        System.out.println();
    }

    public void vievResults(URLQuestionParameters urlQuestionParameters, int pageNumber) {
        System.out.println();
        System.out.print("Lista przepisów kulinarnych dań z: ");
        urlQuestionParameters.getIngredients().stream()
                .forEach(s -> System.out.print(s + ", "));
        System.out.print(" strona nr " + pageNumber + ":");
        System.out.println();
        System.out.println();
        final int[] number = new int[] {1};

        store.get(pageNumber).stream()
                .map(results -> results.getTitle())
                .forEach(s -> System.out.println((number[0]++) + ". " + s.trim().replace("\r", "").replace("\t", "").replace("\n", "")));

    }
}
