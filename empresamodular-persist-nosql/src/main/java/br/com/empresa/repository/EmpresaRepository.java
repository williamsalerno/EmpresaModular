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
package br.com.empresa.repository;

import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.findToDocumentFilter;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.toDocument;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.updateToDocumentFilter;
import static br.com.contmatic.empresawilliam.assembler.MongoClientDate.codecDate;
import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.empresa.repository.util.EmpresaRepositoryUtil.FieldsList;
import static br.com.empresa.repository.util.EmpresaRepositoryUtil.getDb;
import static br.com.empresa.repository.util.EmpresaRepositoryUtil.iterateCollection;
import static br.com.empresa.repository.util.EmpresaRepositoryUtil.validateCnpj;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.mongodb.client.model.Projections.include;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import br.com.contmatic.empresawilliam.Empresa;

/**
 * The Class EmpresaRepository.
 *
 * @author William
 */
public class EmpresaRepository {

    /** The host. */
    private String host;

    /** The port. */
    private int port;

    /** The db. */
    private String db;

    /** The Constant COLLECTION. */
    public static final String COLLECTION = "empresa";

    private FindIterable<Document> collection;

    /**
     * Instantiates a new empresa repository.
     *
     * @param host the host
     * @param port the port
     * @param db the db
     */
    public EmpresaRepository(String host, int port, String db) {
        this.host = host;
        this.port = port;
        this.db = db;
    }

    /**
     * Save empresa.
     *
     * @param empresa the empresa
     */
    public void saveEmpresa(Empresa empresa) {
        try {
            checkNotNull(empresa, "A empresa não pode ser null.");
            checkArgument(!hasErrors(empresa), "Foi encontrado erro em Empresa.");
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.insertOne(toDocument(empresa));
        } catch (MongoWriteException e) {
            throw new IllegalStateException("Empresa já existe.");
        } finally {
            connectDb().close();
        }
    }

    /**
     * Update empresas.
     *
     * @param cnpjFiltro the cnpj filtro
     * @param empresa the empresa
     */
    public void updateEmpresaPorCnpj(String cnpjFiltro, Empresa empresa) {
        try {
            checkNotNull(cnpjFiltro, "cnpjFiltro não pode ser null.");
            checkNotNull(empresa, "A empresa não pode ser null.");
            validateCnpj(cnpjFiltro);
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.updateOne(new Document("_id", cnpjFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } finally {
            connectDb().close();
        }
    }

    /**
     * Valida um cnpj da empresa, utilizado nos metodos de pesquisa, exclusão e atualização.
     *
     * @param empresaFiltro the empresa filtro
     * @param empresa the empresa
     */

    public void updateEmpresaPorFiltro(Empresa empresaFiltro, Empresa empresa) {
        try {
            checkNotNull(empresaFiltro, "O filtro não pode ser null.");
            checkNotNull(empresa, "A empresa nova não pode ser null.");
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.updateOne(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } finally {
            connectDb().close();
        }
    }

    /**
     * Update empresas por filtro.
     *
     * @param empresaFiltro the empresa filtro
     * @param empresa the empresa
     */
    public void updateEmpresasPorFiltro(Empresa empresaFiltro, Empresa empresa) {
        try {
            checkNotNull(empresaFiltro, "O filtro não pode ser null.");
            checkNotNull(empresa, "A empresa não pode ser null.");
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.updateMany(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } finally {
            connectDb().close();
        }
    }

    /**
     * Remove empresa.
     *
     * @param cnpjFiltro the cnpj filtro
     */
    public void removeEmpresaPorCnpj(String cnpjFiltro) {
        try {
            checkNotNull(cnpjFiltro, "cnpjFiltro não pode ser null.");
            validateCnpj(cnpjFiltro);
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.deleteOne(new Document("_id", cnpjFiltro));
        } finally {
            connectDb().close();
        }
    }

    /**
     * Remove empresas.
     *
     * @param empresaFiltro the empresa filtro
     */
    public void removeEmpresaPorFiltro(Empresa empresaFiltro) {
        try {
            checkNotNull(empresaFiltro, "O filtro não pode ser null.");
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.deleteOne(findToDocumentFilter(empresaFiltro));
        } finally {
            connectDb().close();
        }
    }

    /**
     * Remove empresas por filtro.
     *
     * @param empresaFiltro the empresa filtro
     */
    public void removeEmpresasPorFiltro(Empresa empresaFiltro) {
        try {
            checkNotNull(empresaFiltro, "O filtro não pode ser null.");
            MongoCollection<Document> collection = getDb(connectDb(), this.db).getCollection(COLLECTION);
            collection.deleteMany(findToDocumentFilter(empresaFiltro));
        } finally {
            connectDb().close();
        }
    }

    /**
     * Busca empresa.
     *
     * @param cnpjFiltro the cnpj filtro
     * @return the list
     */
    public List<Empresa> buscaEmpresaPorCnpj(String cnpjFiltro) {
        try {
            checkNotNull(cnpjFiltro, "cnpjFiltro não pode ser null.");
            validateCnpj(cnpjFiltro);
            this.collection = getDb(connectDb(), this.db).getCollection(COLLECTION).find(new Document("_id", cnpjFiltro));
            return iterateCollection(this.collection);
        } finally {
            connectDb().close();
        }

    }

    /**
     * Busca empresa por.
     *
     * @param empresaFiltro the empresa filtro
     * @return the list
     */
    public List<Empresa> buscaEmpresaPorFiltro(Empresa empresaFiltro) {
        try {
            checkNotNull(empresaFiltro, "O filtro não pode ser null.");
            this.collection = getDb(connectDb(), this.db).getCollection(COLLECTION).find(updateToDocumentFilter(empresaFiltro)).projection(include(FieldsList(updateToDocumentFilter(empresaFiltro))))
                    .limit(1);
            return iterateCollection(this.collection);
        } finally {
            connectDb().close();
        }
    }

    /**
     * Busca empresas por filtro.
     *
     * @param empresaFiltro the empresa filtro
     * @return the list
     */
    public List<Empresa> buscaEmpresasPorFiltro(Empresa empresaFiltro) {
        try {
            checkNotNull(empresaFiltro, "O filtro não pode ser null.");
            this.collection = getDb(connectDb(), this.db).getCollection(COLLECTION).find(updateToDocumentFilter(empresaFiltro)).projection(include(FieldsList(updateToDocumentFilter(empresaFiltro))));
            return iterateCollection(this.collection);
        } finally {
            connectDb().close();
        }
    }

    /**
     * Paginar buscas.
     *
     * @param numeroPagina the numero pagina
     * @param elementosPorPagina the elementos por pagina
     * @return the list
     */
    public List<Empresa> buscasPaginadas(int numeroPagina, int elementosPorPagina) {
        try {
            checkArgument(elementosPorPagina != 0, "Elementos por página deve ser diferente de 0.");
            this.collection = getDb(connectDb(), this.db).getCollection(COLLECTION).find().skip(elementosPorPagina * (numeroPagina - 1)).limit(elementosPorPagina).sort(new Document("_id", 1));
            return iterateCollection(this.collection);
        } finally {
            connectDb().close();
        }
    }

    private MongoClient connectDb() {
        return new MongoClient(this.host + ":" + this.port, codecDate());
    }
}
