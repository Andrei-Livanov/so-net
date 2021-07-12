package example.simplesocialnetwork.repo;

import example.simplesocialnetwork.domain.User;
import example.simplesocialnetwork.domain.UserSubscription;
import example.simplesocialnetwork.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);

    List<UserSubscription> findByChannel(User channel);

    UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
