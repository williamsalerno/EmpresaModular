package br.com.contmatic.empresawilliam.templates;

import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;

import br.com.contmatic.empresawilliam.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class TelefoneTemplate.
 *
 * @author williansalerno
 */
public class TelefoneTemplate implements TemplateLoader {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.six2six.fixturefactory.loader.TemplateLoader#load()
     */
    @Override
    public void load() {
        Fixture.of(Telefone.class).addTemplate("fixo_valido", new Rule() {
            {
                add("tipoTelefone", random(FIXO));
                add("ddd", random(Integer.class, range(11, 99)));
                add("telefone", regex("[0-9]{8}"));
            }
        });

        Fixture.of(Telefone.class).addTemplate("celular_valido", new Rule() {
            {
                add("tipoTelefone", random(CELULAR));
                add("ddd", random(Integer.class, range(11, 99)));
                add("telefone", regex("[9][0-9]{8}"));
            }
        });

        Fixture.of(Telefone.class).addTemplate("fixo_invalido", new Rule() {
            {
                add("tipoTelefone", FIXO);
                add("ddd", random(Integer.class, range(100, 500)));
                add("telefone", regex("[0-9]{7}+|[0-9]{9}+"));
            }
        });

        Fixture.of(Telefone.class).addTemplate("celular_invalido", new Rule() {
            {
                add("tipoTelefone", random(CELULAR));
                add("ddd", random(Integer.class, range(11, 99)));
                add("telefone", regex("[0-9]{8}+|[0-9]{10}+"));
            }
        });

        Fixture.of(Telefone.class).addTemplate("ddd_menor_que_limite").inherits("fixo_invalido", new Rule() {
            {
                add("ddd", random(Integer.class, range(-10, 10)));
            }
        });

    }

}
