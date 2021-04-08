import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.*;

public class Catalog {
    private ArrayList<Student> listaStudenti;
    private ArrayList<Profesor> listaProfesori;
    private ArrayList<Curs> listaCursuri;
    private HashMap<Student, ArrayList<Pair<Curs,Nota>>> note;

    Catalog() {
        this.listaStudenti = new ArrayList<>();
        this.listaCursuri = new ArrayList<>();
        this.listaProfesori = new ArrayList<>();
        this.note = new HashMap<>();
    }

    public void afiseazaStudenti() {
        if(listaStudenti.size() == 0){
            System.out.println("Nu exista studenti de afisat");
        } else {
            for(Student student : listaStudenti){
                System.out.println(student);
            }
        }
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
            System.out.println("Student adaugat cu succes");
        }

    }

    public void afiseazaProfesori() {
        if(this.listaProfesori.size() == 0){
            System.out.println("Nu exista profesori de afisat");
        }else{
            for(Profesor profesor : this.listaProfesori){
                System.out.println(profesor);
            }
        }
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
        System.out.println("Profesor adaugat cu succes");
    }

    public void afiseazaToateCursurile() {
        if(this.listaCursuri.size() == 0){
            System.out.println("Nu exista cursuri de afisat");
        }else{
            for(Curs curs: this.listaCursuri){
                System.out.println(curs);
            }
        }
    }

    public void adaugaCurs() {
        String nume = "";
        Integer credite = 0;
        Pair<Date,Date> perioadaDesfasurare = null;
        Date dataExamen = null;
        Profesor profesor = null;

        Scanner myScanner = new Scanner(System.in);

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
                    credite = Integer.parseInt(input[1].trim());
                    Date dataInceput = new SimpleDateFormat("dd/MM/yyyy").parse(input[2].trim());
                    Date dataSfarsit =  new SimpleDateFormat("dd/MM/yyyy").parse(input[3].trim());
                    perioadaDesfasurare = new Pair<>(dataInceput,dataSfarsit);
                    dataExamen = new SimpleDateFormat("dd/MM/yyyy").parse(input[4].trim());
                    profesor = this.listaProfesori.get(Integer.parseInt(input[5].trim()));
                }catch(Exception e){
                    flag = true;
                }


            }
        }while(flag);

        Curs curs = new Curs(nume,credite,perioadaDesfasurare,dataExamen,profesor);

        this.listaCursuri.add(curs);
        System.out.println("Curs adaugat cu succes");
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
                    System.out.println(curs.getNume() + " : " + nota.getValoare() + "(" + curs.getDataExamen().toString() +")");
                }
            }
        }
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




    }
}
