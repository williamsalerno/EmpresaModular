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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.empresa.repository.EmpresaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class EmpresaRepositoryTest.
 *
 * @author William
 */
public class EmpresaRepositoryTest {

    private Empresa empresa;
    private Empresa empresaUpdate;
    private Empresa empresaFind;
    private String cnpjFiltro;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_valida");
        empresaUpdate = Fixture.from(Empresa.class).gimme("empresa_valida");
        cnpjFiltro = empresa.getCnpj();
    }

    // Save====================================================================================================================
    @Test()
    public void deve_incluir_empresa_uma_vez() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.saveEmpresa(this.empresa);
    }

    // Update===================================================================================================================
    @Test
    public void deve_atualizar_empresa_por_cnpj() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.updateEmpresaPorCnpj(cnpjFiltro, this.empresaUpdate);
    }

    @Test
    public void deve_atualizar_empresa_por_filtro() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setRazaoSocial("Fiap");
        this.empresaFind.setEmail("ciclano@teste.com");
        repository.updateEmpresaPorFiltro(this.empresaFind, this.empresaUpdate);
    }

    @Test
    public void deve_atualizar_empresas_por_filtro() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();

        this.empresaFind.setRazaoSocial("Uniquintal");
        Empresa emp = new Empresa();
        emp.setRazaoSocial("TESTE");
        repository.updateEmpresasPorFiltro(this.empresaFind, emp);
    }

    // Remove===================================================================================================================
    @Test
    public void deve_remover_empresa_por_cnpj() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.removeEmpresaPorCnpj("47752365000136");
    }

    @Test
    public void deve_remover_empresa_por_filtro() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        repository.removeEmpresaPorFiltro(this.empresaFind);
    }

    @Test
    public void deve_remover_empresas_por_filtro() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setEnderecos(empresa.getEnderecos());
        repository.removeEmpresasPorFiltro(this.empresaFind);
    }

    // Find===================================================================================================================
    @Test
    public void deve_buscar_empresa_por_cnpj() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        System.out.println(repository.buscaEmpresaPorCnpj("47752365000136"));
    }

    @Test
    public void deve_buscar_empresa_por_filtro() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setEmail("fulano@exemplo.com");
        System.out.println(repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresa_por_filtros() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Fulano");
        this.empresaFind.setRazaoSocial("Unip");
        this.empresaFind.setEmail("eu@teste.com.br");
        System.out.println(repository.buscaEmpresaPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_filtro() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Alguém");
        this.empresaFind.setEmail("eu@teste.com.br");
        System.out.println(repository.buscaEmpresasPorFiltro(empresaFind));
    }

    @Test
    public void deve_buscar_empresas_por_filtros() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        empresaFind = new Empresa();
        this.empresaFind.setProprietario("Alguém");
        this.empresaFind.setEmail("eu@teste.com.br");
        System.out.println(repository.buscaEmpresasPorFiltro(empresaFind));
    }

    // Paging===================================================================================================================
    @Test
    public void deve_paginar_busca_de_empresas() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        System.out.println(repository.paginarBuscas(3, 20));
    }
}
