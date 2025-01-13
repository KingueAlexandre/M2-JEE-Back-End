package fr.uge.jee.onlineshop;

public class InsuranceReturn implements Insurance{
    private final boolean privatE;

    public InsuranceReturn(boolean privee ){
        this.privatE = privee;
    }
    @Override
    public String description() {
        if(!privatE){
            return "Return insurance only for members";
        }
        return "Return open to all";
    }
}
