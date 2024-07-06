package com.fqh.utilities.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {
    /**
     * 获取文件内容
     *
     * @param file
     * @param contentType
     * @return {@link String}
     */
    public static String getContent(File file, String contentType) {
        if (file == null){
            return null;
        }
        switch (contentType){
            case "application/pdf":
                try {
                    StringBuffer sb = new StringBuffer();
                    PdfReader pdfReader = new PdfReader(new FileInputStream(file));
                    PdfDocument pdfDocument = new PdfDocument(pdfReader);

                    int numberOfPages = pdfDocument.getNumberOfPages();
                    for (int i = 1; i <= numberOfPages; i++) {
                        PdfPage page = pdfDocument.getPage(i);
                        String textFromPage = PdfTextExtractor.getTextFromPage(page);
                        sb.append(textFromPage);
                    }
                    pdfReader.close();
                    pdfDocument.close();
                    return sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            default:
                return null;

        }
    }
}
