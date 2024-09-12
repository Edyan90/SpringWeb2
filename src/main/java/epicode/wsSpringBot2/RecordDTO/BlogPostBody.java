package epicode.wsSpringBot2.RecordDTO;


import jakarta.validation.constraints.*;

import java.util.UUID;

public record BlogPostBody(
        @NotEmpty(message = "manca la categoria")
        @Size(min = 3, max = 20, message = "la categoria deve avere un minimo di 2 ad un massimo di 20 caratteri")
        String categoria,

        @NotEmpty(message = "manca il titolo")
        @Size(min = 3, max = 20, message = "il titolo deve avere un minimo di 2 ad un massimo di 20 caratteri")
        String titolo,

        @NotEmpty(message = "manca il contenuto")
        @Size(min = 3, max = 50, message = "il contenuto deve avere un minimo di 2 ad un massimo di 50 caratteri")
        String contenuto,

        @NotNull(message = "manca il tempo di lettura")
        @Min(value = 1, message = "il tempo di lettura deve essere almeno 1 minuto")
        @Max(value = 999, message = "il tempo di lettura non può superare i 999 minuti")
        Integer tempoDiLettura,

        @NotNull(message = "manca l'id dell'autore")
        UUID autoreID) {
}
