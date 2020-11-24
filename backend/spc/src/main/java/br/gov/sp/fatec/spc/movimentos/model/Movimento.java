package br.gov.sp.fatec.spc.movimentos.model;

import br.gov.sp.fatec.spc.fonte.model.Fonte;
import br.gov.sp.fatec.spc.pessoafisica.model.PessoaFisica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "movimentos")
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"pessoaFisica", "fonte"})
public class Movimento {
    @PrimaryKeyJoinColumn(name = "doc_cli", columnDefinition = "doc_cli", referencedColumnName = "doc_cli")
    private PessoaFisica pessoaFisica;

    @PrimaryKeyJoinColumn(name = "id_fnt", columnDefinition = "id_fnt", referencedColumnName = "id_fnt")
    private Fonte fonte;

    @Id
    @Column(name = "tip_cli")
    private String tipoCliente;

    @Column(name = "num_unc")
    private Long numeroContrato;

    @Column(name = "dat_vct")
    private Long datVencimento;

    @Column(name = "qtd_pcl_vct")
    private Long qntParcelaVencimento;

    @Column(name = "qtd_pcl_pgr")
    private Long qntParcelaAPagar;

    @Column(name = "vlr_total_fat")
    private Double valorTotalFat;

    @Column(name = "vlr_min_fat")
    private Double valorMinFat;

    @Column(name = "valor_pcl")
    private Double valorParcela;

    @Column(name = "tip_mtv")
    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;

    @Column(name = "prd")
    private String periodicidade;

}
