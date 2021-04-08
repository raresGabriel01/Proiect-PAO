public class StudentLicenta extends Student{
    private String domeniuLicenta;
    StudentLicenta(String nume, String prenume, Integer an, String domeniuLicenta) throws Exception{
        super(nume,prenume,an);
        if(!(1<=an && an <=3)){
            throw new Exception("An de studiu invalid pentru licenta");
        }
        this.domeniuLicenta = domeniuLicenta;
    }

    public String getDomeniuLicenta() {
        return domeniuLicenta;
    }

    public void setDomeniuLicenta(String domeniuLicenta) {
        this.domeniuLicenta = domeniuLicenta;
    }

    @Override
    public void setAn(Integer an) throws Exception{
        if(1 <= an && an <=3) {
            super.setAn(an);
        }else {
            throw new Exception("Anul de studiu pentru licenta trebuie sa fie un intreg pozitiv intre 1 si 3");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " la domeniul de licenta " + this.domeniuLicenta;
    }
}
