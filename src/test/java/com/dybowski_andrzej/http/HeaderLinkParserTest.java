package com.dybowski_andrzej.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeaderLinkParserTest {

    @Test
    void isKeyExist() {
        String header = " <https://api.github.com/user/562236/repos?page=2>; rel=\"next\", <https://api.github.com/user/562236/repos?page=2>; rel=\"last\"";

        HeaderLinkParser parser = new HeaderLinkParser(header);

        Assertions.assertTrue(parser.isKeyExist("next"));
        Assertions.assertTrue(parser.isKeyExist("last"));
    }

    @Test
    void getLink() {
        String header = " <https://api.github.com/user/562236/repos?page=2>; rel=\"next\", <https://api.github.com/user/562236/repos?page=2>; rel=\"last\"";

        HeaderLinkParser parser = new HeaderLinkParser(header);

        Assertions.assertEquals("https://api.github.com/user/562236/repos?page=2", parser.getLink("next"));
        Assertions.assertEquals("https://api.github.com/user/562236/repos?page=2", parser.getLink("last"));}
}