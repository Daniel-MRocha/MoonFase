package MoonFasePkg;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class App_MoonFase {

    public static String calculateOfFase(String dateRef){
        try {
            LocalDate dataReference = LocalDate.parse(dateRef);

            int day = dataReference.getDayOfMonth();
            int month = dataReference.getMonthValue();
            int year = yearPreparation(dataReference.getYear());

            int algoritmoLunar = (((year + 2) * 11) + day + month) % 30;

            return moon(algoritmoLunar).name();
        }catch (DateTimeParseException e){
            System.out.println(e);
            return null;
        }
    }
    private static E_Fase moon(int algoLunar){
        E_Fase result = null;
        if(algoLunar>=0 && algoLunar <=7){
            result = E_Fase.NOVA;
        }else if(algoLunar > 7 && algoLunar <= 14){
            result = E_Fase.CRESCENTE;
        }else if(algoLunar > 14 && algoLunar <=21){
            result = E_Fase.CHEIA;
        }else if(algoLunar>21 && algoLunar <=29){
            result = E_Fase.MINGUANTE;
        }
        return result;
    }
    private static int yearPreparation( int year){

        int auxAno = year;

        if(auxAno < 2000) {
            while (auxAno < 2000) {
                auxAno += 19;
            }
        }
        return auxAno - 2000;
    }

}
