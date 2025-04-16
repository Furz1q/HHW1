package com.example.demo.service;

import com.example.demo.model.Avatar;
import com.example.demo.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AvatarService {

    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Page<Avatar> getAvatarsPaginated(int page, int size) {
        logger.info("Was invoked method for getting paginated avatars");
        logger.debug("Pagination parameters - page: {}, size: {}", page, size);

        if (page < 0 || size <= 0) {
            logger.warn("Invalid pagination request - page: {}, size: {}", page, size);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Avatar> result = avatarRepository.findAll(pageable);

        logger.debug("Retrieved {} avatars on page {}", result.getNumberOfElements(), page);
        return result;
    }
}
