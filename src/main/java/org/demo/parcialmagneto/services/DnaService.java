package org.demo.parcialmagneto.services;

import org.demo.parcialmagneto.entities.Dna;
import org.demo.parcialmagneto.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DnaService {

    // Inyectamos una instancia de DnaRepository en el constructor de DnaService
    private final DnaRepository dnaRepository;

    // Longitud de la secuencia de ADN que se considera mutante
    private static final int longitudcadena = 4;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    // Método estático que determina si una secuencia de ADN es mutante o no
    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int contadorlongitud = 0;

        // Verificamos las filas de la matriz de ADN
        contadorlongitud += checkRows(dna, n);
        if (contadorlongitud > 1) return true;

        // Verificamos las columnas de la matriz de ADN
        contadorlongitud += checkColumns(dna, n);
        if (contadorlongitud > 1) return true;

        // Verificamos las diagonales de la matriz de ADN
        contadorlongitud += checkDiagonals(dna, n);
        return contadorlongitud > 1;
    }

    // Método privado estático que verifica las filas de la matriz de ADN
    private static int checkRows(String[] dna, int n) {
        int contadorlongitud = 0;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 1; j < n; j++) {
                // Si el carácter actual es igual al carácter anterior, incrementamos el contador
                if (dna[i].charAt(j) == dna[i].charAt(j - 1)) {
                    count++;
                    // Si el contador alcanza la longitud de la secuencia mutante, incrementamos el contador de secuencias mutantes
                    if (count == longitudcadena) {
                        contadorlongitud++;
                        if (contadorlongitud > 1) return contadorlongitud;
                    }
                } else {
                    // Si el carácter actual es diferente al carácter anterior, reiniciamos el contador
                    count = 1;
                }
            }
        }
        return contadorlongitud;
    }

    // Método privado estático que verifica las columnas de la matriz de ADN
    private static int checkColumns(String[] dna, int n) {
        int contadorlongitud = 0;

        for (int j = 0; j < n; j++) {
            int count = 1;
            for (int i = 1; i < n; i++) {
                // Si el carácter actual es igual al carácter anterior, incrementamos el contador
                if (dna[i].charAt(j) == dna[i - 1].charAt(j)) {
                    count++;
                    // Si el contador alcanza la longitud de la secuencia mutante, incrementamos el contador de secuencias mutantes
                    if (count == longitudcadena) {
                        contadorlongitud++;
                        if (contadorlongitud > 1) return contadorlongitud;
                    }
                } else {
                    // Si el carácter actual es diferente al carácter anterior, reiniciamos el contador
                    count = 1;
                }
            }
        }
        return contadorlongitud;
    }

    // Método privado estático que verifica las diagonales de la matriz de ADN
    private static int checkDiagonals(String[] dna, int n) {
        int contadorlongitud = 0;

        // Verificamos las diagonales de izquierda (arriba) a derecha (abajo)
        for (int i = 0; i <= n - longitudcadena; i++) {
            for (int j = 0; j <= n - longitudcadena; j++) {
                if (checkDiagonal(dna, i, j, 1, 1, n)) {
                    contadorlongitud++;
                    if (contadorlongitud > 1) return contadorlongitud; // Early exit
                }
            }
        }

        // Verificamos las diagonales de derecha (arriba) a izquierda (abajo)
        for (int i = 0; i <= n - longitudcadena; i++) {
            for (int j = longitudcadena - 1; j < n; j++) {
                if (checkDiagonal(dna, i, j, 1, -1, n)) {
                    contadorlongitud++;
                    if (contadorlongitud > 1) return contadorlongitud;
                }
            }
        }
        return contadorlongitud;
    }

    // Método privado estático que verifica una diagonal de la matriz de ADN
    private static boolean checkDiagonal(String[] dna, int x, int y, int dx, int dy, int n) {
        char first = dna[x].charAt(y);
        for (int i = 1; i < longitudcadena; i++) {
            // Si la diagonal no cumple con la longitud de la secuencia mutante o si el carácter actual es diferente al carácter anterior, devolvemos false
            if (x + i * dx >= n || y + i * dy >= n || y + i * dy < 0 || dna[x + i * dx].charAt(y + i * dy) != first) {
                return false;
            }
        }
        // Si la diagonal cumple con la longitud de la secuencia mutante, devolvemos true
        return true;
    }

    // Método que analiza una secuencia de ADN y determina si es mutante o no
    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verificamos si el ADN ya existe en la base de datos
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            // Si el ADN ya existe, devolvemos el valor de la propiedad isMutant de la entidad Dna recuperada de la base de datos
            return existingDna.get().isMutant();
        }

        // Si el ADN no existe, determinamos si es mutante o no llamando al método isMutant
        boolean isMutant = isMutant(dna);
        // Creamos una nueva entidad Dna con la secuencia de ADN y el valor de la propiedad isMutant
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        // Guardamos la entidad Dna en la base de datos
        dnaRepository.save(dnaEntity);

        // Devolvemos el valor de la propiedad isMutant de la entidad Dna creada
        return isMutant(dna);
    }
}