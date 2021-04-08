public class StudentMaster extends Student{
    private String domeniuMaster;
    StudentMaster(String nume, String prenume, Integer an, String domeniuMaster) throws Exception {
        super(nume,prenume,an);
        if(!(1<=an && an<=2)){
            throw new Exception("An de studiu invalid pentru master");
        }
        this.domeniuMaster = domeniuMaster;
    }

    public String getDomeniuMaster() {
        return domeniuMaster;
    }

    public void setDomeniuMaster(String domeniuMaster) {
        this.domeniuMaster = domeniuMaster;
    }

    @Override
    public void setAn(Integer an) throws Exception {
        if(1 <= an && an <= 2){
            super.setAn(an);
        }else {
            throw new Exception("Anul de studiu pentru master trebuie sa fie un intreg intre 1 si 2");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " la domeniul de master " + this.domeniuMaster;
    }
}
