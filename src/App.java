import java.time.LocalDate;


public class App implements I_MoonFase{


    public static Fase calculoDaFase(LocalDate dateRef){
        LocalDate dataReference = dateRef;

        int day = dataReference.getDayOfMonth();
        int month = dataReference.getMonthValue();
        int year = yearPreparation(dataReference.getYear());

        int algoritmoLunar = (((year+2)*11)+day+month) % 30;

        return lua(algoritmoLunar);
    }

    public static Fase lua(int algoLunar){
        Fase result = null;
        if(algoLunar>=0 && algoLunar <=7){
            result = Fase.NOVA;
        }else if(algoLunar > 7 && algoLunar <= 14){
            result = Fase.CRESCENTE;
        }else if(algoLunar > 14 && algoLunar <=21){
            result = Fase.CHEIA;
        }else if(algoLunar>21 && algoLunar <=28){
            result = Fase.MINGUANTE;
        }
        return result;
    }

    public static int yearPreparation( int year){

        int auxAno = year;

        if(auxAno < 2000) {
            while (auxAno < 2000) {
                auxAno += 19;
            }
        }
        return auxAno - 2000;
    }

    @Override
    public String faseToday(LocalDate dr) {
        return calculoDaFase(dr).toString();
    }
}
