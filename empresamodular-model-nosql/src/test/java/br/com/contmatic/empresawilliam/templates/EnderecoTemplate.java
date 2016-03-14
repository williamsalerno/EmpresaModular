package br.com.contmatic.empresawilliam.templates;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;

import br.com.contmatic.empresawilliam.Endereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class EnderecoTemplate.
 *
 * @author williansalerno
 */
public class EnderecoTemplate implements TemplateLoader {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.six2six.fixturefactory.loader.TemplateLoader#load()
     */
    @Override
    public void load() {
        Fixture.of(Endereco.class).addTemplate("endereco_valido", new Rule() {
            {
                add("tipoLogradouro", random("Rua", "Avenida", "Praça", "Parque"));
                add("nomeLogradouro", random("Exemplo", "Teste", "Qualquer Lugar", "Lugar Nenhum"));
                add("numeroEndereco", random(Integer.class, range(1, 9999)));
                add("tipoEndereco", random(COMERCIAL, RESIDENCIAL));
                add("cep", random("12345000", "54321000", "12345123", "54321321"));
            }
        });

        Fixture.of(Endereco.class).addTemplate("endereco_invalido", new Rule() {
            {
                add("tipoLogradouro", random("R", "Av", "Pr", "b"));
                add("nomeLogradouro", random("a", "b", "c", "d"));
                add("numeroEndereco", random(Integer.class, range(10000, 11000)));
                add("tipoEndereco", random(COMERCIAL, RESIDENCIAL));
                add("cep", random("abc", "5432100087", "ab123456", "2"));
            }
        });

    }

}
