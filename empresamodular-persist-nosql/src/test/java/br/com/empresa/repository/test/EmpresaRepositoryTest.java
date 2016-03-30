package br.com.empresa.repository.test;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;
import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;
import br.com.empresa.repository.EmpresaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * @author William
 *
 */
public class EmpresaRepositoryTest {

    private Empresa empresa;
    private Empresa empresaUpdate;
    private Empresa empresaFind;
    Set<Endereco> endereco;
    Set<Telefone> telefone;
    List<String> pesquisa;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_valida");
        empresaUpdate = new Empresa();
        empresaFind = new Empresa();
        endereco = new HashSet<Endereco>();
        telefone = new HashSet<Telefone>();
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();

        endereco1.setTipoLogradouro("Rua");
        endereco1.setNomeLogradouro("Exemplo");
        endereco1.setNumeroEndereco(100);
        endereco1.setCep("12345678");
        endereco1.setTipoEndereco(COMERCIAL);

        endereco2.setTipoLogradouro("Avenida");
        endereco2.setNomeLogradouro("Teste");
        endereco2.setNumeroEndereco(999);
        endereco2.setCep("87654321");
        endereco2.setTipoEndereco(RESIDENCIAL);

        this.endereco.add(endereco1);
        this.endereco.add(endereco2);

        telefone1.setDdd(11);
        telefone1.setTelefone("12345678");
        telefone1.setTipoTelefone(FIXO);

        telefone2.setDdd(11);
        telefone2.setTelefone("123456789");
        telefone2.setTipoTelefone(CELULAR);

        telefone.add(telefone1);
        telefone.add(telefone2);

        this.empresaUpdate.setCnpj("62726723000120");
        this.empresaUpdate.setRazaoSocial("TESTE2");
        this.empresaUpdate.setProprietario("TESTE");
        this.empresaUpdate.setEmail("teste@gmail.com");
        this.empresaUpdate.setSite("teste.com.br");
        this.empresaUpdate.setEnderecos(endereco);
        this.empresaUpdate.setTelefones(telefone);
        this.empresaUpdate.setDataDeCriacao(empresa.getDataDeCriacao());
        this.empresaUpdate.setDataDeAlteracao(LocalDate.now().plusDays(20));

        this.empresaFind.setProprietario("exemplo");
        this.empresaFind.setRazaoSocial("teste");
        this.empresaFind.setEmail("exemplo@gmail.com");
    }

    @Test()
    public void deve_incluir_empresa_uma_vez() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        for(int i = 0 ; i < 99999 ; i++) {
            empresa = Fixture.from(Empresa.class).gimme("empresa_valida");
            repository.saveEmpresa(this.empresa);
        }
    }

    @Test
    public void deve_atualizar_empresa() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.updateEmpresa(this.empresa, this.empresaUpdate);
    }

    @Test
    public void deve_atualizar_empresas() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.updateEmpresas(this.empresa, this.empresaUpdate);
    }

    @Test
    public void deve_remover_empresa() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.removeEmpresa(this.empresa);
    }

    @Test
    public void deve_remover_empresas() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.removeEmpresas();
    }

    @Test
    public void deve_buscar_todas_as_empresas() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        System.out.println(repository.buscaEmpresa(this.empresa));
    }

    @Test
    public void deve_buscar_empresa_por_campos_predeterminados() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        pesquisa = new ArrayList<String>();
        pesquisa.add("_id");
        System.out.println(repository.buscaEmpresaPor(pesquisa));
    }

    @Test
    public void deve_buscar_empresa_por_lista_de_campos() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        System.out.println(repository.buscaEmpresaPor(empresaFind));
    }

    @Test
    public void deve_pagina_busca_de_empresas() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        System.out.println(repository.paginarBuscas(3, 5));
    }
}
