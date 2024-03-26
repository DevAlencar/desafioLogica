public class Input {

    protected String logicalSentence;
    protected int size;

    public Input(String logicalSentence) {
        this.logicalSentence = logicalSentence;
        this.size = logicalSentence.length();
    }

    private int[] createValidation(String word) {
        int[] aux = new int[size];
        char wordArray[] = word.toCharArray();

        for (int i = 0; i < size; i++) {
            if (isPunctuation(wordArray[i])) {
                aux[i] = 2;
            } else if (isClosePunctuation(wordArray[i])) {
                aux[i] = 2;
            } else if (isSymbol(wordArray[i])) {
                aux[i] = 2;
            } else if (isConnective(wordArray[i])) {
                aux[i] = 3;
            } else if (isEspConnective(wordArray[i])) {
                aux[i] = 2;
            } else if (isTrueOrFalse(wordArray[i])) {
                aux[i] = 2;
            } else {
                System.out.println("Setença lógica inserida de maneira incorreta");
                System.exit(0);
            }
        }
        return aux;
    }

    public int validation() {
        int[] vetor = createValidation(logicalSentence);
        char wordArray[] = logicalSentence.toCharArray();
        int aux = vetor[0];
        int openPoint = 0;
        int closePoint = 0;

        // conectivo no inicio
        if (isConnective(wordArray[0])) {
            return 1;
        }

        // conectivo no final
        if (isConnective(wordArray[size - 1])) {
            return size;
        }

        for (int i = 0; i < size; i++) {
            if (isPunctuation(wordArray[i])) {
                openPoint++;
            }
            if (isClosePunctuation(wordArray[i])) {
                closePoint++;
            }
            if (i > 0) {
                if (isSymbol(wordArray[i - 1]) && isEspConnective(wordArray[i])) {
                    return (i + 1);
                }
                if (isConnective(wordArray[i]) && isEspConnective(wordArray[i - 1])) {
                    return (i + 1);
                }
            }
            if (Math.abs(vetor[i] - aux) > 1) {
                return (i + 1);
            }
            aux = vetor[i];
        }
        if (openPoint != closePoint) {
            return -2; // erro para parênteses errados
        }
        return -1;
    }

    public boolean isPunctuation(char obj) {
        if (obj == '(') {
            return true;
        }
        return false;
    }

    public boolean isClosePunctuation(char obj) {
        if (obj == ')') {
            return true;
        }
        return false;
    }

    public boolean isTrueOrFalse(char obj) {
        if (obj == 't' || obj == 'f') {
            return true;
        }
        return false;
    }

    public boolean isSymbol(char obj) {
        if (obj == 'P' || obj == 'Q' || obj == 'R' || obj == 'S' || obj == 'T' || obj == 'U') {
            return true;
        }
        return false;
    }

    public boolean isConnective(char obj) {
        if (obj == '|' || obj == '^' || obj == '>' || obj == '=') {
            return true;
        }
        return false;
    }

    public boolean isEspConnective(char obj) {
        if (obj == '~') {
            return true;
        }
        return false;
    }

}
