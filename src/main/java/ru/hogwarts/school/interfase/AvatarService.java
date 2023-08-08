package ru.hogwarts.school.interfase;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.Collection;

public interface AvatarService {
    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    Avatar findAvatar(long studentId);

    Collection<Avatar> getAllAvatar(Integer pageNumber, Integer pageSize);
}
