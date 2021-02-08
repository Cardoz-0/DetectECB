package main.src.java.cardoso.detectecb;


import main.src.java.cardoso.detectecb.useful.StringUtils;
import main.src.java.cardoso.detectecb.useful.UserInput;


import java.util.HashSet;
import java.util.List;

public class DetectECB {
    //Em ECB o mesmo bloco de 16 bytes gera o mesmo ciphertext. Logo, caso ocorra alguma repetição no texto original
    //dentro desses 16bytes, haverá um bloco repetido. Repetições de bytes são raras na cryptografia em geral,
    //mas são comuns em ECB. Esse algoritmo procura por essa repetição para determinar ciphertext encryptado em ECB;

    public static void main(String[] args) {
        String entrada = UserInput.fileInput("INPUT-detect_ECB.txt");

        String[] frases = new String[204];
        for (int i = 0; i < 204; i++) {
            frases[i] = entrada.substring((i * 320), ((i + 1) * 320));
        }
        for (int i = 0; i < 204; i++) {
            List<String> blocks = StringUtils.splitStringToFixedChunks(frases[i], 16); //Criação dos blocos de 16bytes (sim eu cansei de usar arrays de tamanho determinado)
            if (blocks.size() != new HashSet<>(blocks).size()) {
                //Joga-se no hash para ver se há algum conjunto de 16 bytes repetido, caso esse seja o caso o tamanho
                // do hash nao aumentará, e haverá uma diferença na quantidade de bytes do conjunto, e a quantidade de
                //bytes diferentes
                System.out.println(frases[i]);
                System.out.println("Numero da Linha de texto: "+i);
                break;
            }
        }
    }
}


