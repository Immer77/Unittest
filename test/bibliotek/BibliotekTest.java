package bibliotek;

import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BibliotekTest {

    private Bibliotek bibliotek;

    @BeforeEach
    void setUp(){
        bibliotek = new Bibliotek();
    }

    @Test
    @Order(1)
    void beregnBoede_voksenEnDag(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,9,20);
        LocalDate faktiskDato = LocalDate.of(2022,9,21);
        boolean isVoksen = true;

        // Act
        double result = bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);

        // Arrange
        assertEquals(20,result);
    }

    @Test
    @Order(2)
    void beregnBoede_barnEnDag(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,9,20);
        LocalDate faktiskDato = LocalDate.of(2022,9,21);
        boolean isVoksen = false;

        // Act
        double result = bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);

        // Arrange
        assertEquals(10,result);
    }

    @Test
    @Order(3)
    void beregnBoede_Voksen8dage(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,9,20);
        LocalDate faktiskDato = LocalDate.of(2022,9,28);
        boolean isVoksen = true;

        // Act
        double result = bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);

        // Arrange
        assertEquals(60,result);
    }

    @Test
    @Order(3)
    void beregnBoede_barn8dage(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,9,20);
        LocalDate faktiskDato = LocalDate.of(2022,9,28);
        boolean isVoksen = false;

        // Act
        double result = bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);

        // Arrange
        assertEquals(30,result);
    }

    @Test
    @Order(4)
    void beregnBoede_VoksenMereEndFemtenDage(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,9,20);
        LocalDate faktiskDato = LocalDate.of(2022,10,10);
        boolean isVoksen = true;

        // Act
        double result = bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);

        // Arrange
        assertEquals(90,result);
    }

    @Test
    @Order(5)
    void beregnBoede_barnMereEndFemtenDage(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,9,20);
        LocalDate faktiskDato = LocalDate.of(2022,10,10);
        boolean isVoksen = false;

        // Act
        double result = bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);

        // Arrange
        assertEquals(45,result);
    }

    @Test
    @Order(6)
    void beregnBoede_BeregnetDatoEfterFaktiskDato(){
        // Arrange
        LocalDate beregnetDato = LocalDate.of(2022,10,20);
        LocalDate faktiskDato = LocalDate.of(2022,9,10);
        boolean isVoksen = false;

        // Act
        Exception exception = assertThrows(RuntimeException.class,() -> {
            bibliotek.beregnboede(beregnetDato,faktiskDato,isVoksen);
        });

        // Arrange
        assertEquals(exception.getMessage(),"beregnet dato skal komme foer faktiskdato");

    }


}