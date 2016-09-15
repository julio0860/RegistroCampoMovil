package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 10/05/2016.
 */
public class Funciones {

    private static String CadenaEncriptada  = "|°!#$%&/()=?¡'¿<}~ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑª¿®¦ÁÂÀ©¦ãÃ.j+";
    private static String CadenaDesencriptada   = "ABCDEFGHIJKLLLMNOPQRSTUVWXYZabcdefrgihklllmnopqrstuvwxyz1234567890 .j+";


    public static String Encriptar(String Texto)
            {
                String TextoEncriptado="";
            for (int i=1;i<=Texto.trim().length();i++)
                {

                    TextoEncriptado=TextoEncriptado+CadenaEncriptada.substring(CadenaDesencriptada.indexOf(Texto.substring(i-1,i)),(CadenaDesencriptada.indexOf(Texto.substring(i-1,i)))+1);
                }
                return TextoEncriptado;
            }
    public static Boolean Habilitar(String Valor)
    {
             boolean Habilitar;
        if (Valor.equals("1")){
            Habilitar=true;
        }
        else
        {
            Habilitar=false;
        }
        return  Habilitar;
    }


}
