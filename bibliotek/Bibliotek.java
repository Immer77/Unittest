package bibliotek;

import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bibliotek {


    public int beregnboede(LocalDate beregnetDato, LocalDate faktiskDato, boolean isVoksen){
        int result = 0;
        if(beregnetDato.isAfter(faktiskDato)){
            throw new RuntimeException("beregnet dato skal komme foer faktiskdato");
        }
        if(faktiskDato.isAfter(beregnetDato)){
            if(ChronoUnit.DAYS.between(beregnetDato,faktiskDato) >= 1 && ChronoUnit.DAYS.between(beregnetDato,faktiskDato) <= 7){
                if(isVoksen){
                    result = 20;
                }else{
                    result = 10;
                }
            }else if(ChronoUnit.DAYS.between(beregnetDato,faktiskDato) >= 8 && ChronoUnit.DAYS.between(beregnetDato,faktiskDato) <= 15) {
                if (isVoksen) {
                    result = 60;
                } else {
                    result = 30;
                }
            }else {
                if (isVoksen) {
                    result = 90;
                } else {
                    result = 45;
                }
            }
        }
        return result;
    }

}
