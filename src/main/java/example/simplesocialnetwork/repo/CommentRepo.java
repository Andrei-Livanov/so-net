package example.simplesocialnetwork.repo;

import example.simplesocialnetwork.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
