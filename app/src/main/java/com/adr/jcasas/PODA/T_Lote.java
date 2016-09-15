package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 13/05/2016.
 */
public class T_Lote {

    public static final String TABLE_NAME="Lote";
    public static final String FIELD_ID="Con_Id";
    public static final String FIELD_IDSUC="Suc_Id";
    public static final String FIELD_DESCRIPCION="Con_Descripcion";
    public static final String FIELD_DESCRIPCIONCOR="Con_DescripcionCor";
    public static final String FIELD_ESTID="Est_Id";

    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_ID +" integer primary key,"  +
            FIELD_IDSUC+" integer, "+
            FIELD_DESCRIPCION+" text, "+
            FIELD_DESCRIPCIONCOR+" text, "+
            FIELD_ESTID+" integer "
            +");"  ;

    public static String _INSERT(Integer ID,Integer IDSUC,String DESCRIPCION,String DESCRIPCIONCOR ,Integer ESTADO )

    {
        String _INSERT;
        _INSERT = "INSERT INTO "+TABLE_NAME +"("+FIELD_ID+","+FIELD_IDSUC+","+FIELD_DESCRIPCION+","+FIELD_DESCRIPCIONCOR+","
                +FIELD_ESTID+
                ")VALUES('"+ID+"','"+IDSUC+"','"+DESCRIPCION+"','"+DESCRIPCIONCOR+"','"+ESTADO+"')";

        return _INSERT;
    }
}
