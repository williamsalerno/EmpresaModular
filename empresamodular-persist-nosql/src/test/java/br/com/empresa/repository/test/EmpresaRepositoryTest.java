package br.com.empresa.repository.test;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;
import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;
import static br.com.empresa.repository.util.EmpresaDocument.toDocument;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;
import br.com.empresa.repository.EmpresaRepository;

public class EmpresaRepositoryTest {

    @Test
    public void deve_incluir_doc() {
        EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
        
        Endereco endereco1 = new Endereco();
        endereco1.setTipoLogradouro("Rua");
        endereco1.setNomeLogradouro("Exemplo");
        endereco1.setNumeroEndereco(100);
        endereco1.setCep("12345678");
        endereco1.setTipoEndereco(COMERCIAL);
        
        Endereco endereco2 = new Endereco();
        endereco2.setTipoLogradouro("Avenida");
        endereco2.setNomeLogradouro("Teste");
        endereco2.setNumeroEndereco(1000);
        endereco2.setCep("87654321");
        endereco2.setTipoEndereco(RESIDENCIAL);
        
        Set<Endereco> endereco = new HashSet<Endereco>();
        endereco.add(endereco1);
        endereco.add(endereco2);
        
        Telefone telefone1 = new Telefone();
        telefone1.setDdd(11);
        telefone1.setTelefone("12345678");
        telefone1.setTipoTelefone(FIXO);
        
        Telefone telefone2 = new Telefone();
        telefone2.setDdd(11);
        telefone2.setTelefone("123456789");
        telefone2.setTipoTelefone(CELULAR);
        
        Set<Telefone> telefone = new HashSet<Telefone>();
        telefone.add(telefone1);
        telefone.add(telefone2);
        
        Empresa empresa = new Empresa();
        empresa.setCnpj("12345678911234");
        empresa.setRazaoSocial("TimeTrial Factory");
        empresa.setProprietario("William");
        empresa.setEmail("timetrial.fac@gmail.com");
        empresa.setSite("www.timetrialfac.com.br");
        //empresa.setEnderecos(endereco);
        empresa.setTelefones(telefone);
//        empresa.setDataDeCriacao(LocalDate.now());
//        empresa.setDataDeAlteracao(LocalDate.now().plusDays(20));
        
        repository.salvarEmpresa(toDocument(empresa));
    }

}
