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
package br.com.contmatic.empresawilliam.templates;

import static org.joda.time.LocalDate.now;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class EmpresaTemplate.
 *
 * @author williansalerno
 */
public class EmpresaTemplate implements TemplateLoader {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.six2six.fixturefactory.loader.TemplateLoader#load()
     */
    @Override
    public void load() {
        Fixture.of(Empresa.class).addTemplate("empresa_valida", new Rule() {
            {
                add("razaoSocial", random("Uniesquina", "Uniquintal", "Unip", "Fiap"));
                add("cnpj", cnpj());
                add("proprietario", random("Fulano", "Ciclano", "Alguém", "Eu"));
                add("email", random("fulano@exemplo.com", "ciclano@teste.com", "eu@teste.com.br"));
                add("site", random("teste.com.br", "exemplo.com.br"));
                add("enderecos", has(2).of(Endereco.class, "endereco_valido"));
                add("telefones", has(2).of(Telefone.class, "fixo_valido", "celular_valido"));
                add("dataDeCriacao", random(now()));
                add("dataDeAlteracao", random(now().plusDays(300)));
            }
        });

        Fixture.of(Empresa.class).addTemplate("empresa_invalida", new Rule() {
            {
                add("cnpj", random("12345678911", "00111222333344555666666", "abcderf5467534", "9q834jdfhnjnd"));
                add("email", random("fulano@.com", "ciclano@teste", "exemplo.com", "teste@br.com.com", "_teste@teste.com", "teste@.br"));
                add("site", random("teste.com", "exemplocom", ".teste.com.br", "exemplo.br.com"));
                add("enderecos", has(1).of(Endereco.class, "endereco_invalido"));
                add("telefones", has(1).of(Telefone.class, "fixo_invalido"));
                add("dataDeCriacao", random(now().minusDays(300)));
                add("dataDeAlteracao", random(now()));
            }
        });
    }
}
