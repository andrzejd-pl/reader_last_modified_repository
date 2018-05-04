package com.dybowski_andrzej;

import com.dybowski_andrzej.http.HeaderLinkParser;
import com.dybowski_andrzej.http.RestClient;
import com.dybowski_andrzej.json.JsonParser;
import com.dybowski_andrzej.repository.RepositoriesContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderController {
    @GetMapping("/")
    @ResponseBody
    public String indexPage() {
        String address = "https://api.github.com/orgs/allegro/repos";

        RestClient client = new RestClient(address);
        JsonParser parser = new JsonParser();

        String rawData = client.get();
        parser.addRepositoriesFromRawJsonBody(rawData);

        if (client.getResponseHeaders().containsKey("Link")) {
            HeaderLinkParser linkHeaderParser = new HeaderLinkParser(client.getResponseHeaders().get("Link").get(0));
            while (linkHeaderParser.isKeyExist("next")) {
                client = new RestClient(linkHeaderParser.getLink("next"));
                rawData = client.get();
                parser.addRepositoriesFromRawJsonBody(rawData);
                linkHeaderParser = new HeaderLinkParser(client.getResponseHeaders().get("Link").get(0));
            }
        }

        RepositoriesContainer repositories = parser.getRepositories();
        return repositories.getLastModifiedRepository().getName();
    }
}
