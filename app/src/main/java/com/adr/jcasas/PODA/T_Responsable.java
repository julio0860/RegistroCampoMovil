package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 13/05/2016.
 */
public class T_Responsable {

    public static final String TABLE_NAME="Responsable";
    public static final String FIELD_ID="Res_Id";
    public static final String FIELD_CODIGO="Res_Codigo";
    public static final String FIELD_DESCRIPCION="Res_Descripcion";
    public static final String FIELD_ESTADO="Est_Id";

    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_ID +" integer primary key,"  +
            FIELD_CODIGO+" text, "+
            FIELD_DESCRIPCION+" text, "+
            FIELD_ESTADO+" integer"
            +");"  ;
    public static String _INSERT(Integer ID,String CODIGO,String DESCRIPCION, Integer ESTADO )

    {
        String _INSERT;
        _INSERT = "INSERT INTO "+TABLE_NAME +"("+FIELD_ID+","+FIELD_CODIGO+","+FIELD_DESCRIPCION+","
                +FIELD_ESTADO+
                ")VALUES('"+ID+"','"+CODIGO+"','"+DESCRIPCION+"','"+ESTADO+"')";

        return _INSERT;
    }
}
