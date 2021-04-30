

public class Temat {
    private Integer idx = 0;
    private Integer godzin = 0;
    private String tekst;
    private String opis;
    public Temat(Integer index, Integer godzin , String tekst){
        this.idx = index;
        this.godzin = godzin;
        this.tekst = tekst;
    }
    public void setGodz(Integer value){
        this.godzin = value;
    }
    public Integer getGodzin(){
        return this.godzin;
    }
    public void setTekst(String value){
        this.tekst = value;
    }
    public String getTekst(){
        return this.tekst;
    }
    public void setOpis(String value){
        this.opis = value;
    }
    public String getOpis(){
        return this.opis;
    }
    public Integer getId(){
        return this.idx;
    }
    public String toString(){
        return "{#"+idx+"; G:"+godzin+"; T:"+tekst+"}";
    }
}
