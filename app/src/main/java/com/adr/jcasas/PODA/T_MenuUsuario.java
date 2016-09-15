package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 11/05/2016.
 */
public class T_MenuUsuario {
    public static final String TABLE_NAME="MenuUsuario";
    public static final String FIELD_ID="MenUsu_Id";
    public static final String FIELD_USUID="Usu_Id";
    public static final String FIELD_MENCOD="Men_Codigo";
    public static final String FIELD_MENPER="MenUsu_Permitido";
    public static final String FIELD_NUEVO="MenUsu_Nuevo";
    public static final String FIELD_EDITAR="MenUsu_Editar";
    public static final String FIELD_ELIMINAR="MenUsu_Eliminar";
    public static final String FIELD_IMPRIMIR="MenUsu_Imprimir";
    public static final String FIELD_APROBAR="MenUsu_Aprobar";
    public static final String FIELD_ANULAR="MenUsu_Anular";
    public static final String FIELD_VERLOGS="MenUsu_VerLogs";
    public static final String FIELD_GUARDAR="MenUsu_Guardar";
    public static final String FIELD_SINCRONIZAR="MenUsu_Sincronizar";


    public static final String CREATE_DB_TABLE="create table "+TABLE_NAME +"( " +
            FIELD_ID +" integer primary key,"  +
            FIELD_USUID+" integer, "+
            FIELD_MENCOD+" text, "+
            FIELD_MENPER+" text, "+
            FIELD_NUEVO+" text, "+
            FIELD_EDITAR+" text, "+
            FIELD_ELIMINAR+" text, "+
            FIELD_IMPRIMIR+" text, "+
            FIELD_APROBAR+" text, "+
            FIELD_ANULAR+" text, "+
            FIELD_VERLOGS+" text, "+
            FIELD_GUARDAR+" text, "+
            FIELD_SINCRONIZAR+" text"
            +");"  ;
    public static String _INSERT(Integer Id,Integer UsuID,String MenCod,String Permitir, String nuevo,String Editar,String Eliminar,
                                String Imprimir,String Aprobar,String Anular,String Verlogs,String Guardar ,String Sincronizar )

    {
        String _INSERT;
        _INSERT = "INSERT INTO "+TABLE_NAME +"("+FIELD_ID+","+FIELD_USUID+","+FIELD_MENCOD+"," +FIELD_MENPER+","+FIELD_NUEVO+","+FIELD_EDITAR+","
                +FIELD_ELIMINAR+","+FIELD_IMPRIMIR+","+FIELD_APROBAR+","+FIELD_ANULAR+","+FIELD_VERLOGS+","+FIELD_GUARDAR+","
                +FIELD_SINCRONIZAR+
                ")VALUES('"+Id+"','"+UsuID+"','"+MenCod+"','" +Permitir+"','"+nuevo +"','"+Editar+"','"+Eliminar+"','"+Imprimir+"','"
                +Aprobar+"','"+Anular+"','"+Verlogs+"','"+Guardar+"','" +Sincronizar+"')";

        return _INSERT;
    }

}
