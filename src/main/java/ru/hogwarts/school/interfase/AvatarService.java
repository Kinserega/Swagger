package ru.hogwarts.school.interfase;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    Avatar findAvatar(long studentId);
}
