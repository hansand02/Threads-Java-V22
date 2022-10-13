public class Subsekvens{

    private int antallForekomster;
    public final String aktuellSubSekvens;

    Subsekvens(int antall, String SubSekvens ){
        this.antallForekomster = antall;
        this.aktuellSubSekvens = SubSekvens;
    }

    public void endreAntallForekomster(int antall){
        antallForekomster = antall;
    }

    public int hentAntallForekomster(){
        return antallForekomster;
    }

    public String toString(){
        return "(" + aktuellSubSekvens + "," + antallForekomster + ")";
    }   
}