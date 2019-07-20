package packKitchenRecipies.packResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Results {
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;
}
