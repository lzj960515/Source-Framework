import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HealthCheck {

    public static void main(String[] args) {
        if (args == null || args.length == 0){
            System.exit(0);
        }
        String url = args[0];
        if (check(url)) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

    public static boolean check(String url) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            var client = java.net.http.HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
