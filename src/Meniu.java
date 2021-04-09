import java.util.Scanner;

public class Meniu {
    private static Meniu meniu = null;
    private Catalog catalog;
    private Meniu(){
        this.catalog = new Catalog();
    }
    public static Meniu getInstance() {
        if(meniu == null)
            meniu = new Meniu();
        return meniu;
    }
    private void afiseazaMeniu() {

        System.out.println("============================================================\n" +
                "Pentru moment m-am limitat la numarul strict minim deoarece codul se va modifica " +
                "mult atunci cand vom adauga bazele de date si implicit toate operatiile de tip CRUD\n" +
                "Proiect PAO - Baesu Rares Gabriel, grupa 243\n" +
                "===============================================================\n" +
                "Introduceti una din optiunile dorite:\n" +
                "0. Inchidere meniu \n" +
                "1. Afisati toti studentii \n" +
                "2. Afisati toti profesorii \n" +
                "3. Afisati toate cursurile \n" +
                "4. Adagati un nou student \n" +
                "5. Adaugati un nou profesor \n" +
                "6. Adaugati un nou curs \n" +
                "7. Adaugati o nota unui student\n" +
                "8. Afisati toate notele \n" +
                "9. Afisati notele unui anumit student \n" +
                "10. Afisati din nou meniul \n" +
                "11. Modificati un student \n" +
                "12. Sterge un student \n");
    }
    public void deschideMeniu() {

        afiseazaMeniu();

        Scanner myScanner = new Scanner(System.in);
        Integer optiune = -1;

        do{
            try{
                optiune = Integer.parseInt(myScanner.nextLine());
            }catch(Exception e){
                System.out.println("Introduceti un numar intreg corespunzator");
                optiune = -1;
            }
            switch(optiune){
                case 0:
                    System.out.println("Inchidere meniu...");
                    break;

                case 1:
                    System.out.println("Se afiseaza toti studentii...");
                    this.catalog.afiseazaStudenti();
                    break;
                case 2:
                    System.out.println("Se afiseaza toti profesorii...");
                    this.catalog.afiseazaProfesori();
                    break;
                case 3:
                    System.out.println("Se afiseaza toate cursurile...");
                    this.catalog.afiseazaToateCursurile();
                    break;
                case 4:
                    this.catalog.adaugaStudent();
                    break;
                case 5:
                    this.catalog.adaugaProfesor();
                    break;
                case 6:
                    this.catalog.adaugaCurs();
                    break;
                case 7:
                    this.catalog.adaugaNotaStudent();
                    break;
                case 8:
                    this.catalog.afiseazaToateNotele();
                    break;
                case 9:
                    break;
                case 10:
                    afiseazaMeniu();
                    break;
                case 11:
                    this.catalog.modificaStudent();
                    break;

                default:
                    System.out.println("Comanda invalida");
            }
            System.out.println("Introduceti o noua comanda de meniu");
        }while(optiune != 0);
    }
}
