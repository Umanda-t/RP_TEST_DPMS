package com.example.dpms_research_sample;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PT_PDFExporter {
    private List<PT> listPT;

    public PT_PDFExporter(List<PT> listPT) {
        this.listPT = listPT;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Time", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Systolic Blood Pressure (mmHg)", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Diastolic Blood Pressure (mmHg)", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Pulse Rate (BMP)", font));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {
        for (PT pt : listPT) {
            table.addCell(pt.getDate());
            table.addCell(pt.getTime());
            table.addCell(String.valueOf(pt.getSystolic()));
            table.addCell(String.valueOf(pt.getDiastolic()));
            table.addCell(String.valueOf(pt.getPulse()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Blood Pressure Tracker All Records", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.9f,1.9f, 3.5f, 3.0f, 3.6f });
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
