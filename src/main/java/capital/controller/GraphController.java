package capital.controller;

import capital.service.GraphService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grafo")
public class GraphController {

    private final GraphService graphService;

    private final String[] nomesCapitais = {
            "Aracaju", "Belém", "Belo Horizonte", "Boa Vista", "Brasília",
            "Campo Grande", "Cuiabá", "Curitiba", "Florianópolis", "Fortaleza",
            "Goiânia", "João Pessoa", "Macapá", "Maceió", "Manaus",
            "Natal", "Palmas", "Porto Alegre", "Porto Velho", "Recife",
            "Rio Branco", "Rio de Janeiro", "Salvador", "São Luís",
            "São Paulo", "Teresina", "Vitória"
    };

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
        inicializarGrafo();
    }

    private void inicializarGrafo() {
        graphService.initializeGraph(27);

        graphService.addEdge(0, 13, 322);
        graphService.addEdge(0, 22, 501);
        graphService.addEdge(1, 12, 1100);
        graphService.addEdge(1, 23, 700);
        graphService.addEdge(2, 21, 570);
        graphService.addEdge(2, 24, 430);
        graphService.addEdge(2, 26, 340);
        graphService.addEdge(3, 14, 2100);
        graphService.addEdge(4, 10, 430);
        graphService.addEdge(4, 16, 880);
        graphService.addEdge(5, 6, 730);
        graphService.addEdge(5, 24, 690);
        graphService.addEdge(6, 16, 1100);
        graphService.addEdge(7, 8, 300);
        graphService.addEdge(7, 24, 400);
        graphService.addEdge(8, 17, 410);
        graphService.addEdge(9, 15, 550);
        graphService.addEdge(9, 25, 700);
        graphService.addEdge(10, 16, 620);
        graphService.addEdge(11, 15, 250);
        graphService.addEdge(11, 19, 180);
        graphService.addEdge(12, 14, 1600);
        graphService.addEdge(13, 19, 280);
        graphService.addEdge(14, 20, 2300);
        graphService.addEdge(15, 19, 170);
        graphService.addEdge(17, 24, 750);
        graphService.addEdge(19, 22, 400);
        graphService.addEdge(21, 24, 310);
        graphService.addEdge(21, 26, 280);
        graphService.addEdge(22, 26, 520);
        graphService.addEdge(23, 25, 570);
    }

    @GetMapping("/dijkstra")
    public Map<String, Object> encontrarCaminho(@RequestParam int origem, @RequestParam int destino) {
        if (origem < 0 || origem >= nomesCapitais.length || destino < 0 || destino >= nomesCapitais.length) {
            return Map.of("error", "Índice de capital inválido.");
        }
        return graphService.findShortestPathDijkstra(origem, destino, nomesCapitais);
    }

    @GetMapping("/capitais")
    public List<String> listarCapitais() {
        return List.of(nomesCapitais);
    }
}
