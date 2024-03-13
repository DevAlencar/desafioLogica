import java.util.ArrayList;
import java.util.Comparator;

public class TrueTest extends Input {
    public TrueTest(String logicalSentence) {
        super(logicalSentence);
    }

    private int qttPoint = 0;
    ArrayList<Character> symbols = new ArrayList<Character>();
    ArrayList<Character> connectives = new ArrayList<Character>();
    private int qttTests;

    private void casosTeste() {

        char wordArray[] = logicalSentence.toCharArray();

        for (int i = 0; i < size; i++) {
            if (isPunctuation(wordArray[i])) {
                qttPoint++;
            }
            if (isSymbol(wordArray[i])) {
                if (!symbols.contains(wordArray[i])) {
                    symbols.add(wordArray[i]);
                }
            }
            if (isTrueOrFalse(wordArray[i])) {

            }
            if (isConnective(wordArray[i]) || isEspConnective(wordArray[i])) {
                connectives.add(wordArray[i]);
            }
        }
        qttTests = (int) Math.pow(2, symbols.size());
    }

    public int[][] createTests(String logicalSentence) {
        casosTeste();
        ArrayList<String> exp = new ArrayList<String>();
        char wordArray[] = logicalSentence.toCharArray();
        int table[][] = new int[(qttTests)][symbols.size() + connectives.size()];

        for (int i = 0; i < symbols.size(); i++) {
            int aux = 0;
            exp.add(symbols.get(i).toString());
            for (int j = 0; j < qttTests; j++) {

                while (aux < (qttTests / (int) Math.pow(2, i + 1))) {
                    aux++;
                    table[j][i] = 1;
                    j++;
                }
                j--;
                while (aux > 0) {
                    aux--;
                    j++;
                    table[j][i] = 0;
                }
            }
        }

        for (int i = 0; i < wordArray.length; i++) {
            if (isEspConnective(wordArray[i])) {
                ArrayList<Character> auxiliar = new ArrayList<Character>();
                int moveD = i;

                while (!isClosePunctuation(wordArray[moveD])) {
                    moveD++;
                }

                for (int k = 0; k <= moveD - i; k++) {
                    auxiliar.add(wordArray[i + k]);
                }

                String replace = auxiliar.toString();
                replace = replace.replaceAll(",", "");
                replace = replace.replaceAll("\\[", "");
                replace = replace.replaceAll("\\]", "");
                replace = replace.replaceAll(" ", "");
                if (replace.length() == 3) {
                    replace = replace.replaceAll("\\)", "");
                }

                exp.add(replace);
                exp.sort(Comparator.comparing(String::length));
            }
            if (isConnective(wordArray[i])) {
                ArrayList<Character> auxiliar = new ArrayList<Character>();
                int moveD = i;
                int moveE = i;
                int auxMoveO = 0;
                int auxMoveC = 0;
                while (!isPunctuation(wordArray[moveE]) || auxMoveO < auxMoveC) {
                    if (isClosePunctuation(wordArray[moveE])) {
                        auxMoveC++;
                    } else if (isPunctuation(wordArray[moveE])) {
                        auxMoveO++;
                    }
                    moveE--;
                }
                auxMoveO = 0;
                auxMoveC = 0;
                while (!isClosePunctuation(wordArray[moveD]) || auxMoveO > auxMoveC) {
                    if (isClosePunctuation(wordArray[moveD])) {
                        auxMoveC++;
                    } else if (isPunctuation(wordArray[moveD])) {
                        auxMoveO++;
                    }
                    moveD++;
                }
                for (int k = 0; k <= moveD - moveE; k++) {
                    auxiliar.add(wordArray[moveE + k]);
                }
                String replace = auxiliar.toString();
                replace = replace.replaceAll(",", "");
                replace = replace.replaceAll("\\[", "");
                replace = replace.replaceAll("\\]", "");
                replace = replace.replaceAll(" ", "");
                exp.add(replace);
                exp.sort(Comparator.comparing(String::length));
            }
        }

        for (int i = symbols.size(); i < exp.size(); i++) {
            String auxS = exp.get(i);
            auxS = auxS.replace(" ", "");
            char auxC[] = auxS.toCharArray();
            int colA;
            int colB;

            // resolver negação
            if (isEspConnective(auxS.toCharArray()[0])) {
                char aux[] = new char[auxC.length - 1];
                for (int t = 0; t < auxC.length - 1; t++) {
                    aux[t] = auxC[t + 1];
                }
                String compS = new String(aux);
                if (compS.length() == 2 || compS.length() == 3 || compS.length() == 4) {
                    compS = compS.replaceAll("\\)", "");
                    compS = compS.replaceAll("\\(", "");
                }
                colA = exp.indexOf(compS);

                for (int s = 0; s < qttTests; s++) {
                    table[s][i] = table[s][colA] == 1 ? 0 : 1;
                }
            } else {
                ArrayList<Character> auxiliarC = new ArrayList<Character>();
                int indexCone = ((auxC.length + 1) / 2) - 1;
                for (int y = 0; y < auxC.length; y++) {
                    if (y > 0) {
                        if (isConnective(auxC[y - 1]) && isPunctuation(auxC[y])) {
                            indexCone = y - 1;
                        } else if (isConnective(auxC[y]) && isClosePunctuation(auxC[y - 1])) {
                            indexCone = y;
                        }
                    }
                }
                int moveE = indexCone - 1;
                while (!isPunctuation(auxC[moveE])) {
                    moveE--;
                }
                for (int u = 0; u < indexCone - moveE; u++) {
                    auxiliarC.add(auxC[moveE + u]);
                }
                String s1 = new String(auxiliarC.toString());
                s1 = s1.replaceAll(",", "");
                s1 = s1.replaceAll(" ", "");
                s1 = s1.replaceAll("\\[", "");
                s1 = s1.replaceAll("\\]", "");
                if (s1.length() == 2) {
                    s1 = s1.replaceAll("\\(", "");
                }

                colA = exp.indexOf(s1);
                auxiliarC.clear();
                int moveD = indexCone + 1;
                while (!isClosePunctuation(auxC[moveD])) {
                    auxiliarC.add(auxC[moveD]);
                    moveD++;
                }
                auxiliarC.add(')');
                String s2 = new String(auxiliarC.toString());
                s2 = s2.replaceAll(",", "");
                s2 = s2.replaceAll(" ", "");
                s2 = s2.replaceAll("\\[", "");
                s2 = s2.replaceAll("\\]", "");
                if (s2.length() == 2 || s2.length() == 3) {
                    s2 = s2.replaceAll("\\)", "");
                }
                colB = exp.indexOf(s2);

                // or
                if (auxC[indexCone] == '|') {
                    for (int s = 0; s < qttTests; s++) {
                        table[s][i] = (table[s][colA] == 1 || table[s][colB] == 1) ? 1 : 0;
                    }
                }
                // and
                if (auxC[indexCone] == '^') {
                    for (int s = 0; s < qttTests; s++) {
                        table[s][i] = table[s][colA] * table[s][colB];
                    }
                }
                // cond
                if (auxC[indexCone] == '>') {
                    for (int s = 0; s < qttTests; s++) {
                        if (table[s][colA] == 1 && table[s][colB] == 0) {
                            table[s][i] = 0;
                        } else {
                            table[s][i] = 1;
                        }
                    }
                }
                // bicond
                if (auxC[indexCone] == '=') {
                    for (int s = 0; s < qttTests; s++) {
                        if (table[s][colA] == table[s][colB]) {
                            table[s][i] = 1;
                        } else {
                            table[s][i] = 0;
                        }
                    }
                }
            }
        }

        String tipo;
        boolean allT = true;
        boolean allF = true;
        boolean oneT = false;
        boolean oneF = false;
        int result[] = new int[qttTests];

        for (int i = 0; i < exp.size(); i++) {
            System.out.print(exp.get(i));
            System.out.print("\t");
        }
        System.out.print("\n");

        for (int i = 0; i < qttTests; i++) {
            for (int j = 0; j < symbols.size() + connectives.size(); j++) {
                System.out.print(table[i][j]);
                System.out.print("\t");
                if (j == symbols.size() + connectives.size() - 1) {
                    result[i] = table[i][j];
                }
            }
            System.out.print("\n");
        }

        for (int i = 0; i < qttTests; i++) {
            if (result[i] == 0) {
                oneF = true;
                allT = false;
            } else if (result[i] == 1) {
                oneT = true;
                allF = false;
            }
        }

        if (allT == true) {
            tipo = "tautologia";
            System.out.println(tipo);
        }
        if (allF == true) {
            tipo = "Contradicao";
            System.out.println(tipo);
        }
        if (oneT == true) {
            tipo = "satisfatível";
            System.out.println(tipo);
        }
        if (oneT == true && oneF == true) {
            tipo = "contigencia";
            System.out.println(tipo);
        }

        return table;

    }
}
