package org.example;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileOperationsTest {
    @Test
    void createSecureFileUploadAppliesMultipartLimits() {
        ServletFileUpload upload = FileOperations.createSecureFileUpload();

        assertEquals(FileOperations.MAX_FILE_SIZE_BYTES, upload.getFileSizeMax());
        assertEquals(FileOperations.MAX_UPLOAD_SIZE_BYTES, upload.getSizeMax());
        assertEquals(FileOperations.MAX_FILE_COUNT, upload.getFileCountMax());
        assertEquals(FileOperations.MAX_PART_HEADER_SIZE_BYTES, upload.getPartHeaderSizeMax());

        assertTrue(upload.getFileItemFactory() instanceof DiskFileItemFactory);
        DiskFileItemFactory factory = (DiskFileItemFactory) upload.getFileItemFactory();
        assertEquals(new File(System.getProperty("java.io.tmpdir")), factory.getRepository());
    }
}
