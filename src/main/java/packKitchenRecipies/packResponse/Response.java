package packKitchenRecipies.packResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import packKitchenRecipies.packURL.URLQuestionParameters;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String title;
    private String version;
    private String href;
    private List<Results> results;

    public void addResultsToResultsMapAtStote(URLQuestionParameters urlQuestionParameters, ResponseStore responseStore) {
        responseStore.getStore().put(urlQuestionParameters.getPageNumber(), results);
    }

}
