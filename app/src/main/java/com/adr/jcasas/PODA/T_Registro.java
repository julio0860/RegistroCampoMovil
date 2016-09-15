package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 25/04/2016.
 */
public class T_Registro {
    public static final String TABLE_NAME="Registro";
    public static final String FIELD_IDREGISTRO="Id_Registo";
    public static final String FIELD_LOTE="Lote";
    public static final String FIELD_ID="Id";
    public static final String FIELD_FECHA="Fecha";
    public static final String FIELD_REGISTRADOR="Registrador";
    public static final String FIELD_CARGADORES="Cargadores";
    public static final String FIELD_FRC="Frc";
    public static final String FIELD_YEMAS="Yemas";
    public static final String FIELD_PROMY="Promy";
    public static final String FIELD_FRY="Fry";
    public static final String FIELD_PITONES="Pitones";
    public static final String FIELD_FRP="Frp";
    public static final String FIELD_SUPERVISOR="Supervisor";
    public static final String FIELD_UBICACION="Ubicacion";
    public static final String FIELD_FALTA="Falta";
    public static final String FIELD_LIMPIEZA="Limpieza";
    public static final String FIELD_ZONA="Zona";
    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_IDREGISTRO +" integer primary key autoincrement, "  +
            FIELD_LOTE+" text, "+
            FIELD_ID+" text, "+
            FIELD_FECHA+" text, "+
            FIELD_REGISTRADOR+" text,"+
            FIELD_CARGADORES+" integer,"+
            FIELD_FRC+" integer,"+
            FIELD_YEMAS+" integer,"+
            FIELD_PROMY+" decimal(18, 3),"+
            FIELD_FRY+" integer,"+
            FIELD_PITONES+" integer,"+
            FIELD_FRP+" integer,"+
            FIELD_SUPERVISOR+" text, "+
            FIELD_UBICACION+" integer,"+
            FIELD_FALTA+" integer,"+
            FIELD_LIMPIEZA+" integer,"+
            FIELD_ZONA+ " text "
            +");"  ;




}
