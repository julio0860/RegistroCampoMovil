package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 13/05/2016.
 */
public class T_Sucural {
    public static final String TABLE_NAME="Sucursal";
    public static final String FIELD_ID="Suc_Id";
    public static final String FIELD_DESCRIPCION="Suc_Descripcion";
    public static final String FIELD_ZONA="Suc_Zona";

    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_ID +" integer primary key,"  +
            FIELD_DESCRIPCION+" text, "+
            FIELD_ZONA+" text "
            +");"  ;
    public static String _INSERT(Integer ID,String DESCRIPCION,String ZONA )
    {
        String _INSERT;
        _INSERT = "INSERT INTO "+TABLE_NAME +"("+FIELD_ID+","+FIELD_DESCRIPCION+","
                +FIELD_ZONA+
                ")VALUES('"+ID+"','"+DESCRIPCION+"','"+ZONA+"')";
        return _INSERT;
    }
}
