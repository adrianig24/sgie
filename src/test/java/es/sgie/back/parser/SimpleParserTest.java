package es.sgie.back.parser;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

public class SimpleParserTest {

    @Test
    public void parseHtmlEngReport() {
        try {
            File f = new ClassPathResource("reports/GR_eng.html").getFile();
            HtmlReportParser pru = new HtmlReportParser(f);

            TestCase.assertEquals(pru.getName(), "GR");
            TestCase.assertEquals(pru.getCode(), "28/11/2016 20:55:28 A4880F0F");
            TestCase.assertEquals(pru.getGender(), "female");
            TestCase.assertEquals(pru.getAge(), 60);
            TestCase.assertEquals(pru.getWeight(), 54f);
            TestCase.assertEquals(pru.getRespRate(), 18f);
            TestCase.assertEquals(pru.getAtmPres(), 741.7f);
            TestCase.assertEquals(pru.getLCA(), 34.85f);
            TestCase.assertEquals(pru.getRCA(), 34.57f);
            TestCase.assertEquals(pru.getLAC(), 35.2f);
            TestCase.assertEquals(pru.getRAC(), 35.43f);
            TestCase.assertEquals(pru.getABD(), 35.05f);
            TestCase.assertEquals(pru.getUNK(), 175.1f);
            TestCase.assertEquals(pru.getStatus(), "99999");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
