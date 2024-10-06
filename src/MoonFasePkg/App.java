package MoonFasePkg;

import java.time.LocalDate;

/**
 * Aplicação que processa um objeto LocalDate e informa a fase lunar deste.
 * @author Daniel Machado da Rocha
 * @version 0.1
 * @since 28/9/2024
 */

public class App implements I_MoonFase{
    public static LocalDate dateRef;

    /**
     * Método que analiza se o LocalDate é valido, sendo valido a variável static dataRef é setada.
     * @param day dia
     * @param month mês
     * @param year ano
     * @return boleano com a definição se a data informada é ou não valida.
     */
    public static boolean setMoonDate(int day,int month, int year){
        try{
            dateRef = LocalDate.of(year,month,day);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Método que aplica o algoritmo matemático.
     * @param dateRef Data para processamento.
     * @return Resultado do processamento como parametro da função lua, que retorna o Enum da fase em questão.
     */
    private Fase calculoDaFase(LocalDate dateRef){
        LocalDate dataReference = dateRef;

        int day = dataReference.getDayOfMonth();
        int month = dataReference.getMonthValue();
        int year = yearPreparation(dataReference.getYear());

        int algoritmoLunar = (((year+2)*11)+day+month) % 30;

        return lua(algoritmoLunar);
    }

    /**
     * Método que seleciona o Enum conforme o resultado do algoritmo lunar.
     * @param algoLunar Algoritmo proveniente da função calculoDaFase.
     * @return O Enum com a fase lunar.
     */
    private Fase lua(int algoLunar){
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

    /**
     * Método que verifica o ano, pois o algoritmo é diferente se o ano for menor que 2000.
     * @param year ano
     * @return Ano retificado para o processamento do algoritmo.
     */
    private int yearPreparation( int year){

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
