import java.util.Scanner;

public class UAS1C05 {
    static Scanner scanner = new Scanner(System.in);
    static int jumlahTim;
    static String[] namaTim;
    static int[][] skorTim;
    static int[] totalSkor;
    static int nimTerakhir = 15; // Sesuaikan dengan nomor absen Anda
    static boolean dataDiinput = false;

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Input Data Skor Tim");
            System.out.println("2. Tampilkan Tabel Skor");
            System.out.println("3. Tentukan Juara");
            System.out.println("4. Keluar");

            System.out.print("Pilih menu (1-4): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    inputDataSkorTim();
                    break;
                case 2:
                    tampilkanTabelSkor();
                    break;
                case 3:
                    tentukanJuara();
                    break;
                case 4:
                    System.out.println("\n===== PROGRAM SELESAI. TERIMA KASIH! =====\n");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.\n");
            }
        } while (pilihan != 4);
    }

    static void inputDataSkorTim() {
        jumlahTim = (15 % 3) + 4; // Dua digit terakhir NIM Anda % 3 + 4
        namaTim = new String[jumlahTim];
        skorTim = new int[jumlahTim][2];
        totalSkor = new int[jumlahTim];

        for (int i = 0; i < jumlahTim; i++) {
            System.out.print("Masukkan nama tim ke-" + (i + 1) + ": ");
            namaTim[i] = scanner.nextLine();

            for (int j = 0; j < 2; j++) {
                int skor;
                do {
                    System.out.print("Masukkan skor " + namaTim[i] + " untuk Level " + (j + 1) + ": ");
                    skor = scanner.nextInt();
                    if (skor < 0) {
                        System.out.println("Input tidak valid. Skor harus positif atau nol.");
                    }
                } while (skor < 0);

                if (j == 0 && skor < 35) {
                    skor = 0;
                }

                skorTim[i][j] = skor;
            }
            scanner.nextLine(); // Clear buffer
        }
        dataDiinput = true;
        System.out.println("\n===== DATA SKOR TIM BERHASIL DIINPUT =====\n");
    }

    static void tampilkanTabelSkor() {
        if (!dataDiinput) {
            System.out.println("\nTidak ada data yang bisa ditampilkan.");
            return;
        }

        System.out.println("\n===== TABEL SKOR TURNAMEN =====");
        System.out.printf("%-10s %-10s %-10s %-10s%n", "Nama Tim", "Level 1", "Level 2", "Total Skor");

        for (int i = 0; i < jumlahTim; i++) {
            totalSkor[i] = skorTim[i][0] + skorTim[i][1];

            // Kurangi 15 poin jika total skor genap
            if (totalSkor[i] % 2 == 0) {
                totalSkor[i] -= 15;
            }

            // Tambahkan bonus Buff Kemenangan jika skor pada dua level > 50
            if (skorTim[i][0] > 50 && skorTim[i][1] > 50) {
                totalSkor[i] += nimTerakhir;
            }

            System.out.printf("%-10s %-10d %-10d %-10d%n", namaTim[i], skorTim[i][0], skorTim[i][1], totalSkor[i]);
        }
    }

    static void tentukanJuara() {
        if (!dataDiinput) {
            System.out.println("\nTidak ada data yang bisa ditampilkan.");
            return;
        }

        int maxSkor = -1;
        int timJuara = -1;

        for (int i = 0; i < jumlahTim; i++) {
            if (totalSkor[i] > maxSkor) {
                maxSkor = totalSkor[i];
                timJuara = i;
            } else if (totalSkor[i] == maxSkor) {
                // Jika total skor sama, bandingkan skor Level 2
                if (skorTim[i][1] > skorTim[timJuara][1]) {
                    timJuara = i;
                } else if (skorTim[i][1] == skorTim[timJuara][1]) {
                    System.out.println("\nTurnamen berakhir seri. Tim terbaik adalah Nama Anda.");
                    return;
                }
            }
        }

        System.out.println("\nSelamat kepada Tim " + namaTim[timJuara] + " yang telah memenangkan kompetisi!");
    }
}
