package epicode.wsSpringBot2.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@ToString
public class BlogPostBody {

    private String categoria;
    private String titolo;
    private String contenuto;
    private Integer tempoDiLettura;
    private UUID autoreID;

    public BlogPostBody(String categoria, String titolo, String cover, String contenuto, Integer tempoDiLettura, UUID autoreID) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autoreID = autoreID;
    }
}
