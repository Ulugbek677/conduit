package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "following", source = "user", qualifiedByName = "toDtoAuthorFollowing")
    public abstract ProfileDTO toDto(User user);

    @Named("toDtoAuthorFollowing")
    public Boolean toDtoAuthorFollowing(User user){
        //TODO change userId get from ContextHolder
        Long userId = 4L;
        return userRepository.isFollowedToArticleOwner(user.getId(), userId).equalsIgnoreCase("true");
    }

    public abstract User toEntity(ProfileDTO profileDTO);
}
