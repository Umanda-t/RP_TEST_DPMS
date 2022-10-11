package com.example.dpms_research_sample;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.awt.Color;

public class BST_PDFExporter {

    private List<BST> listBST;

    public BST_PDFExporter(List<BST> listBST) {
        this.listBST = listBST;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Blood Sugar Level(mg/dL)", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Time", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Period", font));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {
        for (BST bst : listBST) {
            table.addCell(bst.getDate());
            table.addCell(String.valueOf(bst.getBloodsugar()));
            table.addCell(bst.getTime());
            table.addCell(bst.getPeriod());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Blood Sugar Tracker All Records", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.6f });
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
