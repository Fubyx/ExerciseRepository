package _5AT.TP.ClientServer.MainServer;

public class IsPrimeTemplate extends SubServerTemplate {
    @Override
    public String[] getCommands() {
        return new String[]{"/isPrime", "done"};
    }

    @Override
    public String doCommand(String[] command) {
        if (command.length < 2) {
            return "also enter a number (example: /isPrime 5)";
        }
        boolean val;
        try {
            val = isPrime(Long.parseLong(command[1]));
        } catch (NumberFormatException e) {
            val = false;
        }
        return Boolean.toString(val);
    }

    private boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i < Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
