package com.dybowski_andrzej;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReaderController {
    @GetMapping("/")
    public String indexPage(Model model) {
        String address = "https://api.github.com/users/allegro/repos";

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

        model.addAttribute("lastRepository", repositories.getLastModifiedRepository().getName());

        model.addAttribute("name", repositories.getLastModifiedRepository().getName());
        return "index";
    }
}
