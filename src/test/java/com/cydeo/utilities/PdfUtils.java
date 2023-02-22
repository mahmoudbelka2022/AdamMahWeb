package com.cydeo.utilities;

import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.*;

    public class PdfUtils {

        public static void convertHtmlToPdf(String htmlFilePath, String pdfFilePath) throws Exception {
            String html = readHtmlFile(htmlFilePath);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            OutputStream os = new FileOutputStream(pdfFilePath);
            renderer.createPDF(os);
            os.close();
        }

        private static String readHtmlFile(String filePath) throws Exception {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        }
    }


