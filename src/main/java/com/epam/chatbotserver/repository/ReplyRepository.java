package com.epam.chatbotserver.repository;

import com.epam.chatbotserver.models.Reply;
import com.epam.chatbotserver.utility.IntentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Victor on 05.07.2018.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByIntentType(IntentTypes intentType);
}
