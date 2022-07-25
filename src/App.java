import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        // fazer uma conexão HTTP e buscar os Top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        //String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=<API_KEY>&language=en-US&page=1";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";
            
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Gerando título: " + titulo);
        }
    }
}
