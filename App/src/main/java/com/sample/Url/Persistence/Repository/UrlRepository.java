package com.sample.Url.Persistence.Repository;

import com.sample.Url.Persistence.Model.UrlDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlDAO, String>
{
}
