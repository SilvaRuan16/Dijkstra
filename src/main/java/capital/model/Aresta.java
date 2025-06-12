package capital.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aresta {
  private String origem;
  private String destino;
}
