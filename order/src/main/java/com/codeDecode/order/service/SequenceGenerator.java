package com.codeDecode.order.service;

import com.codeDecode.order.entity.Sequence;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;
    public int generatorNextOrderId() {
        Sequence counter = mongoOperations.findAndModify(
                query(where("_id").is("sequence")),
                new Update().inc("sequence", 1),
                options().returnNew(true).upsert(true),
                Sequence.class);

        // Check if counter or counter.getSequence() is null
        if (counter == null) {
            throw new IllegalStateException("Sequence value is null or not found in database.");
        }

        // Return the sequence value
        return counter.getSequence();
    }

//    public int generatorNextOrderId() {
//        Sequence counter = mongoOperations.findAndModify(
//                query(where("_id").is("sequence")),
//                new Update().inc("sequence", 1),
//                options().returnNew(true).upsert(true),
//                Sequence.class);
//        //return Integer.parseInt(counter.getSequence());
//         //Check if counter or counter.getSequence() is null
//        if (counter == null || counter.getSequence() == null) {
//            throw new IllegalStateException("Sequence value is null or not found in database.");
//        }
//
//        // Convert the sequence value to an integer and return it
//        try {
//            return Integer.parseInt(counter.getSequence());
//        } catch (NumberFormatException e) {
//            throw new IllegalStateException("Failed to parse sequence value as integer: " + counter.getSequence(), e);
//        }
//    }

}
