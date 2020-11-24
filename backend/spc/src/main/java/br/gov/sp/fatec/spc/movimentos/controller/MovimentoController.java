package br.gov.sp.fatec.spc.movimentos.controller;

import br.gov.sp.fatec.spc.movimentos.model.Movimento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentoController {
    @GetMapping("/{mes}/{ano}")
    public List<Movimento> find(@PathVariable("mes") String mes, @PathVariable("ano") Integer ano) {
        return List.of(Movimento.builder()
                .datVencimento(1l)
                .periodicidade("Periodicidade 1")
                .build());
    }
}
