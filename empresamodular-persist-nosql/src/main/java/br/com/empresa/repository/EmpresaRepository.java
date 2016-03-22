package br.com.empresa.repository;

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.empresa.repository.util.EmpresaDocument.toDocument;
import static br.com.empresa.repository.util.EmpresaDocument.toDocumentFilter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.util.ValidationUtil;
import br.com.empresa.repository.util.MongoClientDate;

public class EmpresaRepository {

    private String host;
    private int port;
    private String db;
    public static final String COLLECTION = "empresa";
    private MongoClient mongoClient;

    public EmpresaRepository(String host, int port, String db) {
        this.host = host;
        this.port = port;
        this.db = db;
    }

    public void saveEmpresa(Empresa empresa) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            if (hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.insertOne(toDocument(empresa));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    public void updateEmpresa(Empresa empresaFiltro, Empresa empresa) {
        try {
            if (ValidationUtil.hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(toDocumentFilter(empresaFiltro), new Document("$set", toDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    public void updateEmpresas(Empresa empresaFiltro, Empresa empresa) {
        try {
            if (ValidationUtil.hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateMany(toDocumentFilter(empresaFiltro), new Document("$set", toDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    public void removeEmpresa(Empresa empresaFiltro) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteOne(new Document("cnpj", empresaFiltro.getCnpj()));
        } finally {
            mongoClient.close();
        }
    }

    public void removeEmpresas(Empresa empresaFiltro) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteMany(new Document("cnpj", empresaFiltro.getCnpj()));
        } finally {
            mongoClient.close();
        }
    }

}
