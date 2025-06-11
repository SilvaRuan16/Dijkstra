package capital.dijkstra.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adjacente {
  private Integer destino;
  private Double distancia;
}