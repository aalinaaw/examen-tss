import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exam {
    public static void main(String[] args) throws IOException {
        List<String> inputList = Files.readAllLines(Paths.get("src/main/resources/test-1.txt"));
        int N = Integer.parseInt(inputList.get(0));
        int[] cheat = Arrays.stream(inputList.get(1).split(" ")).collect(Collectors.toList()).stream().mapToInt(Integer::parseInt).toArray();

        int[] res = solve(N, cheat);

        System.out.println(Arrays.toString(res));
    }

    public static int[] solve(int N, int[] cheat) {
        if (N < 4 || N > 100_000) {
            throw new IllegalArgumentException("the N argument is not in the required interval - 4 <= N <= 100.000");
        }

        if (cheat == null || cheat.length == 0) {
            throw new IllegalArgumentException("the cheat argument is null or empty");
        }

        if (cheat.length != N) {
            throw new IllegalArgumentException("the cheat argument has a number of elements different than the N argument");
        }

        if (Arrays.stream(cheat).anyMatch(value -> value > 1_000_000_000 || value < -1_000_000_000)) {
            throw new IllegalArgumentException("a value in the cheat argument array is greater than 1.000.000.000 or lesser than -1.000.000.000");
        }

        int typeN = N % 4;

        if (typeN == 0) {
            return new int[]{-1};
        }

        int S = 0;
        for (int i = 0; i < N; ++i) {
            S += cheat[i];
        }
        S /= 2;

        int[] part = new int[N];
        for (int i = 0; i < N; ++i) {
            part[i] = S;
            for (int j = 2; j < N - typeN; j += 4) {
                part[i] -= cheat[rotatingIndex(N, i, j)];
                part[i] -= cheat[rotatingIndex(N, i, j + 1)];
            }
        }

        int[] res = new int[N];
        switch (typeN) {
            case 1:
                // a b c d
                // N is greater than or equal than 0
                System.arraycopy(part, 0, res, 0, N);
                break;
            case 2:
                // d+a a+b b+c c+d
                for (int i = 0; i < N; ++i) {
                    res[i] = (part[rotatingIndex(N, i, 1)] + part[i] - cheat[i]) / 2;
                }
                break;
            case 3:
                // c+d+a d+a+b a+b+c b+c+d
                for (int i = 0; i < N; ++i) {
                    res[i] = part[rotatingIndex(N, i, 1)] - cheat[i];
                }
                break;
            default:
                break;
        }

        return res;
    }

    public static int rotatingIndex(int N, int index, int offset) {
        index += offset;
        while (index < 0) {
            index += N;
        }

        return index % N;
    }
}
