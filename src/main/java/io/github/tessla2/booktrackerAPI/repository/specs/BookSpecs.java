package io.github.tessla2.booktrackerAPI.repository.specs;

import io.github.tessla2.booktrackerAPI.model.Book;
import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {


    public static Specification<Book> titleLike(String title) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> genreEqual(Genre genre) {
        return (root, query, cb) -> cb.equal(root.get("genre"), "%" + genre + "%");
    }

    public static Specification<Book> statusEqual(Status status) {
        return (root, query, cb) -> cb.equal(root.get("status"), "%" + status + "%");
    }

    public static Specification<Book> nameAuthorEqual(String nameAuthor) {
        return (root, query, cb) -> {
            Join<Object, Object> joinAuthor = root.join("author", JoinType.LEFT);
            return cb.equal(cb.upper(joinAuthor.get("name")), "%" + nameAuthor.toUpperCase() + "%");
        };
    }


}
