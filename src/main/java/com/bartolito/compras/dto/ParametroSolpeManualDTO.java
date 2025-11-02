package com.bartolito.compras.dto;

public class ParametroSolpeManualDTO {
    private String codtip;
    private String codlab;
    private String codgen;
    private String estr;
    private String pet;

    public ParametroSolpeManualDTO() {
    }

    public ParametroSolpeManualDTO(String codtip, String codlab, String codgen, String estr, String pet) {
        this.codtip = codtip;
        this.codlab = codlab;
        this.codgen = codgen;
        this.estr = estr;
        this.pet = pet;
    }

    public String getCodtip() {
        return codtip;
    }

    public void setCodtip(String codtip) {
        this.codtip = codtip;
    }

    public String getCodlab() {
        return codlab;
    }

    public void setCodlab(String codlab) {
        this.codlab = codlab;
    }

    public String getCodgen() {
        return codgen;
    }

    public void setCodgen(String codgen) {
        this.codgen = codgen;
    }

    public String getEstr() {
        return estr;
    }

    public void setEstr(String estr) {
        this.estr = estr;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }
}
