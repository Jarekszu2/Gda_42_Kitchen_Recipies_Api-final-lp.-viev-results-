package packKitchenRecipies.packURL;

public class URLBilder {
    private static final String BASE_URL = "http://www.recipepuppy.com/api/?i=";
    private final StringBuilder builder;

    public URLBilder() {
        builder = new StringBuilder(BASE_URL);
    }

    public void appendIngredients(URLQuestionParameters parameters) {
        for (int i = 0; i < parameters.getIngredients().size(); i++) {
            builder.append(parameters.getIngredients().get(i));
            builder.append(",");
        }
    }

    public void appendPageNumber(URLQuestionParameters parameters) {
        builder.append("&p=");
        builder.append(parameters.getPageNumber());
    }

    public void buildBuilder(URLQuestionParameters parameters) {
        appendIngredients(parameters);
        appendPageNumber(parameters);
    }

    public String createURLQuestion() {
        return builder.toString();
    }
}
