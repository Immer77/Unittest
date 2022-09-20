package forsikring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BilForsikringTest {

    private BilForsikring bilForsikring = new BilForsikring();

    @BeforeEach
    void setUp() {
        bilForsikring.setGrundpaemie(10000);
    }


    @Test
    void setPraemietest(){
        // Arrange
        var bilforsikring = new BilForsikring();

        // Act
        bilforsikring.setGrundpaemie(5000);

        // Assert
        assertEquals(5000,bilforsikring.getGrundPraemie());
    }
    @Test
    @Order(1)
    void beregnPraemie_femogtyveAarMand() {
        // Arrange
        int alder = 25;
        boolean iskvinde = false;
        int skadefriAar = 0;

        // Act & Assert
        assertEquals(10000,bilForsikring.beregnPraemie(alder,iskvinde,skadefriAar));
    }

    @Test
    @Order(2)
    void beregnPraemie_attenAarkvinde() {
        // Arrange
        int alder = 18;
        boolean iskvinde = true;
        int skadefriAar = 0;

        // Act & Assert
        assertEquals(11875,bilForsikring.beregnPraemie(alder,iskvinde,skadefriAar));
    }

    @Test
    @Order(3)
    void beregnPraemie_seksogtyveaarkvinde() {
        // Arrange
        int alder = 26;
        boolean iskvinde = true;
        int skadefriAar = 0;

        // Act & Assert
        assertEquals(9500,bilForsikring.beregnPraemie(alder,iskvinde,skadefriAar));
    }

    @Test
    @Order(4)
    void beregnPraemie_syttenAarKvinde() {
        // Arrange
        int alder = 17;
        boolean iskvinde = true;
        int skadefriAar = 0;

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class,() -> {
            bilForsikring.beregnPraemie(alder,iskvinde,skadefriAar);
        });
        assertEquals(exception.getMessage(),"Du er for ung til at tegne en forsikring");
    }

    @Test
    @Order(5)
    void beregnPraemie_attenAarmandMedtoskadefrieaar() {
        // Arrange
        int alder = 18;
        boolean iskvinde = true;
        int skadefriAar = 2;

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class,() -> {
            bilForsikring.beregnPraemie(alder,iskvinde,skadefriAar);
        });
        assertEquals(exception.getMessage(),"Du kan ikke have kaert skadefri saelaenge");
    }

    @Test
    @Order(6)
    void beregnPraemie_grundpramieskalVaerepositiv() {
        // Arrange
        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class,() -> {
            bilForsikring.setGrundpaemie(0);
        });
        assertEquals(exception.getMessage(),"grundPr skal vaere positiv");
    }
    @Test
    @Order(7)
    void beregnPraemie_skadefrieaarnegativ() {
        // Arrange
        int alder = 24;
        boolean iskvinde = true;
        int skadefriAar = -1;

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class,() -> {
            bilForsikring.beregnPraemie(alder,iskvinde,skadefriAar);
        });
        assertEquals(exception.getMessage(),"Antal skade frie aer skal vaere positiv");
    }




}