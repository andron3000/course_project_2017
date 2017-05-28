package module.demonstration;

import com.google.common.base.Stopwatch;
import module.math.matrix.Matrix;
import module.math.matrix.Vector;
import module.math.matrix.Vectors;
import module.optimization.ConjugateGradientSolver;
import module.optimization.function.QuadraticFunction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Conjugate Gradient Solver:\n");

        Matrix A = new Matrix(readInputMatrixFromFile());
        Vector b = Vectors.dense(readInputVectorFromFile());

        solver(A, b, ConjugateGradientSolver.PreconditioningMethod.IDENTITY);
        //solver(A, b, ConjugateGradientSolver.PreconditioningMethod.JACOBI);
        //solver(A, b, ConjugateGradientSolver.PreconditioningMethod.SYMMETRIC_SUCCESSIVE_OVER_RELAXATION);
    }

    public static void solver(Matrix A, Vector b,
                       ConjugateGradientSolver.PreconditioningMethod preconditioningMethod) {

        double[] initialVector = new double[b.size()];
        Stopwatch stopwatch = Stopwatch.createStarted();

        ConjugateGradientSolver conjugateGradientSolver =
                new ConjugateGradientSolver.Builder(new QuadraticFunction(A, b),
                        Vectors.dense(initialVector))
                        .preconditioningMethod(preconditioningMethod)
                        .build();

        double[] x = conjugateGradientSolver.solve().getDenseArray();
        stopwatch.stop();

        System.out.println("Solution:\n");
        for (double element : x) {
            System.out.println(element);
        }
        System.out.println();

        System.out.println("elapsed time = "+ stopwatch.elapsed(MILLISECONDS) + " ms");
    }

    private static double[] readInputVectorFromFile() throws IOException {
        File file = new File("optimization/src/main/resources/vector100.txt");
        return Files.lines(Paths.get(file.getAbsolutePath()))
                .mapToDouble(Double::parseDouble).toArray();
    }

    private static double[][] readInputMatrixFromFile() throws IOException {
        File file = new File("optimization/src/main/resources/matrix100.txt");
        Path path = Paths.get(file.getAbsolutePath());
        return Files.lines(path)
                .map((l)->l.trim().split("\\s+"))
                .map((sa)-> Stream.of(sa).mapToDouble(Double::parseDouble).toArray())
                .toArray(double[][]::new);
    }
}
