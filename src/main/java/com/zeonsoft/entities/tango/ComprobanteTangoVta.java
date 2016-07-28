package com.zeonsoft.entities.tango;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "GVA12")
public class ComprobanteTangoVta {
    @Id
    @Column(name = "ID_GVA12")
    private int id_gva12;
    @Column(name = "T_COMP")
    private String t_comp;
    @Column(name = "N_COMP")
    private String n_comp;
    @Column(name = "FECHA_EMIS")
    private Date fecha_emis;
    @Column(name = "COD_CLIENT")
    private String cod_client;

    public ComprobanteTangoVta() {
    }

    public ComprobanteTangoVta(int id_gva12, String t_comp, String n_comp, Date fecha_emis, String cod_client) {
        this.id_gva12 = id_gva12;
        this.t_comp = t_comp;
        this.n_comp = n_comp;
        this.fecha_emis = fecha_emis;
        this.cod_client = cod_client;
    }

    public int getId_gva12() {
        return id_gva12;
    }

    public void setId_gva12(int id_gva12) {
        this.id_gva12 = id_gva12;
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

    public String getCod_client() {
        return cod_client;
    }

    public void setCod_client(String cod_client) {
        this.cod_client = cod_client;
    }
}
