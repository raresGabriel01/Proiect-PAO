import javafx.util.Pair;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Catalog {

    


    private ArrayList<Student> listaStudenti;
    private ArrayList<Profesor> listaProfesori;
    private ArrayList<Curs> listaCursuri;
    private HashMap<Student, ArrayList<Pair<Curs,Nota>>> note;
    private MyFileWriter myFileWriter = MyFileWriter.getInstance();
    private MyFileReader myFileReader = MyFileReader.getInstance();
    Catalog() {
        this.listaStudenti = new ArrayList<>();
        this.listaCursuri = new ArrayList<>();
        this.listaProfesori = new ArrayList<>();
        this.note = new HashMap<>();

        String profesori = myFileReader.read("CSV/profesori.csv");
        String[] prf = profesori.split("\n");
        for (String prof : prf) {
            String[] attr = prof.split(",");
            Profesor myProfesor = new Profesor(attr[0],attr[1],attr[2]);
            listaProfesori.add(myProfesor);
        }

        String studentiLicenta = myFileReader.read("CSV/studenti_licenta.csv");
        String[] stdL = studentiLicenta.split("\n");
        for(String std : stdL){
            String[] attr = std.split(",");
            StudentLicenta myStudentLicenta;
            try{
                myStudentLicenta = new StudentLicenta(attr[0],attr[1],Integer.parseInt(attr[2]),attr[3]);
                listaStudenti.add(myStudentLicenta);
            }catch(Exception e){
                System.out.println("An gresit de studiu in fisier");
            }
        }

        String studentiMaster = myFileReader.read("CSV/studenti_master.csv");
        String[] stdM = studentiMaster.split("\n");
        for(String std : stdM){
            String[] attr = std.split(",");
            StudentMaster myStudentMaster;
            try{
                myStudentMaster = new StudentMaster(attr[0],attr[1],Integer.parseInt(attr[2]),attr[3]);
                listaStudenti.add(myStudentMaster);
            }catch(Exception e){
                System.out.println("An de studiu gresit in fisier");
            }
        }

        String cursuri = myFileReader.read("CSV/cursuri.csv");
        String[] c = cursuri.split("\n");
        for(String curs: c){
            String[] attr = curs.split(",");
            try {
                Date dataInceput = new SimpleDateFormat("dd/MM/yyyy").parse(attr[2]);
                Date dataFinal = new SimpleDateFormat("dd/MM/yyyy").parse(attr[3]);
                Date dataExamen = new SimpleDateFormat("dd/MM/yyyy").parse(attr[4]);
                Curs myCurs = new Curs(attr[0],Integer.parseInt(attr[1]),
                        new Pair<Date,Date>(dataInceput,dataFinal),new Examen(dataExamen),listaProfesori.get(Integer.parseInt(attr[5])));
                listaCursuri.add(myCurs);
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }

        }

        ServiciuAudit.audit("loaded_catalog", LocalDateTime.now());
    }

    public void afiseazaStudenti() {
        if(listaStudenti.size() == 0){
            System.out.println("Nu exista studenti de afisat");
        } else {
            for(Student student : listaStudenti){
                System.out.println(student);
            }
        }

        ServiciuAudit.audit("printing_students",LocalDateTime.now());
    }

    public void adaugaStudent() {
        Scanner myScanner = new Scanner(System.in);
        String nume="", prenume="", domeniuStudiu ="", numeDomeniu="";
        Integer an=0;
        boolean flag = true;
        do {
            System.out.println("Introduceti numele, prenumele si anul studentului separate prin virgula:");
            String[] input = myScanner.nextLine().split(",");
            if(input.length == 3){
                flag = false;
                nume = input[0].toUpperCase();
                prenume = input[1].toUpperCase();
                try{
                    an = Integer.parseInt(input[2].trim());
                }catch(Exception e) {
                    flag = true;
                    System.out.println("Introduceti un numar valid pentru anul de studiu");
                }

            }
        }while(flag);

        do{
            System.out.println("Introduceti domeniul de studiu (licenta/master): ");
            domeniuStudiu = myScanner.nextLine().trim();
        }while(!domeniuStudiu.equalsIgnoreCase("master") && !domeniuStudiu.equalsIgnoreCase("licenta"));

        do{
            System.out.println("Introduceti numele domeniului de studiu");
            numeDomeniu = myScanner.nextLine().trim().toUpperCase();
        }while(numeDomeniu.equals(""));

        Student studentNou = null;

        if(domeniuStudiu.equalsIgnoreCase("master")){
            try{
                studentNou = new StudentMaster(nume,prenume,an,numeDomeniu);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }else {
            try{
                studentNou = new StudentLicenta(nume,prenume,an,numeDomeniu);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        if(studentNou != null) {
            this.listaStudenti.add(studentNou);
            String path = "";
            String content = "";
            if(studentNou instanceof StudentLicenta){
                path = "CSV/studenti_licenta.csv";
                content = String.format("%s,%s,%s,%s\n", studentNou.getNume(), studentNou.getPrenume(), studentNou.getAn(),
                        ( (StudentLicenta) studentNou).getDomeniuLicenta());
            }else {
                path = "CSV/studenti_master.csv";
                content = String.format("%s,%s,%s,%s\n", studentNou.getNume(), studentNou.getPrenume(), studentNou.getAn(),
                        ( (StudentMaster) studentNou).getDomeniuMaster());
            }

            myFileWriter.writeTo(content,path);
           /* Collections.sort(this.listaStudenti, (s1, s2) -> {  // studentii sunt ordonati crescator mai intai dupa an
                                                                // apoi dupa nume si apoi dupa prenume
                if(s1.getAn().equals(s2.getAn())){
                    if(s1.getNume().compareTo(s2.getNume()) == 0) {
                        return s1.getPrenume().compareTo(s2.getPrenume());
                    }
                    return s1.getNume().compareTo(s2.getNume());
                }
                return s1.getAn() - s2.getAn();
            });*/
            System.out.println("Student adaugat cu succes");
        }
        ServiciuAudit.audit("added_student",LocalDateTime.now());
    }

    public void afiseazaProfesori() {
        if(this.listaProfesori.size() == 0){
            System.out.println("Nu exista profesori de afisat");
        }else{
            for(Profesor profesor : this.listaProfesori){
                System.out.println(profesor);
            }
        }
        ServiciuAudit.audit("printed_teachers", LocalDateTime.now());
    }

    public void adaugaProfesor(){
        Scanner myScanner = new Scanner(System.in);
        String nume ="", prenume ="", titlu ="";
        boolean flag = true;
        do{
            System.out.println("Introduceti titlul, numele si prenumele separate prin virgula");
            String[] input = myScanner.nextLine().split(",");
            if(input.length == 3){
                flag = false;
                titlu = input[0].trim().toUpperCase();
                nume = input[1].trim().toUpperCase();
                prenume = input[2].trim().toUpperCase();
            }

        }while(flag);
        Profesor profesor = new Profesor(nume,prenume,titlu);
        this.listaProfesori.add(profesor);
        String path = "CSV/profesori.csv";
        String content = String.format("%s,%s,%s\n",nume,prenume,titlu);
        myFileWriter.writeTo(content,path);
        System.out.println("Profesor adaugat cu succes");
        ServiciuAudit.audit("added_teacher",LocalDateTime.now());
    }

    public void afiseazaToateCursurile() {
        if(this.listaCursuri.size() == 0){
            System.out.println("Nu exista cursuri de afisat");
        }else{
            for(Curs curs: this.listaCursuri){
                System.out.println(curs);
            }
        }
        ServiciuAudit.audit("printed_courses",LocalDateTime.now());
    }

    public void adaugaCurs() {
        String nume = "";
        Integer credite = 0;
        Pair<Date,Date> perioadaDesfasurare = null;
        Date dataExamen = null;
        Profesor profesor = null;
        Integer id = -1;
        Scanner myScanner = new Scanner(System.in);
        String stringDataInceput = "";
        String stringDataFinal = "";
        String stringDataExamen = "";
        boolean flag = true;

        do {
            System.out.println("Introduceti numele, numarul de credite, data de inceput, data de final, data examenului, " +
                    "si id-ul profesorului care sustine cursul separate prin virgula " +
                    "(datele trebuie introduse in formatul dd/mm/yyyy");
            String[] input = myScanner.nextLine().split(",");
            if(input.length == 6) {
                flag = false;
                nume = input[0].trim().toUpperCase();
                try{
                    stringDataExamen = input[4].trim();
                    stringDataFinal = input[3].trim();
                    stringDataInceput = input[2].trim();
                    credite = Integer.parseInt(input[1].trim());
                    Date dataInceput = new SimpleDateFormat("dd/MM/yyyy").parse(input[2].trim());
                    Date dataSfarsit =  new SimpleDateFormat("dd/MM/yyyy").parse(input[3].trim());
                    perioadaDesfasurare = new Pair<>(dataInceput,dataSfarsit);
                    dataExamen = new SimpleDateFormat("dd/MM/yyyy").parse(input[4].trim());
                    id = Integer.parseInt(input[5].trim());
                    profesor = this.listaProfesori.get(id);
                }catch(Exception e){
                    flag = true;
                }


            }
        }while(flag);

        Curs curs = new Curs(nume,credite,perioadaDesfasurare,new Examen(dataExamen),profesor);

        this.listaCursuri.add(curs);
        String path = "CSV/cursuri.csv";
        String content = String.format("%s,%s,%s,%s,%s,%s\n", nume, credite,stringDataInceput,stringDataFinal,
                stringDataExamen,id);
        myFileWriter.writeTo(content,path);
        System.out.println("Curs adaugat cu succes");
        ServiciuAudit.audit("added_course",LocalDateTime.now());
    }

    void adaugaNotaStudent(){
        Integer idStudent = -1;
        Integer idCurs = -1;
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Introduceti id-ul studentului si id-ul cursului spearate prin virgula");

        boolean flag = true;

        do {
            String[] input = myScanner.nextLine().split(",");
            if(input.length == 2){
                flag = false;
                try{
                    idStudent = Integer.parseInt(input[0].trim());
                    idCurs = Integer.parseInt(input[1].trim());
                }catch(Exception e){
                    flag = true;
                }
            }
        }while(flag);

        flag = true;

        System.out.println("Introduceti valoarea notei si data primirii notei spearate prin virgula" +
                "(data trebuie sa fie in format dd/mm/yyyy");
        Nota nota = null;
        do {
            String[] input = myScanner.nextLine().split(",");
            if(input.length == 2){
                flag = false;
                try{
                    Double valoare = Double.parseDouble(input[0].trim());
                    Date data = new SimpleDateFormat("dd/MM/yyyy").parse(input[1].trim());
                    nota = new Nota(valoare,data);
                }catch(Exception e){
                    flag = true;
                }
            }
        }while(flag);



        Student student = this.listaStudenti.get(idStudent);
        Curs curs = this.listaCursuri.get(idCurs);

        if(this.note.get(student) == null) {
            this.note.put(student,new ArrayList<Pair<Curs,Nota>>());
        }


        this.note.get(student).add(new Pair<Curs,Nota>(curs,nota));

        System.out.println("Nota adaugata cu succes");
        ServiciuAudit.audit("added_grade",LocalDateTime.now());
    }

    public void afiseazaToateNotele() {
        if(this.note.size() == 0){
            System.out.println("Nu exista note de afisat");
        }else{
            for(Map.Entry<Student,ArrayList<Pair<Curs,Nota>>> entry : this.note.entrySet()){
                Student student = entry.getKey();
                System.out.println("Studentul " + student.getNume() + " " + student.getPrenume() +
                        " are notele: ");
                for(Pair<Curs,Nota> pereche : entry.getValue()){
                    Curs curs = pereche.getKey();
                    Nota nota = pereche.getValue();
                    System.out.println(curs.getNume() + " : " + nota.getValoare() + "(" + curs.getExamen().toString() +")");
                }
            }
        }
        ServiciuAudit.audit("printed_all_grades", LocalDateTime.now());
    }

    public void afiseazaNota() {
        if(this.note.size() == 0){
            System.out.println("Nu exista note de afisat");
        }else {
            Integer idStudent = -1;
            boolean flag = true;
            Scanner myScanner = new Scanner(System.in);
            do{
                System.out.println("Introduceti id-ul studentului");
                try{
                    idStudent = Integer.parseInt(myScanner.nextLine().trim());
                    flag = false;
                }catch(Exception e) {
                    flag = true;
                }
            }while(flag = true);

            Student student = this.listaStudenti.get(idStudent);
            if(this.note.get(student) == null) {
                System.out.println("Nu exista note de afisat pentru studentul selectat");
            }else {
                System.out.println("Notele lui " + student.getNume() + " " + student.getPrenume() + " sunt :");
                ArrayList<Pair<Curs,Nota>> note = this.note.get(student);
                for(Pair<Curs,Nota> pereche : note) {
                    Curs curs = pereche.getKey();
                    Nota nota = pereche.getValue();
                    System.out.println(curs.getNume() + ": " + nota.getValoare() + " (" + nota.getDataPrimire().toString() + ")");
                }
            }
        }
        ServiciuAudit.audit("printed_grade",LocalDateTime.now());
    }

    public void modificaStudent() {

        if(this.listaStudenti.size() == 0) {
            System.out.println("Nu exista studenti de modificat");
        } else {
            System.out.println("Introduceti id-ul studentului pe care doriti sa il modificati");
            Scanner myScanner = new Scanner(System.in);
            Integer idStudent = Integer.parseInt(myScanner.nextLine());

            Student student = this.listaStudenti.get(idStudent);

            System.out.println("Introduceti ce doriti sa modificati: \n" +
                    "1. Numele \n" +
                    "2. Prenumele \n" +
                    "3. Anul de studiu \n");

            Integer optiune = Integer.parseInt(myScanner.nextLine());

            switch(optiune){
                case 1:
                    System.out.println("Introduceti noul nume:");
                    String numeNou = myScanner.nextLine();
                    student.setNume(numeNou);
                    break;
                case 2:
                    System.out.println("Introduceti noul prenume:");
                    String prenumeNou = myScanner.nextLine();
                    student.setPrenume(prenumeNou);
                    break;
                case 3:
                    System.out.println("Introduceti noul an:");
                    Integer anNou = Integer.parseInt(myScanner.nextLine());
                    try {
                        student.setAn(anNou);
                    }catch(Exception e) {
                        System.out.println("An invalid pentru domeniul de studiu");
                    }

                    break;
                default:
                    System.out.println("optiune invalida");
            }
        }

        ServiciuAudit.audit("modified_student",LocalDateTime.now());

    }

    public void eliminaStudent() {


        if(this.listaStudenti.size() == 0) {
            System.out.println("nu exista studenti de sters");
        }else {
            System.out.println("Introduceti id-ul studentului pe care doriti sa il stergeti");
            Scanner myScanner = new Scanner(System.in);
            Integer idStudent = Integer.parseInt(myScanner.nextLine());

            Student student = this.listaStudenti.get(idStudent);

            this.note.remove(student);
            this.listaStudenti.remove(idStudent);
        }
        ServiciuAudit.audit("deleted_student", LocalDateTime.now());
    }
}
