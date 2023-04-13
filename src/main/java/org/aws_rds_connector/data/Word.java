package org.aws_rds_connector.data;

import com.google.gson.Gson;

public class Word {
    private String esWord;
    private String zpWord;
    private String esExample;
    private String zpExample;
    private String icon;
    private String meta;

    public String getEsWord() {
        return esWord;
    }

    public String getZpWord() {
        return zpWord;
    }

    public String getEsExample() {
        return esExample;
    }

    public String getZpExample() {
        return zpExample;
    }

    public String getIcon() {
        return icon;
    }

    public String getMeta() {
        return meta;
    }

    public void setEsWord(String esWord) {
        this.esWord = esWord;
    }

    public void setZpWord(String zpWord) {
        this.zpWord = zpWord;
    }

    public void setEsExample(String esExample) {
        this.esExample = esExample;
    }

    public void setZpExample(String zpExample) {
        this.zpExample = zpExample;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
