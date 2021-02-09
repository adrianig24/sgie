package es.sgie.back.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

@Slf4j
public class HtmlReportParser {

    private final int NAME_IDX = 4;

    private final int CODE_IDX = 5;

    private final int GENDER_IDX = 0;
    private final String[] GENDER_REPLACE = {"Gender: ", "Género: "};

    private final int AGE_IDX = 1;
    private final String[] AGE_REPLACE = {"Age: ", "Edad: "};

    private final int WEIGHT_IDX = 2;
    private final String[] WEIGHT_REPLACE = {"Weight: ", "Peso: "};

    private final int PULSE_IDX = 3;
    private final String[] PULSE_REPLACE = {"Pulse: ", "Pulso: "};

    private final int RESPRATE_IDX = 4;
    private final String[] RESPRATE_REPLACE = {"Resp.rate: ", "Resp.rate: "}; // TODO spanish

    private final int ATMPRES_IDX = 5;
    private final String[] ATMPRES_REPLACE = {"Atm.pres: ", "Atm.pres: "}; // TODO spanish

    private final int LCA_IDX = 6;
    private final String[] LCA_REPLACE = {"LCA: ", "LCA: "}; // TODO spanish

    private final int RCA_IDX = 7;
    private final String[] RCA_REPLACE = {"RCA: ", "RCA: "}; // TODO spanish

    private final int LAC_IDX = 8;
    private final String[] LAC_REPLACE = {"LAC: ", "LAC: "}; // TODO spanish

    private final int RAC_IDX = 9;
    private final String[] RAC_REPLACE = {"RAC: ", "RAC: "}; // TODO spanish

    private final int ABD_IDX = 10;
    private final String[] ABD_REPLACE = {"ABD: ", "ABD: "}; // TODO spanish

    private final int UNK_IDX = 11;

    private final int STATUS_IDX = 12;

    private Document doc;
    private Elements p;
    private Elements td;

    public HtmlReportParser(File report) {
        try {
            this.doc = Jsoup.parse(report, "UTF-8");
            this.p = this.doc.body().getElementsByTag("P");
            this.td = this.doc.body().getElementsByTag("TD");
        } catch (Exception e) {
            log.error("Error parsing report: " + report.getAbsolutePath() + " in ParseReportUtils", e);
        }
    }

    public String getName() {
        try {
            return this.p.get(this.NAME_IDX).text();
        } catch (Exception e) {
            log.error("Error parsing code ParseReportUtils.getName", e);
            return "Error";
        }
    }

    public String getCode() {
        try {
            return this.p.get(this.CODE_IDX).text();
        } catch (Exception e) {
            log.error("Error parsing code ParseReportUtils.getCode", e);
            return "Error";
        }
    }

    public String getGender() {
        try {
            String gender = this.td.get(this.GENDER_IDX).text();

            for (String s : this.GENDER_REPLACE) {
                gender = gender.replace(s, "");
            }

            return gender;
        } catch (Exception e) {
            log.error("Error parsing gender ParseReportUtils.getGender", e);
            return "Error";
        }
    }

    public int getAge() {
        try {
            String age = this.td.get(this.AGE_IDX).text();

            for (String s : this.AGE_REPLACE) {
                age = age.replace(s, "");
            }

            return Integer.parseInt(age);
        } catch (Exception e) {
            log.error("Error parsing age ParseReportUtils.getAge", e);
            return -1;
        }
    }

    public float getWeight() {
        try {
            String weight = this.td.get(this.WEIGHT_IDX).text();

            for (String s : this.WEIGHT_REPLACE) {
                weight = weight.replace(s, "");
            }

            weight = weight.replace(",", ".");
            return Float.parseFloat(weight);
        } catch (Exception e) {
            log.error("Error parsing weight ParseReportUtils.getWeight", e);
            return -1.0f;
        }
    }

    public int getPulse() {
        try {
            String pulse = this.td.get(this.PULSE_IDX).text();

            for (String s : this.PULSE_REPLACE) {
                pulse = pulse.replace(s, "");
            }

            return Integer.parseInt(pulse);
        } catch (Exception e) {
            log.error("Error parsing pulse ParseReportUtils.getPulse", e);
            return -1;
        }
    }

    public float getRespRate() {
        try {
            String resprate = this.td.get(this.RESPRATE_IDX).text();

            for (String s : this.RESPRATE_REPLACE) {
                resprate = resprate.replace(s, "");
            }

            resprate = resprate.replace(",", ".");
            return Float.parseFloat(resprate);
        } catch (Exception e) {
            log.error("Error parsing respRate ParseReportUtils.getRespRate", e);
            return -1.0f;
        }
    }

    public float getAtmPres() {
        try {
            String atmpres = this.td.get(this.ATMPRES_IDX).text();

            for (String s : this.ATMPRES_REPLACE) {
                atmpres = atmpres.replace(s, "");
            }

            atmpres = atmpres.replace(",", ".");
            return Float.parseFloat(atmpres);
        } catch (Exception e) {
            log.error("Error parsing atmPres ParseReportUtils.getAtmPres", e);
            return -1.0f;
        }
    }

    public float getLCA() {
        try {
            String lca = this.td.get(this.LCA_IDX).text();

            for (String s : this.LCA_REPLACE) {
                lca = lca.replace(s, "");
            }

            lca = lca.replace(",", ".");
            return Float.parseFloat(lca);
        } catch (Exception e) {
            log.error("Error parsing lca ParseReportUtils.getLCA", e);
            return -1.0f;
        }
    }

    public float getRCA() {
        try {
            String rca = this.td.get(this.RCA_IDX).text();

            for (String s : this.RCA_REPLACE) {
                rca = rca.replace(s, "");
            }

            rca = rca.replace(",", ".");
            return Float.parseFloat(rca);
        } catch (Exception e) {
            log.error("Error parsing rca ParseReportUtils.getRCA", e);
            return -1.0f;
        }
    }

    public float getLAC() {
        try {
            String lac = this.td.get(this.LAC_IDX).text();

            for (String s : this.LAC_REPLACE) {
                lac = lac.replace(s, "");
            }

            lac = lac.replace(",", ".");
            return Float.parseFloat(lac);
        } catch (Exception e) {
            log.error("Error parsing lac ParseReportUtils.getLAC", e);
            return -1.0f;
        }
    }

    public float getRAC() {
        try {
            String rac = this.td.get(this.RAC_IDX).text();

            for (String s : this.RAC_REPLACE) {
                rac = rac.replace(s, "");
            }

            rac = rac.replace(",", ".");
            return Float.parseFloat(rac);
        } catch (Exception e) {
            log.error("Error parsing rac ParseReportUtils.getRAC", e);
            return -1.0f;
        }
    }

    public float getABD() {
        try {
            String abd = this.td.get(this.ABD_IDX).text();

            for (String s : this.ABD_REPLACE) {
                abd = abd.replace(s, "");
            }

            abd = abd.replace(",", ".");
            return Float.parseFloat(abd);
        } catch (Exception e) {
            log.error("Error parsing abd ParseReportUtils.getABD", e);
            return -1.0f;
        }
    }

    public float getUNK() {
        try {
            String unk = this.td.get(this.UNK_IDX).text();

            unk = unk.replace(",", ".");
            return Float.parseFloat(unk);
        } catch (Exception e) {
            log.error("Error parsing unk ParseReportUtils.getUNK", e);
            return -1.0f;
        }
    }

    public String getStatus() {
        try {
            return this.td.get(this.STATUS_IDX).text();
        } catch (Exception e) {
            log.error("Error parsing status ParseReportUtils.getStatus", e);
            return "Error";
        }
    }
}
