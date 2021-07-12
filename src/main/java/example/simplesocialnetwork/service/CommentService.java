package example.simplesocialnetwork.service;

import example.simplesocialnetwork.domain.Comment;
import example.simplesocialnetwork.domain.Message;
import example.simplesocialnetwork.domain.User;
import example.simplesocialnetwork.domain.Views;
import example.simplesocialnetwork.dto.EventType;
import example.simplesocialnetwork.dto.ObjectType;
import example.simplesocialnetwork.repo.CommentRepo;
import example.simplesocialnetwork.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);

        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
