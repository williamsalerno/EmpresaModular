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
import static br.com.contmatic.empresawilliam.assembler.EmpresaObject.empresaToObject;
import static br.com.contmatic.empresawilliam.assembler.MongoClientDate.codecDate;
import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.empresa.repository.util.EmpresaUtil.FieldsList;
import static br.com.empresa.repository.util.EmpresaUtil.validateCnpj;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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

    /** The mongo client. */
    private MongoClient mongoClient;

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
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            if (hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.insertOne(toDocument(empresa));
        } catch (MongoWriteException e) {
            throw new IllegalStateException("Empresa já existe.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
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
            validateCnpj(cnpjFiltro);
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(new Document("_id", cnpjFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
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
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
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
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateMany(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Remove empresa.
     *
     * @param cnpjFiltro the cnpj filtro
     */
    public void removeEmpresaPorCnpj(String cnpjFiltro) {
        try {
            validateCnpj(cnpjFiltro);
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteOne(new Document("_id", cnpjFiltro));
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Remove empresas.
     *
     * @param empresaFiltro the empresa filtro
     */
    public void removeEmpresaPorFiltro(Empresa empresaFiltro) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteOne(findToDocumentFilter(empresaFiltro));
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Remove empresas por filtro.
     *
     * @param empresaFiltro the empresa filtro
     */
    public void removeEmpresasPorFiltro(Empresa empresaFiltro) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteMany(findToDocumentFilter(empresaFiltro));
        } finally {
            mongoClient.close();
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
            validateCnpj(cnpjFiltro);
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find(new Document("_id", cnpjFiltro));
            final List<Empresa> empresas = new ArrayList<Empresa>();
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally {
            mongoClient.close();
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
            List<Empresa> empresas = new ArrayList<Empresa>();
            Document filtro = updateToDocumentFilter(empresaFiltro);
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find(filtro).projection(include(FieldsList(filtro))).limit(1);
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally

        {
            mongoClient.close();
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
            List<Empresa> empresas = new ArrayList<Empresa>();
            Document filtro = updateToDocumentFilter(empresaFiltro);
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection;
            collection = database.getCollection(COLLECTION).find(filtro).projection(include(FieldsList(filtro)));
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally

        {
            mongoClient.close();
        }
    }

    /**
     * Paginar buscas.
     *
     * @param numeroPagina the numero pagina
     * @param elementosPorPagina the elementos por pagina
     * @return the list
     */
    public List<Empresa> paginarBuscas(int numeroPagina, int elementosPorPagina) {
        try {
            List<Empresa> empresas = new ArrayList<Empresa>();
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection;
            collection = database.getCollection(COLLECTION).find().skip(numeroPagina * (elementosPorPagina - 1)).limit(elementosPorPagina).sort(new Document("_id", 1));
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally

        {
            mongoClient.close();
        }
    }
}
