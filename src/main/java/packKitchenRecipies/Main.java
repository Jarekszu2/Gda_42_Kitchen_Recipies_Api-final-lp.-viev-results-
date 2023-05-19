package packKitchenRecipies;

import packKitchenRecipies.packResponse.JSONwork;
import packKitchenRecipies.packResponse.Response;
import packKitchenRecipies.packResponse.ResponseStore;
import packKitchenRecipies.packURL.URLBilder;
import packKitchenRecipies.packURL.URLQuestionParameters;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Program służy do wyboru przpisu kulinarnego dla zadanych składników.");
        System.out.println();
        System.out.println();

        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();
        URLQuestionParameters urlQuestionParameters = new URLQuestionParameters();
        URLBilder urlBilder = new URLBilder();
        ResponseStore responseStore = new ResponseStore();

        // załadowanie pierwszej strony z przepisami i dodanie jej do mapy wyników
        Response response = createAnswer(scannerContentLoader, urlQuestionParameters, urlBilder);
        response.addResultsToResultsMapAtStote(urlQuestionParameters, responseStore);
        responseStore.vievResults(urlQuestionParameters, urlQuestionParameters.getPageNumber());

        // użytkownik ma możliwość dodawania dowolnej ilości stron (do 100) z przepisami dla wybranych składników,
        // a następnie dowolnego ich przeglądania w celu wybrania odpowiedniego przepisu;
        boolean flag = false;
        char znak = 'a';
        String href = "";
        int key = 0;
        int nummer = 0;
        do {
            // podjęcie decyzji czy wybieram przepis z listy jaką mam, czy szukam innych możliwości (innych list przepisów)
            System.out.println();
            System.out.println("Podejmij decyzję: t, n, s:");
            znak = scannerContentLoader.choice();
            switch (znak) {
                case 'n':
                    // ściagam z API Nową stronę i dodaje do mapy wyników
                    boolean flagA = false;
                    do {
                        System.out.println();
                        // wybieram nr nowej strony z wynikami
                        int pageA = scannerContentLoader.getIntFromRange("Podaj żądany nr strony z przepisami", 1, 100);
                        // sprzwdzam, czy jest już ta strona w mapie
                        if (responseStore.getStore().containsKey(pageA)) {
                            System.out.println("Ta strona już jest w bazie danych!");
                        } else {
                            // ustawiam nowy nr strony jako parametr zapytania
                            urlQuestionParameters.setPageNumber(pageA);
                            // ściągam odp z API i dodaję do mapy ze stronami przepisów
                            Response responseA = createResponse(urlQuestionParameters, urlBilder);
                            responseA.addResultsToResultsMapAtStote(urlQuestionParameters, responseStore);
                            responseStore.vievResults(urlQuestionParameters, urlQuestionParameters.getPageNumber());
                            flagA = true;
                        }
                    } while (!flagA);
                    break;
                case 's':
                    // przeglądam zapisane Strony z przepisami kulinarnymi
                    responseStore.vievSetOfAvailableRecipiesPages();
                    int pageNumber = scannerContentLoader.choisePageNumberFromMap(responseStore);
                    responseStore.vievResults(urlQuestionParameters, pageNumber);
                    break;
                case 't':
                    // wybieram konkretny przepis ze strony
                    boolean flagT = false;
                    // potwierdzam nr wybranej strony (by uniknąć pomyłki) i sprawdzam, czy wybrana strona jest w mapie (by uniknąć pomyłki)
                    do {
                        key = scannerContentLoader.getIntfromSet(responseStore);
                        if (responseStore.getStore().containsKey(key)) {
                            flagT = true;
                        } else {
                            System.out.println("Błędny nr strony!");
                        }
                    } while (!flagT);
                    System.out.println();
                    // wybieram nr przepisu
                    nummer = scannerContentLoader.getIntFromRange("Podaj numer przepisu",1, 10);
                    System.out.println(responseStore.getStore().get(key).get(nummer - 1).getTitle());
                    href = responseStore.getStore().get(key).get(nummer - 1).getHref();
                    flag = true;
                    break;
            }
        } while (!flag);

        System.out.println(href);

        // wyświetla URLadress w nowym oknie przeglądarki
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(href));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    // ustala parametry zapytania do API i zwraca odpowiedź
    private static Response createAnswer(ScannerContentLoader scannerContentLoader, URLQuestionParameters urlQuestionParameters, URLBilder urlBilder) {
        scannerContentLoader.setListOfIngredients(urlQuestionParameters);
        scannerContentLoader.setPageNumber(urlQuestionParameters);

        return createResponse(urlQuestionParameters, urlBilder);
    }

    // zwraca odpowiedź API gdy mamy parametry zapytania
    private static Response createResponse(URLQuestionParameters urlQuestionParameters, URLBilder urlBilder) {
        urlBilder.buildBuilder(urlQuestionParameters);
        String urlQuestion = urlBilder.createURLQuestion();

        JSONwork jsoNwork = new JSONwork();

        Response response = jsoNwork.getResponseByInputStream(urlQuestion);

        return response;
    }
}
