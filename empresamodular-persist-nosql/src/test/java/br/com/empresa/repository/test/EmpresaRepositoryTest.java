/******************************************************************************
 * Produto: Gestor de Empresas                                                *
 * Contmatic Phoenix © Desde 1986                                             *
 * Tecnologia em Softwares de Gestão Contábil, Empresarial e ERP              *
 * Todos os direitos reservados.                                              *
 *                                                                            *
 *                                                                            *
 *    Histórico:                                                              *
 *          Data        Programador              Tarefa                       *
 *          ----------  -----------------        -----------------------------*
 *   Autor  31/03/2016  william.salerno          Classe criada.        	      *
 *                                                                            *
 *   Comentários:                                                             *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *****************************************************************************/
package br.com.empresa.repository.test;

import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static java.lang.System.out;
import static org.joda.time.LocalDate.parse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.rules.ExpectedException.none;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.empresa.repository.EmpresaRepository;

/**
 * The Class EmpresaRepositoryTest.
 *
 * @author William
 */
@FixMethodOrder(NAME_ASCENDING)
public class EmpresaRepositoryTest {

    private Empresa empresa;
    private Empresa empresaUpdate;
    private Empresa empresaFind;
    private EmpresaRepository repository;
    private String cnpjFiltro;

    @Rule
    public ExpectedException thrown = none();

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        empresa = from(Empresa.class).gimme("empresa_valida");
        empresaUpdate = from(Empresa.class).gimme("empresa_valida");
        repository = new EmpresaRepository("localhost", 27017, "empresa");
        cnpjFiltro = empresa.getCnpj();
    }

    // Save====================================================================================================================
    @Test
    public void deve_incluir_empresa_uma_vez() {
        this.repository.saveEmpresa(this.empresa);
    }

    // Update===================================================================================================================
    @Test
    public void deve_atualizar_empresa_por_cnpj() {
        this.repository.updateEmpresaPorCnpj(cnpjFiltro, this.empresaUpdate);
    }

    @Test
    public void deve_atualizar_empresa_por_filtro() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.repository.updateEmpresaPorFiltro(this.empresaFind, this.empresaUpdate);
    }

    @Test
    public void deve_atualizar_empresa_por_filtros() {
        empresaFind = new Empresa();
        this.empresaFind.setRazaoSocial("Fiap");
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setEmail("ciclano@teste.com");
        this.repository.updateEmpresaPorFiltro(this.empresaFind, this.empresaUpdate);
    }

    @Test
    public void deve_atualizar_empresas_por_filtro() {
        empresaFind = new Empresa();
        this.empresaFind.setRazaoSocial("Uniquintal");
        Empresa emp = new Empresa();
        emp.setRazaoSocial("TESTE");
        this.repository.updateEmpresasPorFiltro(this.empresaFind, emp);
    }

    @Test
    public void deve_atualizar_empresas_por_filtros() {
        empresaFind = new Empresa();
        this.empresaFind.setRazaoSocial("Unip");
        this.empresaFind.setProprietario("Eu");
        Empresa emp = new Empresa();
        emp.setRazaoSocial("TESTE");
        this.repository.updateEmpresasPorFiltro(this.empresaFind, emp);
    }

    // Remove===================================================================================================================
    @Test
    public void deve_remover_empresa_por_cnpj() {
        this.repository.removeEmpresaPorCnpj(empresa.getCnpj());
    }

    @Test
    public void deve_remover_empresa_por_filtro() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.repository.removeEmpresaPorFiltro(this.empresaFind);
    }

    @Test
    public void deve_remover_empresa_por_filtros() {
        empresaFind = new Empresa();
        this.empresaFind.setRazaoSocial("Fiap");
        this.empresaFind.setProprietario("Fulano");
        this.repository.removeEmpresaPorFiltro(this.empresaFind);
    }

    @Test
    public void deve_remover_empresas_por_filtro() {
        empresaFind = new Empresa();
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.repository.removeEmpresasPorFiltro(this.empresaFind);
    }

    @Test
    public void deve_remover_empresas_por_filtros() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.repository.removeEmpresasPorFiltro(this.empresaFind);
    }

    // Find===================================================================================================================
    @Test
    public void deve_buscar_empresa_por_cnpj() {
        out.println(this.repository.buscaEmpresaPorCnpj("61377991000110"));
    }

    @Test
    public void deve_buscar_empresa_por_filtro() {
        empresaFind = new Empresa();
        this.empresaFind.isPesquisa();
        this.empresaFind.setDataDeCriacao(parse("2016-03-30"));
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_filtros() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setRazaoSocial("Unip");
        this.empresaFind.setEmail("eu@teste.com.br");
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_filtro() {
        empresaFind = new Empresa();
        this.empresaFind.setEmail("eu@teste.com.br");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_proprietario_e_email() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Alguém");
        this.empresaFind.setEmail("eu@teste.com.br");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_proprietario_e_site() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Alguém");
        this.empresaFind.setSite("teste.com.br");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_enderecos_e_telefones() {
        empresaFind = new Empresa();
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.empresaFind.setTelefones(empresa.getTelefones());
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_proprietario_e_enderecos() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Alguém");
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_telefones_e_emails() {
        empresaFind = new Empresa();
        this.empresaFind.setTelefones(empresa.getTelefones());
        this.empresaFind.setEmail("ciclano@teste.com.br");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_razaoSocial_e_emails() {
        empresaFind = new Empresa();
        this.empresaFind.setRazaoSocial("Unip");
        this.empresaFind.setEmail("ciclano@teste.com.br");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_data_de_criacao_e_razaoSocial() {
        empresaFind = new Empresa();
        this.empresaFind.isPesquisa();
        this.empresaFind.setDataDeCriacao(parse("2016-03-30"));
        this.empresaFind.setRazaoSocial("Unip");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_data_de_criacao_e_proprietario() {
        empresaFind = new Empresa();
        this.empresaFind.isPesquisa();
        this.empresaFind.setDataDeCriacao(parse("2016-03-30"));
        this.empresaFind.setProprietario("Alguém");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_site_e_email() {
        empresaFind = new Empresa();
        this.empresaFind.setSite("teste.com.br");
        this.empresaFind.setEmail("eu@teste.com.br");
        out.println(this.repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_proprietario_email_e_enderecos() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setEmail("eu@teste.com.br");
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_proprietario_enderecos_e_telefones() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.empresaFind.setTelefones(empresa.getTelefones());
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_proprietario_enderecos_e_razaoSocial() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.empresaFind.setRazaoSocial(empresa.getRazaoSocial());
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_enderecos_telefones_e_razaoSocial() {
        empresaFind = new Empresa();
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.empresaFind.setTelefones(empresa.getTelefones());
        this.empresaFind.setRazaoSocial(empresa.getRazaoSocial());
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_enderecos_telefones_e_email() {
        empresaFind = new Empresa();
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.empresaFind.setTelefones(empresa.getTelefones());
        this.empresaFind.setEmail("eu@teste.com.br");
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_proprietario_razaoSocial_site_e_email() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setRazaoSocial(empresa.getRazaoSocial());
        this.empresaFind.setSite("teste.com.br");
        this.empresaFind.setEmail("eu@teste.com.br");
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_proprietario_razaoSocial_enderecos_e_telefones() {
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setRazaoSocial(empresa.getRazaoSocial());
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        this.empresaFind.setTelefones(empresa.getTelefones());
        out.println(this.repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_todos_os_campos() {
        out.println(this.repository.buscaEmpresaPorFiltro(empresa));
    }

    // Paging===================================================================================================================
    @Test
    public void deve_paginar_busca_de_empresas() {
        out.println(this.repository.buscasPaginadas(2, 2));
    }

    @Test
    public void deve_retornar_sempre_mesmas_empresas_na_pagina() {
        List<List<Empresa>> empresasBuscaOriginal = new ArrayList<List<Empresa>>();
        List<List<Empresa>> empresasBuscaComparacao = new ArrayList<List<Empresa>>();
        int numeroPagina = 3;
        int elementosPorPagina = 6;
        for(int i = 0 ; i < numeroPagina ; i++) {
            empresasBuscaOriginal.add(this.repository.buscasPaginadas(i, elementosPorPagina));
            empresasBuscaComparacao.add(this.repository.buscasPaginadas(i, elementosPorPagina));
            assertTrue(empresasBuscaOriginal.get(i).equals((empresasBuscaComparacao.get(i))));
        }
    }

    @Test
    public void nao_deve_retornar_a_mesma_empresa_em_outras_paginas() {
        List<Empresa> empresasBuscaOriginal = this.repository.buscasPaginadas(1, 10000);
        List<Empresa> empresasBuscaComparacao = this.repository.buscasPaginadas(2, 10000);
        for(Empresa emp : empresasBuscaOriginal) {
            for(Empresa emp2 : empresasBuscaComparacao) {
                assertFalse(emp.equals(emp2));
            }
        }

    }

    // Null======================================================================================================================
    @Test
    public void deve_tratar_nullPointer_para_filtro_nulo() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("O filtro não pode ser null.");
        out.println(this.repository.buscaEmpresaPorFiltro(null));
    }

    @Test
    public void deve_tratar_nullPointer_para_empresa_filtro() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("O filtro não pode ser null.");
        this.repository.updateEmpresaPorFiltro(this.empresaFind, this.empresa);
    }

    @Test
    public void deve_tratar_nullPointer_para_empresa_nova() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("A empresa nova não pode ser null.");
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.repository.updateEmpresaPorFiltro(this.empresaFind, null);
    }
}
