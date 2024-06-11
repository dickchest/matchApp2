package com.match.services.utils.file;

import com.google.cloud.storage.BlobId;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FileUtils {

    public String uploadFileAndGetDownloadUrl(MultipartFile multipartFile) throws IllegalAccessException, IOException {
        // Проверяем не пустой ли файл
        if (multipartFile.isEmpty()) {
            throw new IllegalAccessException("Cannot upload an empty file");
        }

        // Получаем ссылку на Firebase Storage (если решим сохранять файл там)
        StorageClient storageClient = StorageClient.getInstance();

        // Генерируем уникальное имя файла
        String fileName = "uploads/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        // Загружаем файл и получаем его BlobId
        BlobId blobId = storageClient.bucket().create(fileName, multipartFile.getBytes(), multipartFile.getContentType()).getBlobId();

        // Получаем ссылку на скачивание файла
        return "https://firebasestorage.googleapis.com/v0/b/" +
                storageClient.bucket().getName() + "/o/" +
                URLEncoder.encode(blobId.getName(), StandardCharsets.UTF_8) +
                "?alt=media";
    }
}
