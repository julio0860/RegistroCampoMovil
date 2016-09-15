package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 10/05/2016.
 */
public class T_Usuario {

    public static final String TABLE_NAME="Usuario";
    public static final String FIELD_ID="Id";
    public static final String FIELD_USUARIO="Usu_Nombre";
    public static final String FIELD_PASSWORD="Usu_Password";
    public static final String FIELD_SINCRONIZADO="Usu_Sincronizar";
    public static final String FIELD_ESTADO="Est_Id";

    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_ID +" integer primary key,"  +
            FIELD_USUARIO+" text, "+
            FIELD_PASSWORD+" text, "+
            FIELD_SINCRONIZADO+" integer,"+
            FIELD_ESTADO+" integer"
            +");"  ;

    public static String _INSERT(Integer ID,String CODIGO,String PASSWORD, Integer SINCRONIZADO,Integer ESTADO )

    {
        String _INSERT;
        _INSERT = "INSERT INTO "+TABLE_NAME +"("+FIELD_ID+","+FIELD_USUARIO+","+FIELD_PASSWORD+","+FIELD_SINCRONIZADO+","
                +FIELD_ESTADO+
                ")VALUES('"+ID+"','"+CODIGO+"','"+PASSWORD+"','"+SINCRONIZADO +"','"+ESTADO+"')";

        return _INSERT;
    }

}
