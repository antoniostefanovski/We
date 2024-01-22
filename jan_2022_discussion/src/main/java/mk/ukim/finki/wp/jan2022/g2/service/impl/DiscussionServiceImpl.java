package mk.ukim.finki.wp.jan2022.g2.service.impl;

import mk.ukim.finki.wp.jan2022.g2.model.Discussion;
import mk.ukim.finki.wp.jan2022.g2.model.DiscussionTag;
import mk.ukim.finki.wp.jan2022.g2.model.User;
import mk.ukim.finki.wp.jan2022.g2.model.exceptions.InvalidDiscussionIdException;
import mk.ukim.finki.wp.jan2022.g2.repository.DiscussionRepository;
import mk.ukim.finki.wp.jan2022.g2.service.DiscussionService;
import mk.ukim.finki.wp.jan2022.g2.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscussionServiceImpl implements DiscussionService
{

    private final DiscussionRepository discussionRepository;
    private final UserService userService;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository, UserService userService) {
        this.discussionRepository = discussionRepository;
        this.userService = userService;
    }

    @Override
    public List<Discussion> listAll() {
        return this.discussionRepository.findAll();
    }

    @Override
    public Discussion findById(Long id) {
        return this.discussionRepository.findById(id).orElseThrow(InvalidDiscussionIdException::new);
    }

    @Override
    public Discussion create(String title, String description, DiscussionTag discussionTag, List<Long> participants, LocalDate dueDate) {
        return this.discussionRepository.save(new Discussion(title, description, discussionTag, participants.stream().map(userService::findById).collect(Collectors.toList()), dueDate));
    }

    @Override
    public Discussion update(Long id, String title, String description, DiscussionTag discussionTag, List<Long> participants) {
        Discussion discussion = this.findById(id);

        discussion.setTitle(title);
        discussion.setDescription(description);
        discussion.setTag(discussionTag);
        discussion.setParticipants(participants.stream().map(userService::findById).collect(Collectors.toList()));
        return this.discussionRepository.save(discussion);
    }

    @Override
    public Discussion delete(Long id) {
        Discussion discussion = this.findById(id);
        this.discussionRepository.delete(discussion);
        return discussion;
    }

    @Override
    public Discussion markPopular(Long id) {
        Discussion discussion = this.findById(id);
        discussion.setPopular(true);
        return this.discussionRepository.save(discussion);
    }

    @Override
    public List<Discussion> filter(Long participantId, Integer daysUntilClosing) {

        if(participantId != null && daysUntilClosing != null) {
            User user = this.userService.findById(participantId);
            LocalDate date = LocalDate.now().plusDays(daysUntilClosing);

            return this.discussionRepository.findAllByParticipantsContainingAndDueDateBefore(user, date);
        }

        if(participantId != null) {
            User user = this.userService.findById(participantId);

            return this.discussionRepository.findAllByParticipantsContaining(user);
        }

        if(daysUntilClosing != null) {
            LocalDate date = LocalDate.now().plusDays(daysUntilClosing);

            return this.discussionRepository.findAllByDueDateBefore(date);
        }

        return this.discussionRepository.findAll();
    }
}
