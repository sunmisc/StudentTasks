package zelvalea.tasks.legacy.vm;

import java.util.Arrays;

public class EigenvalueDecomposition {

    // Количество шагов итерации
    private static final int MAX_ITERATIONS = 1000;
    // Допустимая погрешность
    private static final double EPSILON = 1e-10;


    // Собственные числа
    private final double[] eigenvalues;
    // Собственные вектора
    private final double[][] eigenvectors;


    public EigenvalueDecomposition(double[][] A) {
        // Размерность матрицы
        int n = A.length;
        eigenvalues = new double[n];
        eigenvectors = new double[n][n];

        // Инициализируем собственные вектора единичной матрицей
        for (int i = 0; i < n; i++) {
            eigenvectors[i][i] = 1.0;
        }

        // Выполняем итерации метода Крылова
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            // Находим самый большой элемент на главной диагонали вне главной диагонали
            int p = 0;
            int q = 0;
            double max = 0.0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double value = Math.abs(A[i][j]);
                        if (value > max) {
                            max = value;
                            p = i;
                            q = j;
                        }
                    }
                }
            }
            // Если максимальный элемент маленький, то матрица уже достаточно диагональная
            if (max < EPSILON) {
                break;
            }

            // Вычисляем угол поворота
            double theta = (A[q][q] - A[p][p]) / (2 * A[p][q]);
            double t = 1.0 / (Math.abs(theta) + Math.sqrt(theta * theta + 1));
            if (theta < 0.0) {
                t = -t;
            }

            double c = 1.0 / Math.sqrt(t * t + 1);
            double s = t * c;

            // Применяем поворот к матрице A
            A[p][p] -= t * A[p][q];
            A[q][q] += t * A[p][q];
            A[p][q] = 0.0;
            A[q][p] = 0.0;
            for (int i = 0; i < n; i++) {
                if (i != p && i != q) {
                    double apiq = A[i][p];
                    A[i][p] = c * apiq - s * A[i][q];
                    A[i][q] = s * apiq + c * A[i][q];
                    A[p][i] = A[i][p];
                    A[q][i] = A[i][q];
                }
            }

            // Применяем поворот к собственным векторам
            for (int i = 0; i < n; i++) {
                double vp = eigenvectors[i][p];
                double vq = eigenvectors[i][q];
                eigenvectors[i][p] = c * vp - s * vq;
                eigenvectors[i][q] = s * vp + c * vq;
            }
        }
        // Копируем собственные числа в массив
        for (int i = 0; i < n; i++) {
            eigenvalues[i] = A[i][i];
        }
    }

    // Метод возвращает массив собственных чисел
    public double[] getEigenvalues() {
        return eigenvalues;
    }

    // Метод возвращает массив собственных векторов
    public double[][] getEigenvectors() {
        return eigenvectors;
    }

    public static void main(String[] args) {
        double[][] matrixData = {
                {2.2, 1, 0.5, 2},
                {1, 1.3, 2, 1},
                {0.5, 2, 0.5, 1.6},
                {2,1,1.6,2}
        };
        EigenvalueDecomposition decomposition = new EigenvalueDecomposition(matrixData);

        double[] eigenvalues = decomposition.getEigenvalues();
        double[][] eigenvectors = decomposition.getEigenvectors();

        System.out.println("Корни "+Arrays.toString(eigenvalues));


        for (double[] eigenvector : eigenvectors) {
            System.out.println(Arrays.toString(eigenvector));
        }
    }
}