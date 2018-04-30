package com.dybowski_andrzej;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderController {
    @GetMapping("/")
    @ResponseBody
    public String indexPage(@RequestParam(defaultValue = "https://api.github.com/users/allegro/repos", name = "url") String address, Model model) {

        RestClient client = new RestClient(address);
        JsonParser parser = new JsonParser();

        String rawData = client.get();
        parser.addRepositoriesFromRawJsonBody(rawData);

        HeaderLinkParser linkHeaderParser = new HeaderLinkParser(client.getResponseHeaders().get("Link").get(0));
        while (linkHeaderParser.isKeyExist("next")) {
            client = new RestClient(linkHeaderParser.getLink("next"));
            rawData = client.get();
            parser.addRepositoriesFromRawJsonBody(rawData);
            linkHeaderParser = new HeaderLinkParser(client.getResponseHeaders().get("Link").get(0));;
        }

        RepositoriesContainer repositories = parser.getRepositories();

        model.addAttribute("lastRepository", repositories.getLastModifiedRepository().getName());

        return repositories.getLastModifiedRepository().getName();
    }
}
