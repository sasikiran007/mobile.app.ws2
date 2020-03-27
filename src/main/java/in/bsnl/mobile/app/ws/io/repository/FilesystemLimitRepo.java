package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.FilesystemLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesystemLimitRepo extends CrudRepository<FilesystemLimit,Integer> {

}
