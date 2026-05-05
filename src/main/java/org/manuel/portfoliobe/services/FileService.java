package org.manuel.portfoliobe.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {

        if(file.isEmpty()) {
            throw new IllegalArgumentException("Empty file");
        }

        //EN: Prepare the upload directory (if it doesn't exist, it creates one)
        //IT: Preparo la cartella per l upload (se non esiste, ne crea una)
        Path path = Paths.get(uploadDir);
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        // EN:Generate a unique name for the file (to prevent two images with the same name from overwriting each other)
        // IT: Genero un nome unico per il file, per evitare che le due immagini si sovrascrivano
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;

        ///IT: Definisco il percorso completo e salvo il file
        /// EN: I define the complete path e save the file
        Path filePath =   path.resolve(newFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return  newFileName;
    }

}


