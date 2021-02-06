package ru.atiskov.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import ru.atiskov.domain.Author;
import ru.atiskov.repositories.AuthorRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "atiskov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertRepin", author = "atiskov2")
    public void insertRepin(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document().append("firstName", "Repin");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertTolkien", author = "atiskov")
    public void insertTolkien(AuthorRepository repository) {
        repository.save(new Author("Tolkien"));
    }

}
