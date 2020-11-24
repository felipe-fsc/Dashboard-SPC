package br.gov.sp.fatec.spc.pagamento.model;
import br.gov.sp.fatec.spc.fonte.model.Fonte;
import br.gov.sp.fatec.spc.modalidade.model.Modalidade;
import br.gov.sp.fatec.spc.movimentos.model.Movimento;
import br.gov.sp.fatec.spc.operacoes.model.Operacao;
import br.gov.sp.fatec.spc.pessoafisica.model.PessoaFisica;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
@Getter
@Builder
@EqualsAndHashCode(of = {"pessoaFisica", "fonte"})
public class Pagamento implements Serializable {
    @Id
    @JoinColumn(name = "doc_cli", columnDefinition = "doc_cli", referencedColumnName = "doc_cli")
    private PessoaFisica pessoaFisica;

    @Column(name = "tip_cli")
    private Long tipoCliente;

    @Id
    @JoinColumn(name = "id_fnt", columnDefinition = "id_fnt", referencedColumnName = "id_fnt")
    private Fonte fonte;

    @Column(name = "num_unc")
    private Long numContrato;

    @Column(name = "dat_pgt")
    private String dataPagParcela;

    @Column(name = "dat_vct")
    private LocalDate datVencimento;

    @Column(name = "vlr_pgt")
    private Double valorPago;

    @PrimaryKeyJoinColumn(name = "cod_mdl", columnDefinition = "cod_mdl", referencedColumnName = "cod_modalidade")
    private Modalidade modalidade;



}
