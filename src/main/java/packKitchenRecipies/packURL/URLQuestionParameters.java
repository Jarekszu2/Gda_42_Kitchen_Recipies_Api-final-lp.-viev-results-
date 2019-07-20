package packKitchenRecipies.packURL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import packKitchenRecipies.packResponse.ResponseStore;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class URLQuestionParameters {
    private List<String> ingredients;
    private int pageNumber;

}
