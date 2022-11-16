import java.util.Scanner;

public class Bank1 {

    static Scanner input = new Scanner(System.in);
    static int kapasitas = 99;
    static String[] username = new String[kapasitas];
    static String[] password = new String[kapasitas];
    static int[] saldoUser = new int[kapasitas];
    static int user = 0;
    static int userIndex = 0;

    public static void main(String[] args) {
        menu();
    }

    public static void tulis(Object x) {
        System.out.println(x);
    }

    public static void menu() {
        tulis("Selamat Datang di Bank Ilkom");
        tulis("1. Daftar");
        tulis("2. Masuk");
        tulis("3. Keluar Program");

        System.out.print("Menu : ");
        int menu = input.nextInt();
        if (menu == 1) {
            daftar();
        } else if (menu == 2) {
            for (int i = 0; i < kapasitas; i++) {
                if (username[i] == null && password[i] == null) {
                    tulis("Anda belum daftar");
                    menu();
                } else {
                    masuk();
                }
            }
        } else if (menu == 3) {
            keluar();
        } else {
            tulis("Menu salah, Coba lagi");
        }
    }

    public static void menu2() {
        System.out.println("1. Cek Saldo");
        System.out.println("2. Tarik Uang");
        System.out.println("3. Simpan Uang");
        System.out.println("4. Keluar "); // logout
        System.out.print("Menu : ");
        int menu = input.nextInt();
        if (menu == 1) {
            cek();
        } else if (menu == 2) {
            tarik();
        } else if (menu == 3) {
            simpan();
        } else if (menu == 4) {
            menu();
        } else {
            tulis("Maaf Menu Salah");
            menu2();
        }
    }

    public static void daftar() {

        for (int i = user; i < kapasitas; i++) {
            System.out.print("Nama    : ");
            username[i] = input.next();

            System.out.print("Pass    : ");
            password[i] = input.next();

            System.out.print("Apakah data sudah benar : ");
            String kondisi = input.next();
            if (kondisi.equalsIgnoreCase("ya")) {
                user++;
                menu();
            } else {
                daftar();
            }
        }

    }

    public static void masuk() {
        System.out.print("Nama    : ");
        String usernameLogin = input.next();

        System.out.print("Pass    : ");
        String passwordLogin = input.next();
        int kondisi = 0;
        String userLogin = null;

        for (int i = 0; i < kapasitas; i++) {
            if ((usernameLogin.equals(username[i])) && (passwordLogin.equals(password[i]))) {
                kondisi = 1;
                userLogin = username[i];
                userIndex = i;
                break;
            } else {
                kondisi = 0;
            }
        }

        if (kondisi == 1) {
            tulis("Selamat Datang " + userLogin + " di Bank Ilkom");
            menu2();

        } else {
            tulis("Nama / Password anda salah");
            masuk();
        }

    }

    public static void cek() {
        System.out.println("Saldo saat ini : Rp. " + saldoUser[userIndex]);
        kembali();
    }

    public static void tarik() {
        System.out.print("Ambil uang : Rp. ");
        int jmlUang = input.nextInt();
        if (jmlUang <= saldoUser[userIndex]) {
            saldoUser[userIndex] -= jmlUang;
            System.out.println("Saldo saat ini : Rp. " + saldoUser[userIndex]);
        } else {
            System.out.println("Saldo anda tidak mencukupi");
        }
        kembali();
    }

    public static void simpan() {
        System.out.print("Simpan uang : Rp. ");
        int jmlUang = input.nextInt();
        saldoUser[userIndex] += jmlUang;
        System.out.println("Saldo saat ini : Rp. " + saldoUser[userIndex]);
        kembali();
    }

    public static void kembali() {
        tulis("1. Kembali");
        tulis("2. Keluar");
        System.out.print("Menu : ");
        int kondisi = input.nextInt();
        if (kondisi == 1) {
            menu2();
        } else if (kondisi == 2) {
            tulis("Anda telah keluar");
            keluar();
        } else {
            tulis("Menu salah");
            kembali();
        }
    }

    public static void keluar() {
        System.exit(0);
    }

}