package packKitchenRecipies.packResponse;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONwork {
    public final static Gson GSON = new Gson();

    public Response getResponseByInputStream(String urlQuestion) {
        Response response = null;
        try {
            URL url = new URL(urlQuestion);
//            InputStream inputStream = url.openStream();
            response = GSON.fromJson(new InputStreamReader(url.openStream()), Response.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
