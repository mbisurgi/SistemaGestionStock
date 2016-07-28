package com.zeonsoft.entities.tango;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "CPA04")
public class ComprobanteTangoCpa {
    @Id
    @Column(name = "ID_CPA04")
    private int id_cpa04;
    @Column(name = "T_COMP")
    private String t_comp;
    @Column(name = "N_COMP")
    private String n_comp;
    @Column(name = "FECHA_EMIS")
    private Date fecha_emis;
    @Column(name = "COD_PROVEE")
    private String cod_provee;

    public ComprobanteTangoCpa() {
    }

    public ComprobanteTangoCpa(int id_cpa04, String t_comp, String n_comp, Date fecha_emis, String cod_provee) {
        this.id_cpa04 = id_cpa04;
        this.t_comp = t_comp;
        this.n_comp = n_comp;
        this.fecha_emis = fecha_emis;
        this.cod_provee = cod_provee;
    }

    public int getId_cpa04() {
        return id_cpa04;
    }

    public void setId_cpa04(int id_cpa04) {
        this.id_cpa04 = id_cpa04;
    }

    public String getT_comp() {
        return t_comp;
    }

    public void setT_comp(String t_comp) {
        this.t_comp = t_comp;
    }

    public String getN_comp() {
        return n_comp;
    }

    public void setN_comp(String n_comp) {
        this.n_comp = n_comp;
    }

    public Date getFecha_emis() {
        return fecha_emis;
    }

    public void setFecha_emis(Date fecha_emis) {
        this.fecha_emis = fecha_emis;
    }

    public String getCod_provee() {
        return cod_provee;
    }

    public void setCod_provee(String cod_provee) {
        this.cod_provee = cod_provee;
    }
}
