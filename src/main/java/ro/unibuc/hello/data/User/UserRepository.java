package ro.unibuc.hello.data.User;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    //TODO @erol findByUsername when implementing auth
}
