import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

public class Users {
    static User[] users;
    static int[][] followMatrix;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Registrasi user
        System.out.print("Berapa user yang ingin didaftarkan? ");
        int jumlah = input.nextInt();
        input.nextLine();

        users = new User[jumlah];
        followMatrix = new int[jumlah][jumlah];

        for (int i = 0; i < jumlah; i++) {
            System.out.print("\nMasukkan username ke-" + i + ": ");
            String uname = input.nextLine();
            System.out.print("Masukkan password ke-" + i + ": ");
            String pass = input.nextLine();

            users[i] = new User(uname, pass);
        }

        // Tampilkan semua user
        System.out.println("\nDaftar User:");
        for (int i = 0; i < users.length; i++) {
            System.out.println(i + ". " + users[i].getUsername());
        }

        // Proses follow
        System.out.print("\nBerapa kali akan dilakukan follow? ");
        int banyakFollow = input.nextInt();
        input.nextLine();

        int count = 0;
        while (count < banyakFollow) {
            System.out.print("\nUsername yang mem-follow: ");
            String from = input.nextLine();
            System.out.print("Username yang di-follow: ");
            String to = input.nextLine();

            int idxFrom = cariIndex(from);
            int idxTo = cariIndex(to);

            if (idxFrom == -1 || idxTo == -1) {
                System.out.println("Username tidak ditemukan.");
                continue;
            }

            if (idxFrom == idxTo) {
                System.out.println("Tidak bisa follow diri sendiri.");
                continue;
            }

            if (followMatrix[idxFrom][idxTo] == 1) {
                System.out.println("Sudah mem-follow sebelumnya.");
                continue;
            }

            followMatrix[idxFrom][idxTo] = 1;
            System.out.println(from + " berhasil follow " + to);
            count++;
        }

        // Tampilkan daftar followers & following
        for (int i = 0; i < users.length; i++) {
            System.out.println("\n== User: " + users[i].getUsername() + " ==");

            System.out.println("Followers:");
            boolean adaFollower = false;
            for (int j = 0; j < users.length; j++) {
                if (followMatrix[j][i] == 1) {
                    System.out.println("- " + users[j].getUsername());
                    adaFollower = true;
                }
            }
            if (!adaFollower) {
                System.out.println("Belum memiliki followers.");
            }

            System.out.println("Following:");
            boolean adaFollowing = false;
            for (int j = 0; j < users.length; j++) {
                if (followMatrix[i][j] == 1) {
                    System.out.println("- " + users[j].getUsername());
                    adaFollowing = true;
                }
            }
            if (!adaFollowing) {
                System.out.println("Belum mengikuti siapa pun.");
            }
        }

        // Tampilkan user tanpa followers
        System.out.println("\nUser tanpa followers:");
        boolean ada = false;
        for (int i = 0; i < users.length; i++) {
            boolean punyaFollower = false;
            for (int j = 0; j < users.length; j++) {
                if (followMatrix[j][i] == 1) {
                    punyaFollower = true;
                    break;
                }
            }
            if (!punyaFollower) {
                System.out.println("- " + users[i].getUsername());
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Semua user sudah punya followers.");
        }
    }

    static int cariIndex(String name) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}
