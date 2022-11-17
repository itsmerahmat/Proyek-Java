import java.util.Scanner;
import java.util.Random;

public class BankFinal {
    // Membuat Global Variable
    static Scanner input = new Scanner(System.in);
    static Random angka = new Random();
    static int kapasitas = 99;
    static String[] username = new String[kapasitas];
    static String[] password = new String[kapasitas];
    static int[] noRekening = new int[kapasitas];
    static int[] saldoUser = new int[kapasitas];
    static int user = 0;
    static int userOnline = 0;

    public static void main(String[] args) {
        menu();
    }

    public static void tulis(Object x) {
        System.out.println(x);
    }

    public static void menu() {
        tulis("== Selamat Datang di Bank Ilkom ==");
        tulis("|| [1] Daftar");
        tulis("|| [2] Masuk");
        tulis("|| [3] Keluar Program");

        System.out.print("|| Menu : ");
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
            tulis("||");
            tulis(" || Menu salah, Coba lagi");
        }
    }

    public static void daftar() {

        for (int i = user; i < kapasitas; i++) {
            System.out.print("|| Nama    : ");
            username[i] = input.next();

            System.out.print("|| Pass    : ");
            password[i] = input.next();

            System.out.print("|| Apakah data sudah benar : ");
            String kondisi = input.next();
            if (kondisi.equalsIgnoreCase("ya")) {
                int max = 9999999;
                int min = 1000000;
                int random = angka.nextInt(max - min) + min;
                noRekening[i] = random;
                user++;
                menu();
            } else {
                daftar();
            }
        }

    }

    public static void masuk() {
        System.out.print("|| Nama    : ");
        String usernameLogin = input.next();

        System.out.print("|| Pass    : ");
        String passwordLogin = input.next();
        int kondisi = 0;

        for (int i = 0; i < kapasitas; i++) {
            if ((usernameLogin.equals(username[i])) && (passwordLogin.equals(password[i]))) {
                kondisi = 1;
                userOnline = i;
                break;
            } else {
                kondisi = 0;
            }
        }

        if (kondisi == 1) {
            tulis("|| Selamat Datang " + username[userOnline] + " di Bank Ilkom");
            menu2();

        } else {
            tulis("|| Nama / Password anda salah");
            masuk();
        }

    }

    public static void menu2() {
        tulis("|| Nama           : " + username[userOnline]);
        tulis("|| Saldo          : Rp. " + saldoUser[userOnline]);
        tulis("|| Nomor Rekening : " + noRekening[userOnline]);
        tulis("||");
        tulis("|| [1] Tarik Uang");
        tulis("|| [2] Simpan Uang");
        tulis("|| [3] Transfer Uang");
        tulis("|| [4] Keluar "); // logout
        System.out.print("|| Menu : ");
        int menu = input.nextInt();
        if (menu == 1) {
            System.out.print("|| Ambil uang : Rp. ");
            int jmlUang = input.nextInt();
            tulis(tarik(jmlUang, saldoUser[userOnline]));
            kembali();
        } else if (menu == 2) {
            System.out.print("|| Simpan uang : Rp. ");
            int jmlUang = input.nextInt();
            tulis(simpan(jmlUang, saldoUser[userOnline]));
            kembali();
        } else if (menu == 3) {
            System.out.print("|| Masukkan nomor rekening : ");
            int noRek = input.nextInt();
            System.out.print("|| Masukkan jumlah transfer : ");
            int jmlUang = input.nextInt();
            tulis(transfer(noRek, jmlUang, saldoUser[userOnline]));
            kembali();
        } else if (menu == 4) {
            menu();
        } else {
            tulis("|| Maaf Menu Salah");
            menu2();
        }
    }

    public static String tarik(int jmlUang, int saldo) {
        String pesan = null;
        if (jmlUang <= saldo) {
            saldoUser[userOnline] = saldo -= jmlUang;
            pesan = "|| Anda telah melakukan penarikan sebesar Rp. " + jmlUang + "\n|| Sisa saldo anda Rp. "
                    + saldoUser[userOnline];

        } else {
            pesan = "|| Saldo saat ini Rp. " + saldoUser[userOnline] + " tidak mencukupi untuk melakukan penarikan";
        }
        return pesan;

    }

    public static String simpan(int jmlUang, int saldo) {
        String pesan = null;
        saldoUser[userOnline] = saldo += jmlUang;
        pesan = "|| Anda telah berhasil menyimpan uang Rp. " + jmlUang + " Saldo anda Rp. " + saldoUser[userOnline];
        return pesan;

    }

    public static String transfer(int noRek, int jmlUang, int saldo) {
        String pesan = null;
        for (int i = 0; i < kapasitas; i++) {
            if (noRek == noRekening[i]) {
                if (jmlUang <= saldo) {
                    saldoUser[i] += jmlUang;
                    saldoUser[userOnline] = saldo -= jmlUang;
                    pesan = "|| Transfer uang Rp. " + jmlUang + " ke " + noRekening[i]
                            + " berhasil \n|| Saldo saat ini : Rp. " + saldoUser[userOnline];
                } else {
                    pesan = "|| Saldo saat ini Rp. " + saldoUser[userOnline]
                            + " tidak mencukupi untuk melakukan transfer";

                }
            } else {
                tulis("|| No Rekening tidak valid");
            }
        }
        return pesan;
    }

    public static void kembali() {
        tulis("||");
        tulis("|| [1] Kembali");
        tulis("|| [2] Keluar");
        System.out.print("|| Menu : ");
        int kondisi = input.nextInt();
        if (kondisi == 1) {
            menu2();
        } else if (kondisi == 2) {
            keluar();
        } else {
            tulis("|| Menu salah");
            kembali();
        }
    }

    public static void keluar() {
        tulis("|| Anda telah keluar");
        tulis("== Terima Kasih ==");
        System.exit(0);
    }

}