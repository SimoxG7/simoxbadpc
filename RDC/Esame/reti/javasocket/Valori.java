public class Valori {
    private String nickname = "";
    private Double rtt=0.;
    private Double rto=0.;
    private Double d=0.;
    private Double alfa=0.;
    private Double beta=0.;
    private String addr = "";

    public Valori(){
        this.nickname = "";
        this.rtt = 0.;
        this.rto = 0.;
        this.d=0.;
        this.alfa=0.;
        this.beta=0.;
        this.addr="";
    }
    public Valori(String addr, String n, Double a, Double b){
        this.nickname = n;
        this.alfa = a;
        this.beta = b;
        this.addr = addr;
    }

    public void setNick(String s){
        this.nickname = s;
    }

    public void setAddr(String s){
        this.addr = s;
    }

    public void setRTT(Double s){
        this.rtt = s;
    }

    public void setRTO(Double s){
        this.rto  = s;
    }

    public void setD(Double s){
        this.d = s;
    }

    public void setAlfa(Double s){
        this.alfa = s;
    }

    public void setBeta(Double s){
        this.beta = s;
    }

    public Double getRTT(){
        return this.rtt;
    } 

    public Double getRTO(){
        return this.rto;
    } 

    public Double getD(){
        return this.d;
    }

    public Double getAlfa(){
        return this.alfa;
    } 

    public Double getBeta(){
        return this.beta;
    } 

    public String getNick(){
        return this.nickname;
    }

    public void remove(){
        this.nickname = "";
        this.rtt=0.;
        this.rto=0.;
        this.d=0.;
        this.alfa=0.;
        this.beta=0.;
        this.addr="";
    }

}


