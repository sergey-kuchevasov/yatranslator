import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class yatranslator {
    public static void main(String[] args) throws IOException {
        System.out.println(translate("ru", "Кошка"));
    }

    private static String translate(String lang, String input) throws IOException {
        String translated;
        String urlYandexTranslateString = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170423T102225Z.850312bf3597d67e.a8bfbebb0b6bd6b0a5cdd90c5af5830ef5303947";
        URL urlObject = new URL(urlYandexTranslateString);
        HttpsURLConnection connection = (HttpsURLConnection)urlObject.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("&text=" + URLEncoder.encode(input,"UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        translated = json.substring(start + 2, end -1);
        connection.disconnect();
        return translated;
    }
}
