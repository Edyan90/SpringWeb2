package epicode.wsSpringBot2.RecordDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "manca il nome")
        @Size(min = 2, max = 20, message = "il nome deve avere un minimo di 2 ad un massimo di 20 caratteri")
        String nome,
        @NotEmpty(message = "manca il cognome")
        @Size(min = 3, max = 20, message = "il cognome deve avere un minimo di 3 ad un massimo di 20 caratteri")
        String cognome,
        @NotEmpty(message = "manca l'email")
        @Size(min = 3, max = 20, message = "l'email deve avere un minimo di 2 ad un massimo di 20 caratteri")
        String email,
        @NotNull(message = "manca la data Di Nascita")
        @Size(min = 10, max = 11, message = "la data Di Nascita deve avere un minimo di 10 caratteri con questo formato YYYY-MM-DD")
        LocalDate dataDiNascita) {
}
