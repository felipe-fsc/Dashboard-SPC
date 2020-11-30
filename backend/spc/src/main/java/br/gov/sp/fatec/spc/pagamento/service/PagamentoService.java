package br.gov.sp.fatec.spc.pagamento.service;

import br.gov.sp.fatec.spc.pagamento.model.Pagamento;
import br.gov.sp.fatec.spc.pagamento.payload.PagamentoData;
import br.gov.sp.fatec.spc.pagamento.payload.ScorePessoa;
import br.gov.sp.fatec.spc.pagamento.payload.StatusPagamento;
import br.gov.sp.fatec.spc.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Set<PagamentoData> contabilizar() {
        final Set<PagamentoData> pagamentos = new HashSet();

        final Integer todos = buscarTodos().size();
        final Integer atrasados = buscarPagamentosAtrasados().size();
        final Integer pagos = buscarPagamentosLiquidados().size();
        final Integer pendentes = buscarPagamentosPendentes().size();
        final Integer pagoAtrasados = buscarPagosAtrasados().size();
        final Integer pagoEmDia = buscarPagosEmDia().size();

        final Double percentualAtrasados = (atrasados * 100d) / todos;
        final Double percentualPagos = (pagos * 100d) / todos;
        final Double percentualPendentes = (pendentes * 100d) / todos;
        final Double percentualPagosEmDia = (pagoEmDia * 100d) / todos;
        final Double percentualPagosAtrasados = (pagoAtrasados * 100d) / todos;

        pagamentos.add(new PagamentoData(StatusPagamento.ATRASADO, percentualAtrasados));
        pagamentos.add(new PagamentoData(StatusPagamento.PAGO, percentualPagos));
        pagamentos.add(new PagamentoData(StatusPagamento.PENDENTE, percentualPendentes));
        pagamentos.add(new PagamentoData(StatusPagamento.PAGO_EM_DIA, percentualPagosEmDia));
        pagamentos.add(new PagamentoData(StatusPagamento.PAGO_ATRASADO, percentualPagosAtrasados));
        return pagamentos;
    }

    private List<Pagamento> buscarTodos() {
        return pagamentoRepository.findAll();
    }

    private List<Pagamento> buscarPagamentosAtrasados() {
        return pagamentoRepository.findAllByAtrasado();
    }

    private List<Pagamento> buscarPagamentosLiquidados() {
        return pagamentoRepository.findAllByPago();
    }

    private List<Pagamento> buscarPagamentosPendentes() {
        return pagamentoRepository.findAllByPendentes();
    }

    private List<Pagamento> buscarPagosAtrasados() {
        return pagamentoRepository.findAllByPagosAtrasados();
    }

    private List<Pagamento> buscarPagosEmDia() {
        return pagamentoRepository.findAllByPagosEmDia();
    }

    private List<Pagamento> buscarTodosPagsPessoa(final String pessoaFisica) {
        return pagamentoRepository.findByTodosPagsPessoa(pessoaFisica);
    }

    private List<Pagamento> buscarPagsPagosPessoa(final String pessoaFisica) {
        return pagamentoRepository.findByPagsPagosPessoa(pessoaFisica);
    }

    private List<Pagamento> buscarPagsAtrasadoPessoa(final String pessoaFisica) {
        return pagamentoRepository.findByPagsAtrasadoPessoa(pessoaFisica);
    }

    private List<Pagamento> buscarPagsPagosAtrasadoPessoa(final String pessoaFisica) {
        return pagamentoRepository.findByPagsPagoAtrasadoPessoa(pessoaFisica);
    }

    public ScorePessoa buscarScorePessoa(final String pessoaFisica){

        final Integer todosPagamentos = buscarTodosPagsPessoa(pessoaFisica).size();
        Integer pagPagoPessoa = buscarPagsPagosPessoa(pessoaFisica).size();
        Integer pagAtrasadoPessoa = buscarPagsAtrasadoPessoa(pessoaFisica).size();
        Integer pagPagoAtrasadoPessoa = buscarPagsPagosAtrasadoPessoa(pessoaFisica).size();

        pagPagoPessoa = (pagPagoPessoa * 100) / todosPagamentos;
        pagAtrasadoPessoa = (pagAtrasadoPessoa * 100) / todosPagamentos;
        pagPagoAtrasadoPessoa = (pagPagoAtrasadoPessoa * 100) / todosPagamentos;

        Integer scorePessoa = 0;
        scorePessoa = scorePessoa - pagAtrasadoPessoa;
        scorePessoa = scorePessoa - (pagPagoAtrasadoPessoa / 2);
        scorePessoa = scorePessoa + pagPagoPessoa;
        if(scorePessoa > 100)  {
            scorePessoa = 100;
        }
        return new ScorePessoa(pessoaFisica, scorePessoa * 10);
    }

}
