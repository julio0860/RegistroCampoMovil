package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 11/05/2016.
 */
public class T_Menu {

    public static final String TABLE_NAME="Menu";
    public static final String FIELD_CODIGO="Men_Codigo";
    public static final String FIELD_DESCRIPCION="Men_Descripcion";
    public static final String FIELD_MENBOTON="Men_Form";
    public static final String FIELD_ANTERIOR="Men_Anterior";
    public static final String FIELD_MODULO="Men_Modulo";
    public static final String FIELD_ESTADO="Est_Id";


    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_CODIGO +" text primary key,"  +
            FIELD_DESCRIPCION+" text, "+
            FIELD_MENBOTON+" text, "+
            FIELD_ANTERIOR+" text, "+
            FIELD_MODULO+" text, "+
            FIELD_ESTADO+" integer"
            +");"  ;

    public static String _INSERT(String Codigo,String Descripcion,String MenBoton, String Anterior,String Modulo,Integer Estado )

    {
        String _INSERT;
        _INSERT = "INSERT INTO "+TABLE_NAME +"("+FIELD_CODIGO+","+FIELD_DESCRIPCION+","+FIELD_MENBOTON+","+FIELD_ANTERIOR+","+FIELD_MODULO+","
                +FIELD_ESTADO+
                ")VALUES('"+Codigo+"','"+Descripcion+"','"+MenBoton+"','"+Anterior +"','"+Modulo+"','"+Estado+"')";

        return _INSERT;
    }
}
