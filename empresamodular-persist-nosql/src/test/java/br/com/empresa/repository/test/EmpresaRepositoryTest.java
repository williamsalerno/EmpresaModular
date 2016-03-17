package br.com.empresa.repository.test;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;
import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;
import static br.com.empresa.repository.util.EmpresaDocument.toDocument;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;
import br.com.empresa.repository.EmpresaRepository;

public class EmpresaRepositoryTest {

    private Empresa empresa = new Empresa();
    Set<Endereco> endereco = new HashSet<Endereco>();
    Set<Telefone> telefone = new HashSet<Telefone>();

    @Before
    public void setUp() {
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

        this.empresa.setCnpj("12345678911234");
        this.empresa.setRazaoSocial("Exemplo");
        this.empresa.setProprietario("Fulano");
        this.empresa.setEmail("timetrial@gmail.com");
        this.empresa.setSite("www.timetrialfac.com");
        this.empresa.setEnderecos(endereco);
        this.empresa.setTelefones(telefone);
        // empresa.setDataDeCriacao(LocalDate.now());
        // empresa.setDataDeAlteracao(LocalDate.now().plusDays(20));
    }

    @Test
    public void deve_incluir_doc() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.saveEmpresa(this.empresa);
    }

    @Test
    public void deve_atualizar_doc() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.updateEmpresa(this.empresa);
    }

    @Test
    public void deve_atualizar_doc_multi_true() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        repository.updateEmpresas(toDocument(this.empresa), "site", "www.timetrialfac.com", "www.timetrialfac.com.br");
    }

    @Test
    public void deve_remover_doc() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
    }

}
